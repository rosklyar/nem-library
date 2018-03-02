package io.nem.client.transaction;

import io.nem.client.common.Message;
import io.nem.client.node.NodeClient;
import io.nem.client.transaction.encode.DefaultSigner;
import io.nem.client.transaction.encode.HexConverter;
import io.nem.client.transaction.encode.Signer;
import io.nem.client.transaction.encode.TransactionEncoder;
import io.nem.client.transaction.fee.FeeProvider;
import io.nem.client.transaction.request.RequestAnnounce;
import io.nem.client.transaction.response.NemAnnounceResult;
import io.nem.client.transaction.version.Network;
import io.nem.client.transaction.version.VersionProvider;

import static java.lang.Math.round;

public class SecureTransactionClient implements TransactionClient {

    private final Network network;
    private final FeignTransactionClient feignTransactionClient;
    private final TransactionEncoder transactionEncoder;
    private final HexConverter hexConverter;
    private final VersionProvider versionProvider;
    private final FeeProvider feeProvider;
    private final NodeClient nodeClient;

    public SecureTransactionClient(Network network,
                                   FeignTransactionClient feignTransactionClient,
                                   TransactionEncoder transactionEncoder,
                                   HexConverter hexConverter,
                                   VersionProvider versionProvider,
                                   FeeProvider feeProvider, NodeClient nodeClient) {
        this.network = network;
        this.feignTransactionClient = feignTransactionClient;
        this.transactionEncoder = transactionEncoder;
        this.hexConverter = hexConverter;
        this.versionProvider = versionProvider;
        this.feeProvider = feeProvider;
        this.nodeClient = nodeClient;
    }

    @Override
    public NemAnnounceResult transferNem(String privateKey, String toAddress, double amount, String message, int timeToLiveInSeconds) {

        Signer signer = new DefaultSigner(hexConverter.getBytes(privateKey));

        int transactionType = 257;
        int currentTime = nodeClient.extendedInfo().nisInfo.currentTime;
        long microXemAmount = round(amount * 1000000);

        Transaction transaction = new Transaction.TransactionBuilder()
                .type(transactionType)
                .version(versionProvider.version(network, transactionType))
                .timeStamp(currentTime)
                .signer(signer.publicKey())
                .fee(feeProvider.fee(microXemAmount, message))
                .deadline(currentTime + timeToLiveInSeconds)
                .recipient(toAddress)
                .amount(microXemAmount)
                .message(new Message(message, 1))
                .build();

        byte[] data = transactionEncoder.data(transaction);
        return feignTransactionClient.prepare(new RequestAnnounce(hexConverter.getString(data), signer.sign(data)));
    }
}
