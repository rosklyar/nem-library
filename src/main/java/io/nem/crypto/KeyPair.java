package io.nem.crypto;

import static io.nem.crypto.ed25519.Ed25519CryptoEngine.createKeyAnalyzer;
import static io.nem.crypto.ed25519.Ed25519CryptoEngine.createKeyGenerator;

public class KeyPair {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;


    public KeyPair(final PrivateKey privateKey) {
        this(privateKey, createKeyGenerator().derivePublicKey(privateKey));
    }


    private KeyPair(final PrivateKey privateKey, final PublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;

        if (!createKeyAnalyzer().isKeyCompressed(this.publicKey)) {
            throw new IllegalArgumentException("publicKey must be in compressed form");
        }
    }

    public static KeyPair random() {
        KeyPair pair = createKeyGenerator().generateKeyPair();
        return new KeyPair(pair.getPrivateKey(), pair.getPublicKey());
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    public boolean hasPrivateKey() {
        return null != this.privateKey;
    }
}