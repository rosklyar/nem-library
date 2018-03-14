package com.github.rosklyar.client.node.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrivateIdentity {

    public final String name;

    @JsonProperty("private-key")
    public final String privateKey;
}
