package io.nem.client;

import io.nem.client.account.AccountClient;
import io.nem.client.blockchain.BlockchainClient;
import io.nem.client.mosaic.MosaicClient;
import io.nem.client.node.NodeClient;
import io.nem.client.status.StatusClient;
import io.nem.client.transaction.TransactionClient;

public interface NemClientFactory {

    StatusClient createStatusClient();

    AccountClient createAccountClient();

    BlockchainClient createBlockchainClient();

    NodeClient createNodeClient();

    MosaicClient createMosaicClient();

    TransactionClient createTransactionClient();
}
