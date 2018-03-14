package com.github.rosklyar.client.node.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Identity {
    public final String name;
    public final String publicKey;

    @JsonCreator
    public Identity(@JsonProperty("name") String name, @JsonProperty("public-key") String publicKey) {
        this.name = name;
        this.publicKey = publicKey;
    }
}
