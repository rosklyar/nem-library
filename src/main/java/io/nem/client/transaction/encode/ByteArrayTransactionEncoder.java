package io.nem.client.transaction.encode;

import com.google.common.primitives.Bytes;
import io.nem.client.transaction.Transaction;

public class ByteArrayTransactionEncoder implements TransactionEncoder {

    private final ByteSerializer byteSerializer;
    private final HexConverter hexConverter;
    private final int numberOfBytesInPublicKey = 32;
    private final int numberOfBytesInRecipient = 40;

    public ByteArrayTransactionEncoder(ByteSerializer byteSerializer, HexConverter hexEncoder) {
        this.byteSerializer = byteSerializer;
        this.hexConverter = hexEncoder;
    }


    @Override
    public byte[] data(Transaction transaction) {
        byte[] message = byteSerializer.messageToByte(transaction.message);
        return
                Bytes.concat(
                        byteSerializer.intToByte(transaction.type),
                        byteSerializer.intToByte(transaction.version),
                        byteSerializer.intToByte(transaction.timeStamp),
                        byteSerializer.intToByte(numberOfBytesInPublicKey),
                        hexConverter.getBytes(transaction.signer),
                        byteSerializer.longToByte(transaction.fee),
                        byteSerializer.intToByte(transaction.deadline),
                        byteSerializer.intToByte(numberOfBytesInRecipient),
                        byteSerializer.addressToByte(transaction.recipient),
                        byteSerializer.longToByte(transaction.amount),
                        byteSerializer.intToByte(message.length),
                        message
                );
    }
}
