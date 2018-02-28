package io.nem.client.blockchain;

import io.nem.client.blockchain.response.block.BlockHeight;
import io.nem.client.blockchain.response.HeightResponse;
import io.nem.client.blockchain.response.ScoreResponse;
import io.nem.client.blockchain.response.block.Block;
import io.nem.client.blockchain.response.block.BlocksAfterResponse;


public interface BlockchainClient {
    HeightResponse height();

    ScoreResponse score();

    Block lastBlock();

    Block block(BlockHeight height);

    BlocksAfterResponse blocksAfter(BlockHeight height);
}
