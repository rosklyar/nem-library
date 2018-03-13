package io.nem.client.mosaic.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class MosaicProperty {

    public final String name;
    public final String value;

    @JsonCreator
    public MosaicProperty(@JsonProperty("name") String name,
                          @JsonProperty("value") String value) {
        this.name = name;
        this.value = value;
    }
}
