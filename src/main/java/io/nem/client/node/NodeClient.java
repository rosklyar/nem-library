package io.nem.client.node;

import io.nem.client.blockchain.response.block.BlockHeight;
import io.nem.client.node.request.BootNodeRequest;
import io.nem.client.node.response.*;

public interface NodeClient {

    Node info();

    ExtendedNodeInfo extendedInfo();

    PeersList peersList();

    NodeCollection active();

    NodeCollection activeBroadcasts();

    BlockHeight maxChainHeight();

    NodeExperiencesResponse experiences();

    void bootNode(BootNodeRequest bootNodeRequest);
}
