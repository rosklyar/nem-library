package io.nem.client.node.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = Node.NodeBuilder.class)
public class Node {

    public final NodeMetaData metaData;
    public final Endpoint endpoint;
    public final Identity identity;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NodeBuilder {

    }
}
