package com.github.rosklyar.crypto;

import com.github.rosklyar.utils.ArrayUtils;
import com.github.rosklyar.utils.HexEncoder;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * A EC signature.
 */
public class Signature {

    private final byte[] r;
    private final byte[] s;

    /**
     * Creates a new signature.
     *
     * @param r The binary representation of r.
     * @param s The binary representation of s.
     */
    public Signature(final byte[] r, final byte[] s) {
        if (32 != r.length || 32 != s.length) {
            throw new IllegalArgumentException("binary signature representation of r and s must both have 32 bytes length");
        }

        this.r = r;
        this.s = s;
    }

    /**
     * Gets the s-part of the signature.
     *
     * @return The s-part of the signature.
     */
    public BigInteger getS() {
        return ArrayUtils.toBigInteger(this.s);
    }

    /**
     * Gets a little-endian 64-byte representation of the signature.
     *
     * @return a little-endian 64-byte representation of the signature
     */
    public byte[] getBytes() {
        return ArrayUtils.concat(this.r, this.s);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.r) ^ Arrays.hashCode(this.s);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null || !(obj instanceof Signature)) {
            return false;
        }

        final Signature rhs = (Signature) obj;
        return 1 == ArrayUtils.isEqualConstantTime(this.r, rhs.r) && 1 == ArrayUtils.isEqualConstantTime(this.s, rhs.s);
    }

    //endregion

    @Override
    public String toString() {
        return HexEncoder.getString(this.getBytes());
    }
}