package io.nem.client.account;

import io.nem.client.account.response.*;

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
}
