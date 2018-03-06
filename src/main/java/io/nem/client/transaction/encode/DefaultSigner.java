package io.nem.client.transaction.encode;

import org.nem.core.crypto.DsaSigner;
import org.nem.core.crypto.KeyPair;

import static org.nem.core.crypto.CryptoEngines.defaultEngine;
import static org.nem.core.crypto.PrivateKey.fromHexString;

public class DefaultSigner implements Signer {

    private final DsaSigner dsaSigner;
    private final KeyPair cryptoKeyPair;

    public DefaultSigner(String hexString) {
        cryptoKeyPair = new KeyPair(fromHexString(hexString));
        dsaSigner = defaultEngine().createDsaSigner(cryptoKeyPair);
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
