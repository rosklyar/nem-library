package io.nem.crypto;

/**
 * Interface for generating keys.
 */
public interface KeyGenerator {

    /**
     * Derives a public key from a private key.
     *
     * @param privateKey the private key.
     * @return The public key.
     */
    PublicKey derivePublicKey(final PrivateKey privateKey);
}
