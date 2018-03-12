package io.nem.crypto.ed25519;

import io.nem.crypto.KeyGenerator;
import io.nem.crypto.PrivateKey;
import io.nem.crypto.PublicKey;
import io.nem.crypto.ed25519.arithmetic.Ed25519EncodedFieldElement;
import io.nem.crypto.ed25519.arithmetic.Ed25519Group;
import io.nem.crypto.ed25519.arithmetic.Ed25519GroupElement;

/**
 * Implementation of the key generator for Ed25519.
 */
public class Ed25519KeyGenerator implements KeyGenerator {

    @Override
    public PublicKey derivePublicKey(final PrivateKey privateKey) {
        final Ed25519EncodedFieldElement a = Ed25519Utils.prepareForScalarMultiply(privateKey);

        // a * base point is the public key.
        final Ed25519GroupElement pubKey = Ed25519Group.BASE_POINT.scalarMultiply(a);

        // verification of signatures will be about twice as fast when pre-calculating
        // a suitable table of group elements.
        return new PublicKey(pubKey.encode().getRaw());
    }
}
