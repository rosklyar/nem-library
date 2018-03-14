package com.github.rosklyar.client.blockchain;

import com.github.rosklyar.client.blockchain.domain.HeightResponse;
import com.github.rosklyar.client.blockchain.domain.ScoreResponse;
import com.github.rosklyar.client.blockchain.domain.block.Block;
import com.github.rosklyar.client.blockchain.domain.block.BlockHeight;
import com.github.rosklyar.client.blockchain.domain.block.BlocksAfterResponse;


public interface BlockchainClient {
    HeightResponse height();

    ScoreResponse score();

    Block lastBlock();

    Block block(BlockHeight height);

    BlocksAfterResponse blocksAfter(BlockHeight height);
}
