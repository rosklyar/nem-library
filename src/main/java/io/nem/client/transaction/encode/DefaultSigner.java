package io.nem.client.transaction.encode;

import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.DsaSigner;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;

import static org.nem.core.utils.ArrayUtils.toBigInteger;

public class DefaultSigner implements Signer {

    private final DsaSigner dsaSigner;
    private final KeyPair cryptoKeyPair;

    public DefaultSigner(byte[] privateKey) {
        cryptoKeyPair = new KeyPair(new PrivateKey(toBigInteger(privateKey)));
        dsaSigner = CryptoEngines.defaultEngine().createDsaSigner(cryptoKeyPair);
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
