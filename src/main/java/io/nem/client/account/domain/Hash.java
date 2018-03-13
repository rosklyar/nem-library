package io.nem.client.account.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Hash {

    public final String data;

    @JsonCreator
    public Hash(@JsonProperty("data") String data) {
        this.data = data;
    }
}
