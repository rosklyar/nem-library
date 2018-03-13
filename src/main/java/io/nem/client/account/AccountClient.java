package io.nem.client.account;

import io.nem.client.account.domain.AccountPrivateKeyTransactionsPage;
import io.nem.client.account.domain.PrivateKey;
import io.nem.client.account.domain.AccountMetaData;
import io.nem.client.account.domain.AccountMetaDataPair;
import io.nem.client.account.domain.KeyPair;
import io.nem.client.account.domain.UnlockedInfo;
import io.nem.client.account.domain.harvest.HarvestsResponse;
import io.nem.client.account.domain.history.HistoryResponse;
import io.nem.client.account.domain.importance.ImportanceResponse;
import io.nem.client.account.domain.mosaic.MosaicsResponse;
import io.nem.client.account.domain.mosaic.OwnedMosaicsResponse;
import io.nem.client.account.domain.namespace.NamespacesResponse;
import io.nem.client.account.domain.transaction.Transactions;
import io.nem.client.account.domain.transaction.UnconfirmedTransactions;

public interface AccountClient {

    KeyPair generate();

    AccountMetaDataPair getFromAddress(String address);

    AccountMetaDataPair getFromPublicKey(String publicKey);

    AccountMetaDataPair getForwarded(String delegateAddress);

    AccountMetaDataPair getForwardedFromPublicKey(String delegatePublicKey);

    AccountMetaData status(String address);

    Transactions incomingTransactions(String address);

    Transactions incomingTransactions(String address, long id);

    Transactions outgoingTransactions(String address);

    Transactions outgoingTransactions(String address, long id);

    Transactions allTransactions(String address);

    Transactions allTransactions(String address, long id);

    UnconfirmedTransactions unconfirmedTransactions(String address);

    Transactions incomingDecodedTransactions(AccountPrivateKeyTransactionsPage request);

    Transactions outgoingDecodedTransactions(AccountPrivateKeyTransactionsPage request);

    Transactions allDecodedTransactions(AccountPrivateKeyTransactionsPage request);

    HarvestsResponse harvests(String address, String hash);

    ImportanceResponse importances();

    NamespacesResponse namespaces(String address, String parent, Long id, Integer pageSize);

    MosaicsResponse mosaics(String address, String parent, Long id);

    OwnedMosaicsResponse ownedMosaics(String address);

    void unlock(PrivateKey privateKey);

    void lock(PrivateKey privateKey);

    UnlockedInfo unlockedInfo();

    HistoryResponse history(String address, long startHeight, long endHeight, int increment);
}
