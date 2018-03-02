package io.nem.client.transaction.encode;

import io.nem.client.common.Message;

public interface ByteSerializer {
    byte[] intToByte(int number);

    byte[] longToByte(long number);

    byte[] addressToByte(String string);

    byte[] messageToByte(Message message);
}
