package io.nem.client.transaction.version;

import io.nem.client.transaction.TransactionType;

public interface VersionProvider {
    int version(Network network, TransactionType transactionType);
}
