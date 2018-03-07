package io.nem.client.transaction.encode;

import io.nem.client.common.Message;
import io.nem.client.common.MosaicTransfer;
import io.nem.client.common.multisig.Modification;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.primitives.Bytes.concat;
import static java.nio.ByteBuffer.allocate;
import static java.nio.ByteOrder.LITTLE_ENDIAN;
import static java.nio.charset.Charset.forName;

public class DefaultByteSerializer implements ByteSerializer {

    private final int numberOfBytesInPublicKey = 32;
    private final HexConverter hexConverter;

    public DefaultByteSerializer(HexConverter hexConverter) {
        this.hexConverter = hexConverter;
    }

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
        return concat(intToByte(message.type), intToByte(payload.length), payload);
    }

    @Override
    public byte[] mosaicToByte(MosaicTransfer mosaicTransfer) {
        byte[] namespaceIdBytes = getUTF8Bytes(mosaicTransfer.mosaicId.namespaceId);
        byte[] mosaicNameBytes = getUTF8Bytes(mosaicTransfer.mosaicId.name);
        byte[] quantityBytes = longToByte(mosaicTransfer.quantity);
        byte[] mosaicIdStructure = concat(intToByte(namespaceIdBytes.length), namespaceIdBytes, intToByte(mosaicNameBytes.length), mosaicNameBytes);
        byte[] mosaicStructure = concat(intToByte(mosaicIdStructure.length), mosaicIdStructure, quantityBytes);
        return concat(intToByte(mosaicStructure.length), mosaicStructure);
    }

    @Override
    public byte[] modificationToByte(Modification modification) {
        byte[] modificationStructureData = concat(
                intToByte(modification.modificationType),
                intToByte(numberOfBytesInPublicKey),
                hexConverter.getBytes(modification.cosignatoryAccount)
        );
        return concat(intToByte(modificationStructureData.length), modificationStructureData);
    }

    private byte[] getUTF8Bytes(String address) {
        return address.getBytes(forName("UTF-8"));
    }
}
