package io.nem.client.node.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = NodeMetaData.NodeMetaDataBuilder.class)
public class NodeMetaData {

    public final int features;
    public final String application;
    public final long networkId;
    public final String version;
    public final String platform;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NodeMetaDataBuilder {

    }
}
