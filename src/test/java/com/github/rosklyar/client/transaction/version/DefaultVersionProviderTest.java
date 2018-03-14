package com.github.rosklyar.client.transaction.version;

import org.junit.jupiter.api.Test;

import static com.github.rosklyar.client.transaction.TransactionType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultVersionProviderTest {

    private final VersionProvider versionProvider = new DefaultVersionProvider();

    @Test
    void returnCorrectVersion() {
        Network test = new Network(0x98, "TAMESPACEWH4MKFMBCVFERDPOOP4FK7MTDJEYP35", "TBMOSAICOD4F54EE5CDMR23CCBGOAM2XSJBR5OLC");
        assertEquals(-1744830463, versionProvider.version(test, TRANSFER_NEM));
        assertEquals(-1744830462, versionProvider.version(test, TRANSFER_MOSAICS));
        assertEquals(-1744830462, versionProvider.version(test, MULTISIG_AGGREGATE_MODIFICATION));
    }
}