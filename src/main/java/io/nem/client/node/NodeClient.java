package io.nem.client.node;

import io.nem.client.blockchain.domain.block.BlockHeight;
import io.nem.client.node.domain.*;

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
