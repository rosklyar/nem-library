package io.nem.client;

import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.ribbon.RibbonClient;
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
import io.nem.client.transaction.fee.DefaultFeeCalculator;
import io.nem.client.transaction.fee.FeeCalculator;
import io.nem.client.transaction.version.DefaultVersionProvider;
import io.nem.client.transaction.version.Network;
import io.nem.client.transaction.version.VersionProvider;

import static com.netflix.config.ConfigurationManager.getConfigInstance;
import static feign.hystrix.HystrixFeign.builder;
import static io.nem.client.transaction.version.Network.valueOf;

public class DefaultNemClientFactory implements NemClientFactory {

    @Override
    public StatusClient createStatusClient() {
        return builder()
                .client(RibbonClient.create())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(FeignStatusClient.class, "http://statusApi");
    }

    @Override
    public AccountClient createAccountClient() {
        return builder()
                .client(RibbonClient.create())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(FeignAccountClient.class, "http://accountApi");
    }

    @Override
    public BlockchainClient createBlockchainClient() {
        return builder()
                .client(RibbonClient.create())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(FeignBlockchainClient.class, "http://blockchainApi");
    }

    @Override
    public NodeClient createNodeClient() {
        return builder()
                .client(RibbonClient.create())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(FeignNodeClient.class, "http://nodeApi");
    }

    @Override
    public MosaicClient createMosaicClient() {
        return builder()
                .client(RibbonClient.create())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(FeignMosaicClient.class, "http://mosaicApi");
    }

    @Override
    public TransactionClient createTransactionClient() {
        FeignTransactionClient feignTransactionClient = builder()
                .client(RibbonClient.create())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(FeignTransactionClient.class, "http://transactionApi");
        HexConverter hexConverter = new DefaultHexConverter();
        ByteSerializer byteSerializer = new DefaultByteSerializer(hexConverter);
        TransactionEncoder transactionEncoder = new ByteArrayTransactionEncoder(byteSerializer, hexConverter);
        VersionProvider versionProvider = new DefaultVersionProvider();
        MosaicClient mosaicClient = createMosaicClient();
        AccountClient accountClient = createAccountClient();
        FeeCalculator feeCalculator = new DefaultFeeCalculator(mosaicClient, accountClient);
        NodeClient nodeClient = createNodeClient();
        Network network = valueOf(getConfigInstance().getString("transaction.client.network"));
        return new SecureTransactionClient(network, feignTransactionClient, transactionEncoder, hexConverter, versionProvider, feeCalculator, nodeClient);
    }
}
