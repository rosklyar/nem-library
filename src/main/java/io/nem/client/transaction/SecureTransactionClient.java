package io.nem.client.transaction;

import io.nem.client.account.domain.Hash;
import io.nem.client.account.domain.Message;
import io.nem.client.mosaic.domain.Levy;
import io.nem.client.mosaic.domain.MosaicProperty;
import io.nem.client.node.NodeClient;
import io.nem.client.transaction.encode.DefaultSigner;
import io.nem.client.transaction.encode.HexConverter;
import io.nem.client.transaction.encode.Signer;
import io.nem.client.transaction.encode.TransactionEncoder;
import io.nem.client.transaction.fee.FeeCalculator;
import io.nem.client.transaction.domain.ProvisionNamespaceTransaction;
import io.nem.client.transaction.domain.RequestAnnounce;
import io.nem.client.transaction.domain.Transaction;
import io.nem.client.transaction.domain.importance.Action;
import io.nem.client.transaction.domain.importance.ImportanceTransferTransaction;
import io.nem.client.transaction.domain.mosaic.*;
import io.nem.client.transaction.domain.multisig.Modification;
import io.nem.client.transaction.domain.multisig.RelativeChange;
import io.nem.client.transaction.domain.NemAnnounceResult;
import io.nem.client.transaction.version.Network;
import io.nem.client.transaction.version.VersionProvider;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static io.nem.client.transaction.TransactionType.*;
import static io.nem.client.transaction.domain.multisig.ModificationType.ADD_COSIGNATORY;
import static io.nem.client.transaction.domain.multisig.ModificationType.REMOVE_COSIGNATORY;
import static java.math.BigInteger.TEN;
import static java.util.stream.Collectors.toList;

public class SecureTransactionClient implements TransactionClient {

    private final Network network;
    private final FeignTransactionClient feignTransactionClient;
    private final TransactionEncoder transactionEncoder;
    private final HexConverter hexConverter;
    private final VersionProvider versionProvider;
    private final FeeCalculator feeCalculator;
    private final NodeClient nodeClient;

    public SecureTransactionClient(Network network,
                                   FeignTransactionClient feignTransactionClient,
                                   TransactionEncoder transactionEncoder,
                                   HexConverter hexConverter,
                                   VersionProvider versionProvider,
                                   FeeCalculator feeCalculator, NodeClient nodeClient) {
        this.network = network;
        this.feignTransactionClient = feignTransactionClient;
        this.transactionEncoder = transactionEncoder;
        this.hexConverter = hexConverter;
        this.versionProvider = versionProvider;
        this.feeCalculator = feeCalculator;
        this.nodeClient = nodeClient;
    }

    @Override
    public NemAnnounceResult transferNem(String privateKey, String toAddress, long microXemAmount, String message, int timeToLiveInSeconds) {

        Signer signer = new DefaultSigner(privateKey);
        String publicKey = signer.publicKey();

        int currentTime = nodeClient.extendedInfo().nisInfo.currentTime;

        Transaction transaction = transferNemTransaction(publicKey, toAddress, microXemAmount, message, currentTime, timeToLiveInSeconds);

        byte[] data = transactionEncoder.data(transaction);
        return feignTransactionClient.prepare(new RequestAnnounce(hexConverter.getString(data), signer.sign(data)));
    }

    @Override
    public NemAnnounceResult transferMosaics(String privateKey, String toAddress, List<MosaicTransfer> mosaics, int times, String message, int timeToLiveInSeconds) {

        Signer signer = new DefaultSigner(privateKey);
        String publicKey = signer.publicKey();

        int currentTime = nodeClient.extendedInfo().nisInfo.currentTime;

        Transaction transaction = mosaicsTransferTransaction(publicKey, toAddress, mosaics, times, message, currentTime, timeToLiveInSeconds);

        byte[] data = transactionEncoder.data(transaction);

        return feignTransactionClient.prepare(new RequestAnnounce(hexConverter.getString(data), signer.sign(data)));
    }

