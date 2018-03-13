package io.nem.client.blockchain;

import feign.Headers;
import feign.RequestLine;
import io.nem.client.blockchain.domain.block.BlockHeight;
import io.nem.client.blockchain.domain.HeightResponse;
import io.nem.client.blockchain.domain.ScoreResponse;
import io.nem.client.blockchain.domain.block.Block;
import io.nem.client.blockchain.domain.block.BlocksAfterResponse;

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
