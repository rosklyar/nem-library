package io.nem.crypto;

public interface KeyGenerator {
    KeyPair generateKeyPair();

    PublicKey derivePublicKey(PrivateKey privateKey);
}