    @Override
    public NemAnnounceResult createMultisigAccount(String privateKey, List<String> cosignatories, int minCosignatories, int timeToLiveInSeconds) {

        Signer signer = new DefaultSigner(privateKey);

        int currentTime = nodeClient.extendedInfo().nisInfo.currentTime;

        List<Modification> modifications = cosignatories.stream().map(publicKey -> new Modification(1, publicKey)).collect(toList());

        Transaction transaction = aggregateModificationTransaction(signer.publicKey(), modifications, minCosignatories, currentTime, timeToLiveInSeconds);

        byte[] data = transactionEncoder.data(transaction);

        return feignTransactionClient.prepare(new RequestAnnounce(hexConverter.getString(data), signer.sign(data)));
    }

    @Override
    public NemAnnounceResult addCosignatoriesToMultisigAccount(String privateKey, List<String> cosignatories, int relativeChange, String multisigPublicKey, int timeToLiveInSeconds) {
        Signer signer = new DefaultSigner(privateKey);
        int currentTime = nodeClient.extendedInfo().nisInfo.currentTime;
        List<Modification> modifications = cosignatories.stream().map(cosignatory -> new Modification(ADD_COSIGNATORY.type, cosignatory)).collect(toList());
        return modifyMultisigAccountTransaction(signer, modifications, relativeChange, multisigPublicKey, currentTime, timeToLiveInSeconds);
    }

    @Override
    public NemAnnounceResult removeCosignatoriesFromMultisigAccount(String privateKey, List<String> cosignatories, int relativeChange, String multisigPublicKey, int timeToLiveInSeconds) {
        Signer signer = new DefaultSigner(privateKey);
        int currentTime = nodeClient.extendedInfo().nisInfo.currentTime;
        List<Modification> modifications = cosignatories.stream().map(cosignatory -> new Modification(REMOVE_COSIGNATORY.type, cosignatory)).collect(toList());
        return modifyMultisigAccountTransaction(signer, modifications, relativeChange, multisigPublicKey, currentTime, timeToLiveInSeconds);
    }

    @Override
    public NemAnnounceResult multisigTransferNem(String privateKey, String toAddress, long microXemAmount, String message, String multisigPublicKey, int timeToLiveInSeconds) {
        Signer signer = new DefaultSigner(privateKey);
        int currentTime = nodeClient.extendedInfo().nisInfo.currentTime;

        Transaction transferTransaction = transferNemTransaction(multisigPublicKey, toAddress, microXemAmount, message, currentTime, timeToLiveInSeconds);
        Transaction transaction = Transaction.builder()
                .type(MULTISIG_TRANSACTION.type)
                .version(versionProvider.version(network, MULTISIG_TRANSACTION))
                .timeStamp(currentTime)
                .signer(signer.publicKey())
                .fee(feeCalculator.multisigTransactionFee())
                .deadline(currentTime + timeToLiveInSeconds)
                .otherTrans(transferTransaction)
                .build();

        byte[] data = transactionEncoder.data(transaction);

        return feignTransactionClient.prepare(new RequestAnnounce(hexConverter.getString(data), signer.sign(data)));
    }

    @Override
    public NemAnnounceResult multisigTransferMosaics(String privateKey, String toAddress, List<MosaicTransfer> mosaics, int times, String message, String multisigPublicKey, int timeToLiveInSeconds) {

        Signer signer = new DefaultSigner(privateKey);
        int currentTime = nodeClient.extendedInfo().nisInfo.currentTime;

        Transaction transferTransaction = mosaicsTransferTransaction(multisigPublicKey, toAddress, mosaics, times, message, currentTime, timeToLiveInSeconds);
        Transaction transaction = Transaction.builder()
                .type(MULTISIG_TRANSACTION.type)
                .version(versionProvider.version(network, MULTISIG_TRANSACTION))
                .timeStamp(currentTime)
                .signer(signer.publicKey())
                .fee(feeCalculator.multisigTransactionFee())
                .deadline(currentTime + timeToLiveInSeconds)
                .otherTrans(transferTransaction)
                .build();

        byte[] data = transactionEncoder.data(transaction);

        return feignTransactionClient.prepare(new RequestAnnounce(hexConverter.getString(data), signer.sign(data)));
    }

