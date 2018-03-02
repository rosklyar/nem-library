package io.nem.client.transaction.version;

public interface VersionProvider {
    int version(Network network, int transactionType);
}
