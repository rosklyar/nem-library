package com.github.rosklyar.client.transaction.encode;

import com.github.rosklyar.client.account.domain.Message;
import com.github.rosklyar.client.transaction.domain.mosaic.MosaicTransfer;
import com.github.rosklyar.client.transaction.domain.multisig.Modification;

public interface ByteSerializer {
    byte[] intToByte(int number);

    byte[] longToByte(long number);

    byte[] stringToBytes(String string);

    byte[] messageToByte(Message message);

    byte[] mosaicToByte(MosaicTransfer mosaicTransfer);

    byte[] modificationToByte(Modification modification);
}
