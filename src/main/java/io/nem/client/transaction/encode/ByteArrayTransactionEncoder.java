package io.nem.client.transaction.encode;

import io.nem.client.common.Transaction;

import static com.google.common.primitives.Bytes.concat;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public class ByteArrayTransactionEncoder implements TransactionEncoder {

    private final ByteSerializer byteSerializer;
    private final HexConverter hexConverter;
    private final int numberOfBytesInPublicKey = 32;
    private final int numberOfBytesInRecipient = 40;
    private final int lengthOfMinCosignatoriesStructure = 4;

    public ByteArrayTransactionEncoder(ByteSerializer byteSerializer, HexConverter hexEncoder) {
        this.byteSerializer = byteSerializer;
        this.hexConverter = hexEncoder;
    }


    @Override
    public byte[] data(Transaction transaction) {
        byte[] transferPart = getTransferPartData(transaction);
        byte[] mosaicsPart = getAllMosaicsBytes(transaction);
        byte[] multisigAccountCreationPart = getAggregateModificationPart(transaction);
        return
                concat(
                        byteSerializer.intToByte(transaction.type),
                        byteSerializer.intToByte(transaction.version),
                        byteSerializer.intToByte(transaction.timeStamp),
                        byteSerializer.intToByte(numberOfBytesInPublicKey),
                        hexConverter.getBytes(transaction.signer),
                        byteSerializer.longToByte(transaction.fee),
                        byteSerializer.intToByte(transaction.deadline),
                        transferPart,
                        mosaicsPart,
                        multisigAccountCreationPart
                );
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
                byteSerializer.intToByte(numberOfBytesInRecipient),
                byteSerializer.addressToByte(transaction.recipient),
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
