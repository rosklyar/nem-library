package com.github.rosklyar.client;

import com.github.rosklyar.client.account.AccountClient;
import com.github.rosklyar.client.blockchain.BlockchainClient;
import com.github.rosklyar.client.mosaic.MosaicClient;
import com.github.rosklyar.client.node.NodeClient;
import com.github.rosklyar.client.status.StatusClient;
import com.github.rosklyar.client.transaction.TransactionClient;
import com.github.rosklyar.client.transaction.version.Network;

public interface NemClientFactory {

    StatusClient createStatusClient(String configurationPrefix);

    AccountClient createAccountClient(String configurationPrefix);

    BlockchainClient createBlockchainClient(String configurationPrefix);

    NodeClient createNodeClient(String configurationPrefix);

    MosaicClient createMosaicClient(String configurationPrefix);

    TransactionClient createTransactionClient(String configurationPrefix, Network network, MosaicClient mosaicClient, AccountClient accountClient, NodeClient nodeClient);
}