    @Override
    public NemAnnounceResult cosignTransaction(String privateKey, String transactionHash, String multisigAddress, int timeToLiveInSeconds) {
        Signer signer = new DefaultSigner(privateKey);
        int currentTime = nodeClient.extendedInfo().nisInfo.currentTime;

        Transaction transaction = Transaction.builder()
                .type(MULTISIG_SIGNATURE.type)
                .version(versionProvider.version(network, MULTISIG_SIGNATURE))
                .timeStamp(currentTime)
                .signer(signer.publicKey())
                .fee(feeCalculator.cosigningFee())
                .deadline(currentTime + timeToLiveInSeconds)
                .otherAccount(multisigAddress)
                .otherHash(new Hash(transactionHash))
                .build();

        byte[] data = transactionEncoder.data(transaction);

        return feignTransactionClient.prepare(new RequestAnnounce(hexConverter.getString(data), signer.sign(data)));
    }

    @Override
    public NemAnnounceResult createNamespace(String privateKey, String parentNamespace, String namespace, int timeToLiveInSeconds) {
        Signer signer = new DefaultSigner(privateKey);
        int currentTime = nodeClient.extendedInfo().nisInfo.currentTime;

        ProvisionNamespaceTransaction transaction = ProvisionNamespaceTransaction.builder()
                .type(PROVISION_NAMESPACE.type)
                .version(versionProvider.version(network, PROVISION_NAMESPACE))
                .timeStamp(currentTime)
                .signer(signer.publicKey())
                .fee(feeCalculator.namespaceProvisionFee())
                .deadline(currentTime + timeToLiveInSeconds)
                .rentalFeeSink(network.rentalFeeSink)
                .rentalFee(feeCalculator.rentalFee(parentNamespace, namespace))
                .parent(parentNamespace)
                .newPart(namespace)
                .build();

        byte[] data = transactionEncoder.data(transaction);

        return feignTransactionClient.prepare(new RequestAnnounce(hexConverter.getString(data), signer.sign(data)));
    }

    @Override
    public NemAnnounceResult importanceTransfer(String privateKey, Action action, String remoteAccountPublicKey, int timeToLiveInSeconds) {
        Signer signer = new DefaultSigner(privateKey);
        int currentTime = nodeClient.extendedInfo().nisInfo.currentTime;

        ImportanceTransferTransaction transaction = ImportanceTransferTransaction.builder()
                .type(IMPORTANCE_TRANSFER_TRANSACTION.type)
                .version(versionProvider.version(network, IMPORTANCE_TRANSFER_TRANSACTION))
                .timeStamp(currentTime)
                .signer(signer.publicKey())
                .fee(feeCalculator.importanceTransferFee())
                .deadline(currentTime + timeToLiveInSeconds)
                .action(action)
                .remoteAccount(remoteAccountPublicKey)
                .build();

        byte[] data = transactionEncoder.data(transaction);

        return feignTransactionClient.prepare(new RequestAnnounce(hexConverter.getString(data), signer.sign(data)));
    }

