package com.github.rosklyar.client.transaction.encode;

import com.github.rosklyar.crypto.DsaSigner;
import com.github.rosklyar.crypto.KeyPair;
import com.github.rosklyar.crypto.ed25519.Ed25519CryptoEngine;

import static com.github.rosklyar.crypto.PrivateKey.fromHexString;

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
