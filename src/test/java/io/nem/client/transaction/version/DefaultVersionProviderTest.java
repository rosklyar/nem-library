package io.nem.client.transaction.version;

import org.junit.jupiter.api.Test;

import static io.nem.client.transaction.TransactionType.*;
import static io.nem.client.transaction.version.Network.TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultVersionProviderTest {

    private final VersionProvider versionProvider = new DefaultVersionProvider();

    @Test
    void returnCorrectVersion() {
        assertEquals(-1744830463, versionProvider.version(TEST, TRANSFER_NEM));
        assertEquals(-1744830462, versionProvider.version(TEST, TRANSFER_MOSAICS));
        assertEquals(-1744830462, versionProvider.version(TEST, MULTISIG_AGGREGATE_MODIFICATION));
    }
}