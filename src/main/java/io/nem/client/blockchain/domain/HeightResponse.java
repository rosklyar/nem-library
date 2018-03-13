package io.nem.client.blockchain.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class HeightResponse {

    public final long height;

    @JsonCreator
    public HeightResponse(@JsonProperty("height") long height) {
        this.height = height;
    }

}
