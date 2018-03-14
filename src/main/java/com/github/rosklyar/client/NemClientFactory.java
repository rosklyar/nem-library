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

    StatusClient simpleStatusClient(String url);

    AccountClient createAccountClient(String configurationPrefix);

    AccountClient simpleAccountClient(String url);

    BlockchainClient createBlockchainClient(String configurationPrefix);

    BlockchainClient simpleBlockchainClient(String url);

    NodeClient createNodeClient(String configurationPrefix);

    NodeClient simpleNodeClient(String url);

    MosaicClient createMosaicClient(String configurationPrefix);

    MosaicClient simpleMosaicClient(String url);

    TransactionClient createTransactionClient(String configurationPrefix, Network network, MosaicClient mosaicClient, AccountClient accountClient, NodeClient nodeClient);

    TransactionClient simpleTransactionClient(String url, Network network);
}
