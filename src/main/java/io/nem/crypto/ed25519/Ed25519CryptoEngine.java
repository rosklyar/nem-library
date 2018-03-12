package io.nem.crypto.ed25519;

import io.nem.crypto.DsaSigner;
import io.nem.crypto.KeyAnalyzer;
import io.nem.crypto.KeyGenerator;
import io.nem.crypto.KeyPair;

/**
 * Class that wraps the Ed25519 specific implementation.
 */
public class Ed25519CryptoEngine {

    public static DsaSigner createDsaSigner(final KeyPair keyPair) {
        return new Ed25519DsaSigner(keyPair);
    }

    public static KeyGenerator createKeyGenerator() {
        return new Ed25519KeyGenerator();
    }

    public static KeyAnalyzer createKeyAnalyzer() {
        return new Ed25519KeyAnalyzer();
    }
}
