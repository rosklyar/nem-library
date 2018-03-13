package io.nem.client.transaction.encode;

import io.nem.client.transaction.domain.ProvisionNamespaceTransaction;
import io.nem.client.transaction.domain.Transaction;
import io.nem.client.transaction.domain.importance.ImportanceTransferTransaction;
import io.nem.client.transaction.domain.mosaic.MosaicDefinitionCreationTransaction;

public interface TransactionEncoder {
    byte[] data(Transaction transaction);

    byte[] data(ProvisionNamespaceTransaction transaction);

    byte[] data(ImportanceTransferTransaction transaction);

    byte[] data(MosaicDefinitionCreationTransaction mosaicDefinitionCreationTransaction);
}
