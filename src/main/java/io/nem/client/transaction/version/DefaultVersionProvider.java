package io.nem.client.transaction.version;

public class DefaultVersionProvider implements VersionProvider {

    @Override
    public int version(Network network, int transactionType) {
        return network == Network.TEST ? -1744830463 : 0x68000001;
    }
}
