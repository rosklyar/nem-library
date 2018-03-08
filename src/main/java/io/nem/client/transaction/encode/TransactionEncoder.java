package io.nem.client.transaction.encode;

import io.nem.client.common.transaction.ProvisionNamespaceTransaction;
import io.nem.client.common.transaction.Transaction;
import io.nem.client.common.transaction.importance.ImportanceTransferTransaction;

public interface TransactionEncoder {
    byte[] data(Transaction transaction);

    byte[] data(ProvisionNamespaceTransaction transaction);

    byte[] data(ImportanceTransferTransaction transaction);
}
