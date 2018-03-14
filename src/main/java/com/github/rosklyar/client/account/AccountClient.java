package com.github.rosklyar.client.account;

import com.github.rosklyar.client.account.domain.*;
import com.github.rosklyar.client.account.domain.harvest.HarvestsResponse;
import com.github.rosklyar.client.account.domain.history.HistoryResponse;
import com.github.rosklyar.client.account.domain.importance.ImportanceResponse;
import com.github.rosklyar.client.account.domain.mosaic.MosaicsResponse;
import com.github.rosklyar.client.account.domain.mosaic.OwnedMosaicsResponse;
import com.github.rosklyar.client.account.domain.namespace.NamespacesResponse;
import com.github.rosklyar.client.account.domain.transaction.Transactions;
import com.github.rosklyar.client.account.domain.transaction.UnconfirmedTransactions;

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
