package io.nem.client.blockchain;

import io.nem.client.DefaultNemClientFactory;
import io.nem.client.blockchain.response.HeightResponse;
import io.nem.client.blockchain.response.ScoreResponse;
import io.nem.client.blockchain.response.block.Block;
import io.nem.client.blockchain.response.block.BlockHeight;
import io.nem.client.blockchain.response.block.BlocksAfterResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.netflix.config.ConfigurationManager.getConfigInstance;
import static org.junit.jupiter.api.Assertions.*;

class BlockchainClientTest {

    private final BlockchainClient blockchainClient = new DefaultNemClientFactory().createBlockchainClient();

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