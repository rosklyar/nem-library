package com.github.rosklyar.client.transaction.encode;

import com.github.rosklyar.utils.HexEncoder;

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
