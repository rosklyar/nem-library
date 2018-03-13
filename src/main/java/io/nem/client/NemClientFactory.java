package io.nem.client;

import io.nem.client.account.AccountClient;
import io.nem.client.blockchain.BlockchainClient;
import io.nem.client.mosaic.MosaicClient;
import io.nem.client.node.NodeClient;
import io.nem.client.status.StatusClient;
import io.nem.client.transaction.TransactionClient;
import io.nem.client.transaction.version.Network;

public interface NemClientFactory {

    StatusClient createStatusClient(String configurationPrefix);

    AccountClient createAccountClient(String configurationPrefix);

    BlockchainClient createBlockchainClient(String configurationPrefix);

    NodeClient createNodeClient(String configurationPrefix);

    MosaicClient createMosaicClient(String configurationPrefix);

    TransactionClient createTransactionClient(String configurationPrefix, Network network, MosaicClient mosaicClient, AccountClient accountClient, NodeClient nodeClient);
}
