package com.github.rosklyar.client.transaction.encode;

public interface Signer {
    String sign(byte[] data);
    String publicKey();
}
