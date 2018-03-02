package io.nem.client.transaction.encode;

import com.google.common.primitives.Bytes;
import io.nem.client.common.Message;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.nio.ByteBuffer.allocate;
import static java.nio.ByteOrder.LITTLE_ENDIAN;
import static java.nio.charset.Charset.forName;

public class DefaultByteSerializer implements ByteSerializer {

    @Override
    public byte[] intToByte(int number) {
        return allocate(4)
                .order(LITTLE_ENDIAN)
                .putInt(number)
                .array();
    }

    @Override
    public byte[] longToByte(long number) {
        return allocate(8)
                .order(LITTLE_ENDIAN)
                .putLong(number)
                .array();
    }

    @Override
    public byte[] addressToByte(String address) {
        return getUTF8Bytes(address);
    }

    @Override
    public byte[] messageToByte(Message message) {
        if (message == null || isNullOrEmpty(message.payload)) {
            return new byte[0];
        }
        byte[] payload = getUTF8Bytes(message.payload);
        return Bytes.concat(intToByte(message.type), intToByte(payload.length), payload);
    }

    private byte[] getUTF8Bytes(String address) {
        return address.getBytes(forName("UTF-8"));
    }
}