    @Override
    public NemAnnounceResult createMosaic(String privateKey, MosaicId mosaicId, String mosaicDescription, MosaicProperties mosaicProperties, Levy levy, int timeToLiveInSeconds) {
        Signer signer = new DefaultSigner(privateKey);
        int currentTime = nodeClient.extendedInfo().nisInfo.currentTime;
        String publicKey = signer.publicKey();

        MosaicDefinition mosaicDefinition = MosaicDefinition.builder()
                .creator(publicKey)
                .id(mosaicId)
                .description(mosaicDescription)
                .levy(levy)
                .properties(newArrayList(
                        new MosaicProperty("divisibility", String.valueOf(mosaicProperties.divisibility)),
                        new MosaicProperty("initialSupply", String.valueOf(mosaicProperties.initialSupply)),
                        new MosaicProperty("supplyMutable", String.valueOf(mosaicProperties.supplyMutable)),
                        new MosaicProperty("transferable", String.valueOf(mosaicProperties.transferable))
                ))
                .build();

        MosaicDefinitionCreationTransaction mosaicDefinitionCreationTransaction = MosaicDefinitionCreationTransaction.builder()
                .type(MOSAIC_DEFINITION_CREATION.type)
                .version(versionProvider.version(network, MOSAIC_DEFINITION_CREATION))
                .timeStamp(currentTime)
                .signer(publicKey)
                .fee(feeCalculator.mosaicCreationFee())
                .deadline(currentTime + timeToLiveInSeconds)
                .creationFee(feeCalculator.mosaicRentalFee())
                .creationFeeSink(network.creationFeeSink)
                .mosaicDefinition(mosaicDefinition)
                .build();

        byte[] data = transactionEncoder.data(mosaicDefinitionCreationTransaction);

        return feignTransactionClient.prepare(new RequestAnnounce(hexConverter.getString(data), signer.sign(data)));
    }

    private Transaction transferNemTransaction(String publicKey, String toAddress, long microXemAmount, String message, int currentTime, int timeToLiveInSeconds) {
        return Transaction.builder()
                .type(TRANSFER_NEM.type)
                .version(versionProvider.version(network, TRANSFER_NEM))
                .timeStamp(currentTime)
                .signer(publicKey)
                .fee(feeCalculator.fee(microXemAmount, message))
                .deadline(currentTime + timeToLiveInSeconds)
                .recipient(toAddress)
                .amount(microXemAmount)
                .message(new Message(message, 1))
                .build();
    }

    private Transaction mosaicsTransferTransaction(String publicKey, String toAddress, List<MosaicTransfer> mosaics, int times, String message, int currentTime, int timeToLiveInSeconds) {
        return Transaction.builder()
                .type(TRANSFER_MOSAICS.type)
                .version(versionProvider.version(network, TRANSFER_MOSAICS))
                .timeStamp(currentTime)
                .signer(publicKey)
                .fee(feeCalculator.fee(mosaics, times, message))
                .deadline(currentTime + timeToLiveInSeconds)
                .recipient(toAddress)
                .amount(times * TEN.pow(6).longValue())
                .message(new Message(message, 1))
                .mosaics(mosaics)
                .build();
    }

    private Transaction aggregateModificationTransaction(String publicKey, List<Modification> modifications, int minCosignatoriesRelativeChange, int currentTime, int timeToLiveInSeconds) {
        return Transaction.builder()
                .type(MULTISIG_AGGREGATE_MODIFICATION.type)
                .version(versionProvider.version(network, MULTISIG_AGGREGATE_MODIFICATION))
                .timeStamp(currentTime)
                .signer(publicKey)
                .fee(feeCalculator.multisigAccountCreationFee())
                .deadline(currentTime + timeToLiveInSeconds)
                .modifications(modifications)
                .minCosignatories(new RelativeChange(minCosignatoriesRelativeChange))
                .build();
    }

    private NemAnnounceResult modifyMultisigAccountTransaction(Signer signer, List<Modification> modifications, int relativeChange, String multisigPublicKey, int currentTime, int timeToLiveInSeconds) {
        Transaction modificationTransaction = aggregateModificationTransaction(multisigPublicKey, modifications, relativeChange, currentTime, timeToLiveInSeconds);

        Transaction transaction = Transaction.builder()
                .type(MULTISIG_TRANSACTION.type)
                .version(versionProvider.version(network, MULTISIG_TRANSACTION))
                .timeStamp(currentTime)
                .signer(signer.publicKey())
                .fee(feeCalculator.multisigTransactionFee())
                .deadline(currentTime + timeToLiveInSeconds)
                .otherTrans(modificationTransaction)
                .build();

        byte[] data = transactionEncoder.data(transaction);

        return feignTransactionClient.prepare(new RequestAnnounce(hexConverter.getString(data), signer.sign(data)));
    }
}
