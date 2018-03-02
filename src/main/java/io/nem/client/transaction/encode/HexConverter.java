package io.nem.client.transaction.encode;

public interface HexConverter {
    byte[] getBytes(String hexString);

    String getString(byte[] bytes);
}
