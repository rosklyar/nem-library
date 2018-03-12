package io.nem.client.transaction.encode;

import io.nem.utils.HexEncoder;

public class DefaultHexConverter implements HexConverter {

    @Override
    public byte[] getBytes(String hexString) {
        return HexEncoder.getBytes(hexString);
    }

    @Override
    public String getString(byte[] bytes) {
        return HexEncoder.getString(bytes);
    }
}
