package com.github.rosklyar.client.transaction.encode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultSignerTest {

    @Test
    void signerShouldReturnPublicKeyFromPrivate() {
        DefaultSigner signer = new DefaultSigner("00b10c0502d087db1309972e2cd533f5dc259514c5ed837e0c7e22ccc58822349d");
        assertEquals("c859557dc42e6e8bce9551e641c12175df5ff725222e1855b055128d3212f313", signer.publicKey());
    }
}