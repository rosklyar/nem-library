package io.nem.client;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import io.nem.client.account.AccountClient;
import io.nem.client.account.FeignAccountClient;
import io.nem.client.blockchain.BlockchainClient;
import io.nem.client.blockchain.FeignBlockchainClient;
import io.nem.client.mosaic.FeignMosaicClient;
import io.nem.client.mosaic.MosaicClient;
import io.nem.client.node.FeignNodeClient;
import io.nem.client.node.NodeClient;
import io.nem.client.status.FeignStatusClient;
import io.nem.client.status.StatusClient;
import io.nem.client.transaction.FeignTransactionClient;
import io.nem.client.transaction.SecureTransactionClient;
import io.nem.client.transaction.TransactionClient;
import io.nem.client.transaction.encode.*;
import io.nem.client.transaction.fee.DefaultFeeProvider;
import io.nem.client.transaction.fee.FeeProvider;
import io.nem.client.transaction.version.DefaultVersionProvider;
import io.nem.client.transaction.version.Network;
import io.nem.client.transaction.version.VersionProvider;

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

    @Override
    public MosaicClient createMosaicClient(String nodeUrl) {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(FeignMosaicClient.class, nodeUrl);
    }

    @Override
    public TransactionClient createTransactionClient(String nodeUrl, Network network) {
        FeignTransactionClient feignTransactionClient = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(FeignTransactionClient.class, nodeUrl);
        ByteSerializer byteSerializer = new DefaultByteSerializer();
        HexConverter hexConverter = new DefaultHexConverter();
        TransactionEncoder transactionEncoder = new ByteArrayTransactionEncoder(byteSerializer, hexConverter);
        VersionProvider versionProvider = new DefaultVersionProvider();
        MosaicClient mosaicClient = createMosaicClient(nodeUrl);
        AccountClient accountClient = createAccountClient(nodeUrl);
        FeeProvider feeProvider = new DefaultFeeProvider(mosaicClient, accountClient);
        NodeClient nodeClient = createNodeClient(nodeUrl);

        return new SecureTransactionClient(network, feignTransactionClient, transactionEncoder, hexConverter, versionProvider, feeProvider, nodeClient);
    }
}
