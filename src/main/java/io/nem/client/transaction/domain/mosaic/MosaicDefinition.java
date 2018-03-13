package io.nem.client.transaction.domain.mosaic;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.nem.client.mosaic.domain.Levy;
import io.nem.client.mosaic.domain.MosaicProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = MosaicDefinition.MosaicDefinitionBuilder.class)
public class MosaicDefinition {

    public final String creator;
    public final String description;
    public final MosaicId id;
    public final List<MosaicProperty> properties;
    public final Levy levy;

    @JsonPOJOBuilder(withPrefix = "")
    public static class MosaicDefinitionBuilder {

    }
}
