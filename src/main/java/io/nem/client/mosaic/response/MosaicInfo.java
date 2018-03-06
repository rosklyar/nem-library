package io.nem.client.mosaic.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.nem.client.common.MosaicId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = MosaicInfo.MosaicInfoBuilder.class)
public class MosaicInfo {

    public final String creator;
    public final String description;
    public final MosaicId id;
    public final List<MosaicProperty> properties;
    public final Levy levy;

    @JsonPOJOBuilder(withPrefix = "")
    public static class MosaicInfoBuilder {

    }
}
