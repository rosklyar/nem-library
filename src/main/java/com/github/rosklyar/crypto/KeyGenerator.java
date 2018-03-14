package com.github.rosklyar.crypto;

public interface KeyGenerator {
    KeyPair generateKeyPair();

    PublicKey derivePublicKey(PrivateKey privateKey);
}
