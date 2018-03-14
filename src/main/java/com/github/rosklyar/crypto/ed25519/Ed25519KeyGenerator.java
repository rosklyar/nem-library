package com.github.rosklyar.crypto.ed25519;

import com.github.rosklyar.crypto.PublicKey;
import com.github.rosklyar.utils.ArrayUtils;
import com.github.rosklyar.crypto.KeyGenerator;
import com.github.rosklyar.crypto.KeyPair;
import com.github.rosklyar.crypto.PrivateKey;
import com.github.rosklyar.crypto.ed25519.arithmetic.Ed25519EncodedFieldElement;
import com.github.rosklyar.crypto.ed25519.arithmetic.Ed25519GroupElement;

import java.security.SecureRandom;

import static com.github.rosklyar.crypto.ed25519.arithmetic.Ed25519Group.BASE_POINT;

public class Ed25519KeyGenerator implements KeyGenerator {
    private final SecureRandom random;

    public Ed25519KeyGenerator() {
        this.random = new SecureRandom();
    }

    @Override
    public KeyPair generateKeyPair() {
        final byte[] seed = new byte[32];
        this.random.nextBytes(seed);

        PrivateKey privateKey = new PrivateKey(ArrayUtils.toBigInteger(seed));

        return new KeyPair(privateKey);
    }

    @Override
    public PublicKey derivePublicKey(final PrivateKey privateKey) {
        final Ed25519EncodedFieldElement a = Ed25519Utils.prepareForScalarMultiply(privateKey);

        final Ed25519GroupElement pubKey = BASE_POINT.scalarMultiply(a);

        return new PublicKey(pubKey.encode().getRaw());
    }
}
