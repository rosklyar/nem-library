package com.github.rosklyar.crypto;

import com.github.rosklyar.utils.ExceptionUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.Security;

/**
 * Static class that exposes hash functions.
 */
public class Hashes {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Performs a SHA3-512 hash of the concatenated inputs.
     *
     * @param inputs The byte arrays to concatenate and hash.
     * @return The hash of the concatenated inputs.
     * @throws CryptoException if the hash operation failed.
     */
    public static byte[] sha3_512(final byte[]... inputs) {
        return hash("Keccak-512", inputs);
    }

    private static byte[] hash(final String algorithm, final byte[]... inputs) {
        return ExceptionUtils.propagate(() -> {
            final MessageDigest digest = MessageDigest.getInstance(algorithm, "BC");

            for (final byte[] input : inputs) {
                digest.update(input);
            }

            return digest.digest();
        }, CryptoException::new);
    }
}
