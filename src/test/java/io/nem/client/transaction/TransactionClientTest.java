package io.nem.client.transaction;

import io.nem.client.DefaultNemClientFactory;
import io.nem.client.transaction.response.NemAnnounceResult;
import org.junit.jupiter.api.Test;

import static io.nem.client.transaction.version.Network.TEST;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionClientTest {

    private final TransactionClient transactionClient = new DefaultNemClientFactory().createTransactionClient("http://5.128.82.36:7890", TEST);

    @Test
    void makeNemTransfer() {
        NemAnnounceResult nemAnnounceResult = transactionClient.transferNem(
                "fcf0dadc958510dca65651df81aa22c82b2bfe5b29bf8dfb92816bc5f1f11a54",
                "TDIOE7BZR4J3DXNJ4SL6WOE4HM3QUI3PVUWW3YI3",
                1.0,
                "test1",
                3600
        );
        assertNotNull(nemAnnounceResult);
    }
}