package com.github.rosklyar.client.node;

import com.github.rosklyar.client.blockchain.domain.block.BlockHeight;
import com.github.rosklyar.client.node.domain.*;

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
