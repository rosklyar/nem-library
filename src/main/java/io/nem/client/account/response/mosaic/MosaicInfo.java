package io.nem.client.account.response.mosaic;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = MosaicInfo.MosaicInfoBuilder.class)
public class MosaicInfo {

    public final String creator;
    public final String description;
    public final MosaicId id;
    public final Map<String, String> properties;

    @JsonPOJOBuilder(withPrefix = "")
    public static class MosaicInfoBuilder {

    }
}
