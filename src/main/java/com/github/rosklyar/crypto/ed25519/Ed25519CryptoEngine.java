package com.github.rosklyar.crypto.ed25519;

import com.github.rosklyar.crypto.DsaSigner;
import com.github.rosklyar.crypto.KeyAnalyzer;
import com.github.rosklyar.crypto.KeyGenerator;
import com.github.rosklyar.crypto.KeyPair;

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
