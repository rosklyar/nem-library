package io.nem.client.transaction.encode;

import io.nem.client.transaction.Transaction;

public interface TransactionEncoder {
    byte[] data(Transaction transaction);
}
