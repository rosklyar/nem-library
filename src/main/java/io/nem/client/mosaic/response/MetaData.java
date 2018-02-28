package io.nem.client.mosaic.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class MetaData {

    public final long id;

    @JsonCreator
    public MetaData(@JsonProperty("id") long id) {
        this.id = id;
    }
}
