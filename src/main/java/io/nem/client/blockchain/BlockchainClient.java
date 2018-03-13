package io.nem.client.blockchain;

import io.nem.client.blockchain.domain.block.BlockHeight;
import io.nem.client.blockchain.domain.HeightResponse;
import io.nem.client.blockchain.domain.ScoreResponse;
import io.nem.client.blockchain.domain.block.Block;
import io.nem.client.blockchain.domain.block.BlocksAfterResponse;


public interface BlockchainClient {
    HeightResponse height();

    ScoreResponse score();

    Block lastBlock();

    Block block(BlockHeight height);

    BlocksAfterResponse blocksAfter(BlockHeight height);
}
