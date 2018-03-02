package io.nem.client.transaction.encode;

public interface Signer {
    String sign(byte[] data);
    String publicKey();
}
