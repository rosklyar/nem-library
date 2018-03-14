package com.github.rosklyar.crypto;

import com.github.rosklyar.utils.HexEncoder;

import java.util.Arrays;

/**
 * Represents a public key.
 */
public class PublicKey {
    private final byte[] value;

    /**
     * Creates a new public key.
     *
     * @param bytes The raw public key value.
     */
    public PublicKey(final byte[] bytes) {
        this.value = bytes;
    }


    /**
     * Gets the raw public key value.
     *
     * @return The raw public key value.
     */
    public byte[] getRaw() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.value);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null || !(obj instanceof PublicKey)) {
            return false;
        }

        final PublicKey rhs = (PublicKey) obj;
        return Arrays.equals(this.value, rhs.value);
    }

    @Override
    public String toString() {
        return HexEncoder.getString(this.value);
    }
}
