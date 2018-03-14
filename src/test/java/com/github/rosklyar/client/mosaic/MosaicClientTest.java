package com.github.rosklyar.client.mosaic;

import com.github.rosklyar.client.DefaultNemClientFactory;
import com.github.rosklyar.client.mosaic.domain.MosaicsMetaDataResponse;
import com.github.rosklyar.client.mosaic.domain.Namespace;
import com.github.rosklyar.client.mosaic.domain.NamespacesMetaDataResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.netflix.config.ConfigurationManager.getConfigInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MosaicClientTest {

    private final MosaicClient mosaicClient = new DefaultNemClientFactory().createMosaicClient("mosaicApi");
    private final MosaicClient simpleMosaicClient = new DefaultNemClientFactory().simpleMosaicClient("http://153.122.112.137:7890");

    @BeforeAll
    static void init() {
        getConfigInstance().setProperty("mosaicApi.ribbon.listOfServers", "153.122.112.137:7890");
        getConfigInstance().setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", 20000);
    }

    @Test
    void getNamespaces() {
        NamespacesMetaDataResponse top10namespaces = mosaicClient.namespaces(null, 10);
        assertEquals(10, top10namespaces.data.size());

        Namespace firstNamespace = top10namespaces.data.stream().findFirst().map(meta -> meta.namespace).orElseThrow(RuntimeException::new);
        Namespace namespace = mosaicClient.namespace(firstNamespace.fqn);
        assertEquals(firstNamespace, namespace);
    }

    @Test
    void getMosaics() {
        MosaicsMetaDataResponse nemMosaics = mosaicClient.mosaics("evias", null, null);
        assertEquals(nemMosaics, simpleMosaicClient.mosaics("evias", null, null));
        assertTrue(nemMosaics.data.size() > 0);
    }

}