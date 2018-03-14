package com.github.rosklyar.client.node;

import com.github.rosklyar.client.DefaultNemClientFactory;
import com.github.rosklyar.client.blockchain.domain.block.BlockHeight;
import com.github.rosklyar.client.node.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.netflix.config.ConfigurationManager.getConfigInstance;
import static org.junit.jupiter.api.Assertions.*;

class NodeClientTest {

    private static String IP = "153.122.112.137";
    private final NodeClient nodeClient = new DefaultNemClientFactory().createNodeClient("nodeApi");

    private final String privateKey = "0476fd96242ac5ef6cb1b268887254c1a3089759556beb1ce660c0cb2c42bb27";

    @BeforeAll
    static void init() {
        getConfigInstance().setProperty("nodeApi.ribbon.listOfServers", IP + ":7890");
        getConfigInstance().setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", 20000);
    }

    @Test
    void getNodeInfo() {
        Node info = nodeClient.info();
        assertEquals(IP, info.endpoint.host);

        ExtendedNodeInfo extendedNodeInfo = nodeClient.extendedInfo();
        assertEquals(info, extendedNodeInfo.node);
    }

    @Test
    void getPeersList() {
        PeersList peersList = nodeClient.peersList();
        assertNotNull(peersList);
    }

    @Test
    void getActivePeers() {
        PeersList peersList = nodeClient.peersList();
        NodeCollection active = nodeClient.active();
        assertEquals(peersList.active, active.data);
    }

    @Test
    void getActiveBroadcastPeers() {
        NodeCollection nodeCollection = nodeClient.activeBroadcasts();
        assertNotNull(nodeCollection);
    }

    @Test
    void getMaxChainHeight() {
        BlockHeight blockHeight = nodeClient.maxChainHeight();
        assertTrue(blockHeight.height > 0);
    }

    @Test
    void getNodeExperiences() {
        NodeExperiencesResponse experiences = nodeClient.experiences();
        assertTrue(experiences.data.size() > 0);
    }

    @Test
    @Disabled("only for local NIS nodes")
    void bootNode() {
        BootNodeRequest bootNodeRequest = new BootNodeRequest(new ApplicationMetaData("application"), new Endpoint("http", "127.0.0.1", 7890), new PrivateIdentity("test", privateKey));
        nodeClient.bootNode(bootNodeRequest);
    }
}
