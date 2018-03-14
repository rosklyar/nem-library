package com.github.rosklyar.client.transaction.version;

import com.github.rosklyar.client.transaction.TransactionType;

import java.util.Set;

import static com.google.common.collect.ImmutableSet.of;
import static com.github.rosklyar.client.transaction.TransactionType.MULTISIG_AGGREGATE_MODIFICATION;
import static com.github.rosklyar.client.transaction.TransactionType.TRANSFER_MOSAICS;

public class DefaultVersionProvider implements VersionProvider {

    private final Set<TransactionType> transferTypes = of(TRANSFER_MOSAICS, MULTISIG_AGGREGATE_MODIFICATION);

    @Override
    public int version(Network network, TransactionType transactionType) {
        return (network.code << 24) + type(transactionType);
    }

    private int type(TransactionType transactionType) {
        return transferTypes.contains(transactionType) ? 2 : 1;
    }
}
