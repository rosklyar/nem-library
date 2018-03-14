package com.github.rosklyar.client.account;

import com.github.rosklyar.client.DefaultNemClientFactory;
import com.github.rosklyar.client.account.domain.*;
import com.github.rosklyar.client.account.domain.harvest.HarvestInfo;
import com.github.rosklyar.client.account.domain.history.HistoryResponse;
import com.github.rosklyar.client.account.domain.importance.ImportanceResponse;
import com.github.rosklyar.client.account.domain.mosaic.MosaicsResponse;
import com.github.rosklyar.client.account.domain.mosaic.OwnedMosaic;
import com.github.rosklyar.client.account.domain.mosaic.OwnedMosaicsResponse;
import com.github.rosklyar.client.account.domain.namespace.NamespacesResponse;
import com.github.rosklyar.client.account.domain.transaction.Transactions;
import com.github.rosklyar.client.account.domain.transaction.UnconfirmedTransactions;
import com.github.rosklyar.client.transaction.domain.mosaic.MosaicId;
import feign.FeignException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.rosklyar.client.account.domain.AccountMetaData.RemoteStatus.INACTIVE;
import static com.github.rosklyar.client.account.domain.AccountMetaData.Status.LOCKED;
import static com.netflix.config.ConfigurationManager.getConfigInstance;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class AccountClientTest {

    private AccountClient accountClient = new DefaultNemClientFactory().createAccountClient("accountApi");
    private AccountClient simpleAccountClient = new DefaultNemClientFactory().simpleAccountClient("http://153.122.112.137:7890");

    private final String address = "TAVNDWBJFJHZYD3YYWJPDQ345ZAZIYEB2LJXSG65";
    private final String cosignatoryAddress = "TBNDMABIECCN6EQY5WVNJZMCXAUVTN7RKGZH4CP4";
    private final String publicKey = "5f5f2bce1a0911aeec9a594a9f8fc4a80cfa193f4525120f53360389074b9a51";
    private final String cosignatoryPublicKey = "0d81da60546ccb7b54b59ac8c1e8d2d0008c20bf76770a75b7bd5a853c26797b";
    private final String privateKey = "0476fd96242ac5ef6cb1b268887254c1a3089759556beb1ce660c0cb2c42bb27";

    @BeforeAll
    static void init() {
        getConfigInstance().setProperty("accountApi.ribbon.listOfServers", "153.122.112.137:7890");
        getConfigInstance().setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", 20000);
    }

    @Test
    @Disabled("only for local NIS nodes")
    void generate() {
        KeyPair keyPair = accountClient.generate();
        assertNotNull(keyPair.publicKey);
    }

    @Test
    void getAccount() {

        AccountMetaDataPair account = accountClient.getFromAddress(address);
        AccountMetaDataPair cosignatoryAccount = accountClient.getFromAddress(cosignatoryAddress);
        AccountMetaDataPair accountFromPublicKey = accountClient.getFromPublicKey(publicKey);

        AccountInfo accountInfo = AccountInfo.builder()
                .address(address)
                .balance(13000000)
                .vestedBalance(account.account.vestedBalance)
                .importance(0.0)
                .publicKey(publicKey)
                .harvestedBlocks(0)
                .build();
        AccountInfo cosignatory = AccountInfo.builder()
                .address(cosignatoryAddress)
                .balance(26000000)
                .vestedBalance(cosignatoryAccount.account.vestedBalance)
                .importance(0.0)
                .publicKey(cosignatoryPublicKey)
                .harvestedBlocks(0)
                .build();

        List<AccountInfo> cosignatoryOf = new ArrayList<>();
        cosignatoryOf.add(cosignatory);

        AccountMetaData meta = AccountMetaData.builder()
                .status(LOCKED)
                .remoteStatus(INACTIVE)
                .cosignatoryOf(cosignatoryOf)
                .cosignatories(new ArrayList<>())
                .build();

        assertEquals(new AccountMetaDataPair(accountInfo, meta), account);
        assertEquals(new AccountMetaDataPair(accountInfo, meta), accountFromPublicKey);
    }

    @Test
    void getForwardedAccount() {
        AccountMetaDataPair account = accountClient.getForwarded(address);
        assertEquals(account, simpleAccountClient.getForwarded(address));

        AccountMetaDataPair cosignatoryAccount = accountClient.getFromAddress(cosignatoryAddress);
        AccountMetaDataPair accountFromPublicKey = accountClient.getForwardedFromPublicKey(publicKey);

        AccountInfo accountInfo = AccountInfo.builder()
                .address(address)
                .balance(13000000)
                .vestedBalance(account.account.vestedBalance)
                .importance(0.0)
                .publicKey(publicKey)
                .harvestedBlocks(0)
                .build();
        AccountInfo cosignatory = AccountInfo.builder()
                .address(cosignatoryAddress)
                .balance(26000000)
                .vestedBalance(cosignatoryAccount.account.vestedBalance)
                .importance(0.0)
                .publicKey(cosignatoryPublicKey)
                .harvestedBlocks(0)
                .build();

        List<AccountInfo> cosignatoryOf = new ArrayList<>();
        cosignatoryOf.add(cosignatory);

        AccountMetaData meta = AccountMetaData.builder()
                .status(LOCKED)
                .remoteStatus(INACTIVE)
                .cosignatoryOf(cosignatoryOf)
                .cosignatories(new ArrayList<>())
                .build();


        assertEquals(new AccountMetaDataPair(accountInfo, meta), account);
        assertEquals(new AccountMetaDataPair(accountInfo, meta), accountFromPublicKey);
    }

    @Test
    void getStatus() {
        AccountMetaData status = accountClient.status(address);
        AccountMetaDataPair cosignatoryAccount = accountClient.getFromAddress(cosignatoryAddress);

        AccountInfo cosignatory = AccountInfo.builder()
                .address(cosignatoryAddress)
                .balance(26000000)
                .vestedBalance(cosignatoryAccount.account.vestedBalance)
                .importance(0.0)
                .publicKey(cosignatoryPublicKey)
                .harvestedBlocks(0)
                .build();

        List<AccountInfo> cosignatoryOf = new ArrayList<>();
        cosignatoryOf.add(cosignatory);

        AccountMetaData meta = AccountMetaData.builder()
                .status(LOCKED)
                .remoteStatus(INACTIVE)
                .cosignatoryOf(cosignatoryOf)
                .cosignatories(new ArrayList<>())
                .build();

        assertEquals(meta, status);
    }

    @Test
    void getIncomingTransactions() {
        Transactions transactions = accountClient.incomingTransactions(address);
        assertEquals(2, transactions.data.size());
        Transactions transactionsBeforeInputTransactionHash = accountClient.incomingTransactions(address, transactions.data.stream().findFirst().map(tr -> tr.meta.id).orElseThrow(RuntimeException::new));
        assertEquals(transactions.data.size() - 1, transactionsBeforeInputTransactionHash.data.size());
    }

    @Test
    void getOutgoingTransactions() {
        Transactions transactions = accountClient.outgoingTransactions(address);
        assertEquals(26000000, transactions.data.stream().mapToLong(pair -> pair.transaction.fee).sum());
        Transactions transactionsBeforeInputTransactionHash = accountClient.outgoingTransactions(address, transactions.data.stream().findFirst().map(tr -> tr.meta.id).orElseThrow(RuntimeException::new));
        assertEquals(transactions.data.size() - 1, transactionsBeforeInputTransactionHash.data.size());
    }

    @Test
    void getAllTransactions() {
        Transactions transactions = accountClient.allTransactions(address);
        assertEquals(55000000, transactions.data.stream().mapToLong(pair -> pair.transaction.fee).sum());
        Transactions transactionsBeforeInputTransactionHash = accountClient.allTransactions(address, transactions.data.stream().findFirst().map(tr -> tr.meta.id).orElseThrow(RuntimeException::new));
        assertEquals(transactions.data.size() - 1, transactionsBeforeInputTransactionHash.data.size());
    }

    @Test
    void getUnconfirmedTransactions() {
        UnconfirmedTransactions unconfirmedTransactions = accountClient.unconfirmedTransactions(address);
        assertEquals(0, unconfirmedTransactions.data.size());
    }

    @Test
    @Disabled("only for local NIS nodes")
    void getDecodedIncomingTransactions() {
        Transactions transactions = accountClient.incomingDecodedTransactions(new AccountPrivateKeyTransactionsPage(privateKey, null, null));
        assertEquals("", transactions.data.stream().findFirst().map(pair -> pair.transaction.message.payload).orElse(null));
    }

    @Test
    @Disabled("only for local NIS nodes")
    void getDecodedOutgoingTransactions() {
        Transactions transactions = accountClient.outgoingDecodedTransactions(new AccountPrivateKeyTransactionsPage(privateKey, null, null));
        assertEquals("", transactions.data.stream().findFirst().map(pair -> pair.transaction.message.payload).orElse(null));
    }

    @Test
    @Disabled("only for local NIS nodes")
    void getDecodedAllTransactions() {
        Transactions transactions = accountClient.allDecodedTransactions(new AccountPrivateKeyTransactionsPage(privateKey, null, null));
        assertEquals("", transactions.data.stream().findFirst().map(pair -> pair.transaction.message.payload).orElse(null));
    }

    @Test
    void getHarvests() {
        List<HarvestInfo> harvests = accountClient.harvests(address, null).data;
        assertEquals(0, harvests.size());
    }

    @Test
    @Disabled("test can fail with timeout")
    void getImportances() {
        ImportanceResponse importanceResponse = accountClient.importances();
        assumeTrue(importanceResponse.data.size() > 0);
        assertTrue(importanceResponse.data.stream().filter(info -> info.importance.isSet).findFirst().get().importance.score > 0);
    }

    @Test
    void getNamespaces() {
        NamespacesResponse namespaces = accountClient.namespaces(address, null, null, null);
        assertEquals(0, namespaces.data.size());
    }

    @Test
    void getMosaics() {
        MosaicsResponse mosaics = accountClient.mosaics(address, null, null);
        assertEquals(0, mosaics.data.size());
    }

    @Test
    void getOwnedMosaics() {
        List<OwnedMosaic> expectedOwnedMosaics = new ArrayList<>();
        expectedOwnedMosaics.add(new OwnedMosaic(new MosaicId("nem", "xem"), 13000000));
        OwnedMosaicsResponse ownedMosaicsResponse = accountClient.ownedMosaics(address);
        assertEquals(expectedOwnedMosaics, ownedMosaicsResponse.data);
    }

    @Test
    @Disabled("only for local NIS")
    void tryToUnlock() {
        assertThrows(FeignException.class, () -> accountClient.unlock(new PrivateKey(privateKey)));
    }

    @Test
    @Disabled("only for local NIS")
    void tryToLock() {
        accountClient.lock(new PrivateKey(privateKey));
    }

    @Test
    void getUnlockedInfo() {
        UnlockedInfo unlockedInfo = accountClient.unlockedInfo();
        assertNotNull(unlockedInfo);
    }

    @Test
    @Disabled("only for NIS nodes that supports historical data feature")
    void getHistory() {
        HistoryResponse history = accountClient.history(address, 1002229, 1003229, 100);
        assertEquals(address, history.data.stream().findFirst().orElseThrow(RuntimeException::new).address);
    }
}