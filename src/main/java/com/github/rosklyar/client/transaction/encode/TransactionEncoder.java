package com.github.rosklyar.client.transaction.encode;

import com.github.rosklyar.client.transaction.domain.ProvisionNamespaceTransaction;
import com.github.rosklyar.client.transaction.domain.Transaction;
import com.github.rosklyar.client.transaction.domain.importance.ImportanceTransferTransaction;
import com.github.rosklyar.client.transaction.domain.mosaic.MosaicDefinitionCreationTransaction;

public interface TransactionEncoder {
    byte[] data(Transaction transaction);

    byte[] data(ProvisionNamespaceTransaction transaction);

    byte[] data(ImportanceTransferTransaction transaction);

    byte[] data(MosaicDefinitionCreationTransaction mosaicDefinitionCreationTransaction);
}
