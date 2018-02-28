package io.nem.client;

import io.nem.client.account.AccountClient;
import io.nem.client.blockchain.BlockchainClient;
import io.nem.client.mosaic.MosaicClient;
import io.nem.client.node.NodeClient;
import io.nem.client.status.StatusClient;

public interface NemClientFactory {

    StatusClient createStatusClient(String nodeUrl);

    AccountClient createAccountClient(String nodeUrl);

    BlockchainClient createBlockchainClient(String nodeUrl);

    NodeClient createNodeClient(String nodeUrl);

    MosaicClient createMosaicClient(String nodeUrl);
}
