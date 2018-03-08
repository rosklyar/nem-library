package io.nem.client.transaction.encode;

import io.nem.client.common.Message;
import io.nem.client.common.transaction.mosaic.MosaicTransfer;
import io.nem.client.common.transaction.multisig.Modification;

public interface ByteSerializer {
    byte[] intToByte(int number);

    byte[] longToByte(long number);

    byte[] stringToBytes(String string);

    byte[] messageToByte(Message message);

    byte[] mosaicToByte(MosaicTransfer mosaicTransfer);

    byte[] modificationToByte(Modification modification);
}
