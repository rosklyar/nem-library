package io.nem.client.transaction.version;

public class DefaultVersionProvider implements VersionProvider {

    @Override
    public int version(Network network, int transactionType) {
        return -1744830463;
    }
}
