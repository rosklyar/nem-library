package com.github.rosklyar.client.transaction.encode;

import com.github.rosklyar.client.mosaic.domain.Levy;
import com.github.rosklyar.client.mosaic.domain.MosaicProperty;
import com.github.rosklyar.client.transaction.domain.ProvisionNamespaceTransaction;
import com.github.rosklyar.client.transaction.domain.Transaction;
import com.github.rosklyar.client.transaction.domain.importance.ImportanceTransferTransaction;
import com.github.rosklyar.client.transaction.domain.mosaic.MosaicDefinition;
import com.github.rosklyar.client.transaction.domain.mosaic.MosaicDefinitionCreationTransaction;
import com.github.rosklyar.client.transaction.domain.mosaic.MosaicId;
import com.github.rosklyar.client.transaction.domain.mosaic.MosaicSupplyChangeTransaction;
import com.github.rosklyar.client.transaction.domain.multisig.MultisigTransaction;

import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.primitives.Bytes.concat;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public class ByteArrayTransactionEncoder implements TransactionEncoder {

    private final ByteSerializer byteSerializer;
    private final HexConverter hexConverter;
    private final int numberOfBytesInPublicKey = 32;
    private final int numberOfBytesInHash = 32;
    private final int numberOfBytesInAddress = 40;
    private final int lengthOfMinCosignatoriesStructure = 4;
    private final int nullBytesSentinelValue = 0xFFFFFFFF;

    public ByteArrayTransactionEncoder(ByteSerializer byteSerializer, HexConverter hexEncoder) {
        this.byteSerializer = byteSerializer;
        this.hexConverter = hexEncoder;
    }


    @Override
    public byte[] data(Transaction transaction) {
        byte[] commonTransactionPart = commonTransactionPart(transaction.type, transaction.version, transaction.timeStamp, transaction.signer, transaction.fee, transaction.deadline);
        byte[] transferPart = getTransferPartData(transaction);
        byte[] mosaicsPart = getAllMosaicsBytes(transaction);
        byte[] multisigAggregateModificationPart = getAggregateModificationPart(transaction);
        byte[] otherTransactionPart = otherTransactionPart(transaction.otherTrans);
        byte[] cosigningTransactionPart = cosigningTransactionPart(transaction);
        return
                concat(
                        commonTransactionPart,
                        transferPart,
                        mosaicsPart,
                        multisigAggregateModificationPart,
                        cosigningTransactionPart,
                        otherTransactionPart
                );
    }

    @Override
    public byte[] data(ProvisionNamespaceTransaction transaction) {
        if (transaction == null) {
            return new byte[0];
        }
        byte[] parentNamespacePart = isNullOrEmpty(transaction.parent) ?
                byteSerializer.intToByte(nullBytesSentinelValue) :
                concat(
                        byteSerializer.intToByte(transaction.parent.length()),
                        byteSerializer.stringToBytes(transaction.parent)
                );

        return concat(
                commonTransactionPart(transaction.type, transaction.version, transaction.timeStamp, transaction.signer, transaction.fee, transaction.deadline),
                byteSerializer.intToByte(numberOfBytesInAddress),
                byteSerializer.stringToBytes(transaction.rentalFeeSink),
                byteSerializer.longToByte(transaction.rentalFee),
                byteSerializer.intToByte(transaction.newPart.length()),
                byteSerializer.stringToBytes(transaction.newPart),
                parentNamespacePart
        );
    }

    @Override
    public byte[] data(ImportanceTransferTransaction transaction) {
        return concat(
                commonTransactionPart(transaction.type, transaction.version, transaction.timeStamp, transaction.signer, transaction.fee, transaction.deadline),
                byteSerializer.intToByte(transaction.action.mode),
                byteSerializer.intToByte(numberOfBytesInPublicKey),
                hexConverter.getBytes(transaction.remoteAccount)
        );
    }

    @Override
    public byte[] data(MosaicDefinitionCreationTransaction transaction) {
        byte[] mosaicDefinitionData = mosaicDefinitionData(transaction.mosaicDefinition);
        return concat(
                commonTransactionPart(transaction.type, transaction.version, transaction.timeStamp, transaction.signer, transaction.fee, transaction.deadline),
                byteSerializer.intToByte(mosaicDefinitionData.length),
                mosaicDefinitionData,
                byteSerializer.intToByte(numberOfBytesInAddress),
                byteSerializer.stringToBytes(transaction.creationFeeSink),
                byteSerializer.longToByte(transaction.creationFee)
        );
    }

    @Override
    public byte[] data(MosaicSupplyChangeTransaction transaction) {
        byte[] mosaicIdData = mosaicIdData(transaction.mosaicId);
        return concat(
                commonTransactionPart(transaction.type, transaction.version, transaction.timeStamp, transaction.signer, transaction.fee, transaction.deadline),
                byteSerializer.intToByte(mosaicIdData.length),
                mosaicIdData,
                byteSerializer.intToByte(transaction.supplyType.type),
                byteSerializer.longToByte(transaction.delta)
        );
    }

    @Override
    public byte[] dataMultisigTransfer(MultisigTransaction<Transaction> transaction) {
        return concat(
                commonTransactionPart(transaction.type, transaction.version, transaction.timeStamp, transaction.signer, transaction.fee, transaction.deadline),
                otherTransactionPart(transaction.otherTrans)
        );
    }

    @Override
    public byte[] dataMultisigProvisionNamespace(MultisigTransaction<ProvisionNamespaceTransaction> transaction) {
        byte[] data = data(transaction.otherTrans);
        return concat(
                commonTransactionPart(transaction.type, transaction.version, transaction.timeStamp, transaction.signer, transaction.fee, transaction.deadline),
                byteSerializer.intToByte(data.length),
                data
        );
    }

    @Override
    public byte[] dataMultisigMosaicCreation(MultisigTransaction<MosaicDefinitionCreationTransaction> transaction) {
        byte[] data = data(transaction.otherTrans);
        return concat(
                commonTransactionPart(transaction.type, transaction.version, transaction.timeStamp, transaction.signer, transaction.fee, transaction.deadline),
                byteSerializer.intToByte(data.length),
                data
        );
    }

    private byte[] mosaicDefinitionData(MosaicDefinition mosaicDefinition) {
        byte[] mosaicIdData = mosaicIdData(mosaicDefinition.id);
        byte[] levyData = levyData(mosaicDefinition.levy);
        return concat(
                byteSerializer.intToByte(numberOfBytesInPublicKey),
                hexConverter.getBytes(mosaicDefinition.creator),
                byteSerializer.intToByte(mosaicIdData.length),
                mosaicIdData,
                byteSerializer.intToByte(mosaicDefinition.description.length()),
                byteSerializer.stringToBytes(mosaicDefinition.description),
                mosaicPropertiesData(mosaicDefinition.properties),
                byteSerializer.intToByte(levyData.length),
                levyData
        );
    }

    private byte[] mosaicIdData(MosaicId mosaicId) {
        return concat(
                byteSerializer.intToByte(mosaicId.namespaceId.length()),
                byteSerializer.stringToBytes(mosaicId.namespaceId),
                byteSerializer.intToByte(mosaicId.name.length()),
                byteSerializer.stringToBytes(mosaicId.name)
        );
    }

    private byte[] levyData(Levy levy) {
        if (levy == null) {
            return new byte[0];
        }
        byte[] mosaicIdData = mosaicIdData(levy.mosaicId);
        return concat(
                byteSerializer.intToByte(levy.type),
                byteSerializer.intToByte(numberOfBytesInAddress),
                byteSerializer.stringToBytes(levy.recipient),
                byteSerializer.intToByte(mosaicIdData.length),
                mosaicIdData,
                byteSerializer.longToByte(levy.fee)
        );
    }

    private byte[] mosaicPropertiesData(List<MosaicProperty> mosaicProperties) {
        return mosaicProperties.stream()
                .map(this::mosaicPropertyData)
                .reduce(byteSerializer.intToByte(mosaicProperties.size()), (b1, b2) -> concat(b1, b2));
    }

    private byte[] mosaicPropertyData(MosaicProperty mosaicProperty) {
        byte[] propertyStructure = concat(
                byteSerializer.intToByte(mosaicProperty.name.length()),
                byteSerializer.stringToBytes(mosaicProperty.name),
                byteSerializer.intToByte(mosaicProperty.value.length()),
                byteSerializer.stringToBytes(mosaicProperty.value)
        );
        return concat(
                byteSerializer.intToByte(propertyStructure.length),
                propertyStructure
        );
    }

    private byte[] cosigningTransactionPart(Transaction transaction) {
        if (transaction.otherHash == null) {
            return new byte[0];
        }
        return concat(
                byteSerializer.intToByte(numberOfBytesInHash + 4),
                byteSerializer.intToByte(numberOfBytesInHash),
                hexConverter.getBytes(transaction.otherHash.data),
                byteSerializer.intToByte(numberOfBytesInAddress),
                byteSerializer.stringToBytes(transaction.otherAccount)
        );
    }

    private byte[] commonTransactionPart(int type, int version, int timeStamp, String signer, long fee, int deadline) {
        return concat(
                byteSerializer.intToByte(type),
                byteSerializer.intToByte(version),
                byteSerializer.intToByte(timeStamp),
                byteSerializer.intToByte(numberOfBytesInPublicKey),
                hexConverter.getBytes(signer),
                byteSerializer.longToByte(fee),
                byteSerializer.intToByte(deadline));
    }

    private byte[] otherTransactionPart(Transaction transaction) {
        if (transaction == null) {
            return new byte[0];
        }
        byte[] transactionData = data(transaction);
        return concat(byteSerializer.intToByte(transactionData.length), transactionData);
    }

    private byte[] getAggregateModificationPart(Transaction transaction) {
        if (isEmpty(transaction.modifications)) {
            return new byte[0];
        }

        byte[] modificationsData = transaction.modifications.stream()
                .map(byteSerializer::modificationToByte)
                .reduce(byteSerializer.intToByte(transaction.modifications.size()), (b1, b2) -> concat(b1, b2));

        return concat(
                modificationsData,
                byteSerializer.intToByte(lengthOfMinCosignatoriesStructure),
                byteSerializer.intToByte(transaction.minCosignatories.relativeChange)
        );
    }

    private byte[] getTransferPartData(Transaction transaction) {
        byte[] messagePart = byteSerializer.messageToByte(transaction.message);
        return transaction.recipient == null ? new byte[0] : concat(
                byteSerializer.intToByte(numberOfBytesInAddress),
                byteSerializer.stringToBytes(transaction.recipient),
                byteSerializer.longToByte(transaction.amount),
                byteSerializer.intToByte(messagePart.length),
                messagePart);
    }

    private byte[] getAllMosaicsBytes(Transaction transaction) {
        byte[] emptyArray = new byte[0];
        return isEmpty(transaction.mosaics) ?
                emptyArray :
                transaction.mosaics.stream()
                        .map(byteSerializer::mosaicToByte)
                        .reduce(byteSerializer.intToByte(transaction.mosaics.size()), (b1, b2) -> concat(b1, b2));
    }
}
