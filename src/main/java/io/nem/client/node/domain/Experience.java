package io.nem.client.node.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Experience {

    public final int s;
    public final int f;

    @JsonCreator
    public Experience(@JsonProperty("s") int s, @JsonProperty("f") int f) {
        this.s = s;
        this.f = f;
    }
}
