package io.nem.client.transaction.fee;

public interface FeeProvider {
    long fee(long amount, String payload);
}
