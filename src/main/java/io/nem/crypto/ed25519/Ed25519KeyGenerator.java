package io.nem.crypto.ed25519;

import io.nem.crypto.KeyGenerator;
import io.nem.crypto.KeyPair;
import io.nem.crypto.PrivateKey;
import io.nem.crypto.PublicKey;
import io.nem.crypto.ed25519.arithmetic.Ed25519EncodedFieldElement;
import io.nem.crypto.ed25519.arithmetic.Ed25519GroupElement;
import io.nem.utils.ArrayUtils;

import java.security.SecureRandom;

import static io.nem.crypto.ed25519.Ed25519Utils.prepareForScalarMultiply;
import static io.nem.crypto.ed25519.arithmetic.Ed25519Group.BASE_POINT;

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
        final Ed25519EncodedFieldElement a = prepareForScalarMultiply(privateKey);

        final Ed25519GroupElement pubKey = BASE_POINT.scalarMultiply(a);

        return new PublicKey(pubKey.encode().getRaw());
    }
}
