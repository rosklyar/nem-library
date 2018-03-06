package io.nem.client.transaction.encode;

import io.nem.client.common.Transaction;

public interface TransactionEncoder {
    byte[] data(Transaction transaction);
}
