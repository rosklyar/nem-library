package io.nem.client.node;

import feign.Headers;
import feign.RequestLine;
import io.nem.client.blockchain.response.block.BlockHeight;
import io.nem.client.node.request.BootNodeRequest;
import io.nem.client.node.response.*;

@Headers({"Accept: application/json"})
public interface FeignNodeClient extends NodeClient {

    @Override
    @RequestLine("GET /node/info")
    Node info();

    @Override
    @RequestLine("GET /node/extended-info")
    ExtendedNodeInfo extendedInfo();

    @Override
    @RequestLine("GET /node/peer-list/all")
    PeersList peersList();

    @Override
    @RequestLine("GET /node/peer-list/reachable")
    NodeCollection active();

    @Override
    @RequestLine("GET /node/peer-list/active")
    NodeCollection activeBroadcasts();

    @Override
    @RequestLine("GET /node/active-peers/max-chain-height")
    BlockHeight maxChainHeight();

    @Override
    @RequestLine("GET /node/experiences")
    NodeExperiencesResponse experiences();

    @Override
    @Headers("Content-Type: application/json")
    @RequestLine("POST /node/boot")
    void bootNode(BootNodeRequest bootNodeRequest);
}
