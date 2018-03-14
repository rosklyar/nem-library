package com.github.rosklyar.client.blockchain;

import com.github.rosklyar.client.blockchain.domain.HeightResponse;
import com.github.rosklyar.client.blockchain.domain.ScoreResponse;
import com.github.rosklyar.client.blockchain.domain.block.Block;
import com.github.rosklyar.client.blockchain.domain.block.BlockHeight;
import com.github.rosklyar.client.blockchain.domain.block.BlocksAfterResponse;
import feign.Headers;
import feign.RequestLine;

@Headers({"Accept: application/json"})
public interface FeignBlockchainClient extends BlockchainClient {

    @Override
    @RequestLine("GET /chain/height")
    HeightResponse height();

    @Override
    @RequestLine("GET /chain/score")
    ScoreResponse score();

    @Override
    @RequestLine("GET /chain/last-block")
    Block lastBlock();

    @Override
    @Headers("Content-Type: application/json")
    @RequestLine("POST /block/at/public")
    Block block(BlockHeight height);

    @Override
    @Headers("Content-Type: application/json")
    @RequestLine("POST /local/chain/blocks-after")
    BlocksAfterResponse blocksAfter(BlockHeight height);
}
