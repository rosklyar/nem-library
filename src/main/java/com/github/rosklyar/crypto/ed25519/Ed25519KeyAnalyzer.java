package com.github.rosklyar.crypto.ed25519;

import com.github.rosklyar.crypto.KeyAnalyzer;
import com.github.rosklyar.crypto.PublicKey;

/**
 * Implementation of the key analyzer for Ed25519.
 */
public class Ed25519KeyAnalyzer implements KeyAnalyzer {
	private static final int COMPRESSED_KEY_SIZE = 32;

	@Override
	public boolean isKeyCompressed(final PublicKey publicKey) {
		return COMPRESSED_KEY_SIZE == publicKey.getRaw().length;
	}
}
