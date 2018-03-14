package com.github.rosklyar.client.transaction.version;

import com.github.rosklyar.client.transaction.TransactionType;

public interface VersionProvider {
    int version(Network network, TransactionType transactionType);
}
