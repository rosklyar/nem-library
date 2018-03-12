package io.nem.client.transaction.encode;

import io.nem.crypto.DsaSigner;
import io.nem.crypto.KeyPair;
import io.nem.crypto.ed25519.Ed25519CryptoEngine;

import static io.nem.crypto.PrivateKey.fromHexString;

public class DefaultSigner implements Signer {

    private final DsaSigner dsaSigner;
    private final KeyPair cryptoKeyPair;

    public DefaultSigner(String hexString) {
        cryptoKeyPair = new KeyPair(fromHexString(hexString));
        dsaSigner = Ed25519CryptoEngine.createDsaSigner(cryptoKeyPair);
    }

    @Override
    public String sign(byte[] data) {
        return dsaSigner.sign(data).toString();
    }

    @Override
    public String publicKey() {
        return cryptoKeyPair.getPublicKey().toString();
    }
}
