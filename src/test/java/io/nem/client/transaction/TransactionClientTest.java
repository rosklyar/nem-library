package io.nem.client.transaction;

import io.nem.client.DefaultNemClientFactory;
import io.nem.client.common.MosaicId;
import io.nem.client.common.MosaicTransfer;
import io.nem.client.transaction.response.NemAnnounceResult;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.google.common.collect.Lists.newArrayList;
import static io.nem.client.transaction.version.Network.TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionClientTest {

    private final TransactionClient transactionClient = new DefaultNemClientFactory().createTransactionClient("http://153.122.112.137:7890", TEST);

    @Test
    @Disabled
    void makeNemTransfer() {
        NemAnnounceResult nemAnnounceResult = transactionClient.transferNem(
                "fcf0dadc958510dca65651df81aa22c82b2bfe5b29bf8dfb92816bc5f1f11a54",
                "TDIOE7BZR4J3DXNJ4SL6WOE4HM3QUI3PVUWW3YI3",
                1000000L,
                "test nem",
                3600
        );
        assertEquals(1, nemAnnounceResult.code);
    }

    @Test
    @Disabled
    void makeMosaicTransfer() {
        NemAnnounceResult nemAnnounceResult = transactionClient.transferMosaics(
                "fcf0dadc958510dca65651df81aa22c82b2bfe5b29bf8dfb92816bc5f1f11a54",
                "TDIOE7BZR4J3DXNJ4SL6WOE4HM3QUI3PVUWW3YI3",
                "test mosaic",
                3600,
                newArrayList(new MosaicTransfer(new MosaicId("library", "testcoin"), 3000)),
                2
        );
        assertEquals(1, nemAnnounceResult.code);
    }
}