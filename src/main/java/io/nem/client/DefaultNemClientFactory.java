package io.nem.client;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import io.nem.client.account.AccountClient;
import io.nem.client.account.FeignAccountClient;
import io.nem.client.blockchain.BlockchainClient;
import io.nem.client.blockchain.FeignBlockchainClient;
import io.nem.client.node.FeignNodeClient;
import io.nem.client.node.NodeClient;
import io.nem.client.status.FeignStatusClient;
import io.nem.client.status.StatusClient;

public class DefaultNemClientFactory implements NemClientFactory {

    @Override
    public StatusClient createStatusClient(String nodeUrl) {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(FeignStatusClient.class, nodeUrl);
    }

    @Override
    public AccountClient createAccountClient(String nodeUrl) {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(FeignAccountClient.class, nodeUrl);
    }

    @Override
    public BlockchainClient createBlockchainClient(String nodeUrl) {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(FeignBlockchainClient.class, nodeUrl);
    }

    @Override
    public NodeClient createNodeClient(String nodeUrl) {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(FeignNodeClient.class, nodeUrl);
    }
}
