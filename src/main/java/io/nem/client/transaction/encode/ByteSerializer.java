package io.nem.client.transaction.encode;

import io.nem.client.account.domain.Message;
import io.nem.client.transaction.domain.mosaic.MosaicTransfer;
import io.nem.client.transaction.domain.multisig.Modification;

public interface ByteSerializer {
    byte[] intToByte(int number);

    byte[] longToByte(long number);

    byte[] stringToBytes(String string);

    byte[] messageToByte(Message message);

    byte[] mosaicToByte(MosaicTransfer mosaicTransfer);

    byte[] modificationToByte(Modification modification);
}
