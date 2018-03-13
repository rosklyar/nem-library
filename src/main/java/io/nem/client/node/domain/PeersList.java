package io.nem.client.node.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = PeersList.PeersListBuilder.class)
public class PeersList {

    public final List<Node> inactive;
    public final List<Node> active;
    public final List<Node> busy;
    public final List<Node> failure;

    @JsonPOJOBuilder(withPrefix = "")
    public static class PeersListBuilder {

    }
}
