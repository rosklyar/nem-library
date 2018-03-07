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
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionClientTest {

    private final TransactionClient transactionClient = new DefaultNemClientFactory().createTransactionClient("http://153.122.112.137:7890", TEST);

    @Test
    @Disabled
    void makeNemTransfer() {
        NemAnnounceResult nemAnnounceResult = transactionClient.transferNem(
                "fcf0dadc958510dca65651df81aa22c82b2bfe5b29bf8dfb92816bc5f1f11a54",
                "TD4F657BT4MDBAJXMOZR37MN5T2CRXQW66MPSONE",
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

    @Test
    @Disabled
    void createMultisigAccount() {
        NemAnnounceResult nemAnnounceResult = transactionClient.createMultisigAccount(
                "1ff5c6eaabedc8c95510635cabf75837e72da536127742f32873a7cc096ca74b",
                3600,
                newArrayList(
                        "7f38d345234f7cdb12acc6f0a0251804362686d66a321f83568f704feef7adae",
                        "6bbc0fe4e79fe1324f6877c573fdcba8d312883af87fec7e436b2e3894ad8545"

                ),
                2
        );
        assertEquals(1, nemAnnounceResult.code);
    }

    @Test
    @Disabled
    void multisigNemTransfer() {
        NemAnnounceResult nemAnnounceResult = transactionClient.multisigTransferNem(
                "2ee2a08ad2ebc1371330c9977d15e52f482aa514554e085bec5ed076e8b11581",
                "d9728f3002d6292d54aa2e5c75f1e72bb7f7b800645c46e91171935285e77747",
                "TCHCCSUCSBE2OSYXLISPYGOVLOVZYCYYI5V73K4Y",
                1000000,
                "test multisig",
                3600
        );
        assertNotNull(nemAnnounceResult.innerTransactionHash);

        NemAnnounceResult cosignNemAnnounceResult = transactionClient.cosignTransaction("fcf0dadc958510dca65651df81aa22c82b2bfe5b29bf8dfb92816bc5f1f11a54", nemAnnounceResult.innerTransactionHash.data, "TD4F657BT4MDBAJXMOZR37MN5T2CRXQW66MPSONE", 3600);
        assertEquals(1, cosignNemAnnounceResult.code);
    }

    @Test
    @Disabled
    void multisigMosaicTransfer() {
        NemAnnounceResult nemAnnounceResult = transactionClient.multisigTransferMosaics(
                "2ee2a08ad2ebc1371330c9977d15e52f482aa514554e085bec5ed076e8b11581",
                "d9728f3002d6292d54aa2e5c75f1e72bb7f7b800645c46e91171935285e77747",
                "TCHCCSUCSBE2OSYXLISPYGOVLOVZYCYYI5V73K4Y",
                "test multisig",
                3600,
                newArrayList(new MosaicTransfer(new MosaicId("library", "testcoin"), 3000)),
                2
        );
        assertEquals(1, nemAnnounceResult.code);
    }
}