package io.nem.client.node.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = NisInfo.NisInfoBuilder.class)
public class NisInfo {

    public final int currentTime;
    public final long startTime;
    public final String application;
    public final String version;
    public final String signer;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NisInfoBuilder {

    }
}
