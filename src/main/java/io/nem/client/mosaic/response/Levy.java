package io.nem.client.mosaic.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = Levy.LevyBuilder.class)
public class Levy {

    public final long fee;
    public final String recipient;
    public final int type;
    public final MosaicId mosaicId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class LevyBuilder {

    }
}
