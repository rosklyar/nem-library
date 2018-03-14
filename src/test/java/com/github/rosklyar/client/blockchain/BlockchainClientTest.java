package com.github.rosklyar.client.blockchain;

import com.github.rosklyar.client.DefaultNemClientFactory;
import com.github.rosklyar.client.blockchain.domain.HeightResponse;
import com.github.rosklyar.client.blockchain.domain.ScoreResponse;
import com.github.rosklyar.client.blockchain.domain.block.Block;
import com.github.rosklyar.client.blockchain.domain.block.BlockHeight;
import com.github.rosklyar.client.blockchain.domain.block.BlocksAfterResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.netflix.config.ConfigurationManager.getConfigInstance;
import static org.junit.jupiter.api.Assertions.*;

class BlockchainClientTest {

    private final BlockchainClient blockchainClient = new DefaultNemClientFactory().createBlockchainClient("blockchainApi");
    private final BlockchainClient simpleBlockchainClient = new DefaultNemClientFactory().simpleBlockchainClient("http://153.122.112.137:7890");

    @BeforeAll
    static void init() {
        getConfigInstance().setProperty("blockchainApi.ribbon.listOfServers", "153.122.112.137:7890");
        getConfigInstance().setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", 20000);
    }

    @Test
    void getChainHeight() {
        HeightResponse heightResponse = blockchainClient.height();
        assertTrue(heightResponse.height > 0);
    }

    @Test
    void getChainScore() {
        ScoreResponse scoreResponse = blockchainClient.score();
        assertNotNull(scoreResponse.score);
    }

    @Test
    void getLastBlock() {
        Block lastBlock = blockchainClient.lastBlock();
        assertEquals(lastBlock, simpleBlockchainClient.lastBlock());
        assertTrue(lastBlock.height > 0);
    }

    @Test
    void getBlock() {
        Block lastBlock = blockchainClient.lastBlock();
        Block block = blockchainClient.block(new BlockHeight(lastBlock.height));
        assertEquals(lastBlock, block);
    }

    @Test
    @Disabled("only for local NIS")
    void getBlocks() {
        Block lastBlock = blockchainClient.lastBlock();
        assertTrue(lastBlock.height > 0);

        Block block = blockchainClient.block(new BlockHeight(lastBlock.height));
        assertEquals(lastBlock, block);

        BlocksAfterResponse blocksAfterResponse = blockchainClient.blocksAfter(new BlockHeight(lastBlock.height - 10));
        assertEquals(10, blocksAfterResponse.data.size());
        assertEquals(lastBlock, blocksAfterResponse.data.stream().filter(blockInfo -> blockInfo.block.height == lastBlock.height).findFirst().orElseThrow(RuntimeException::new).block);
    }
}