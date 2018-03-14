package com.github.rosklyar.client.node.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Endpoint {

    public final String protocol;
    public final String host;
    public final int port;

    @JsonCreator
    public Endpoint(@JsonProperty("protocol") String protocol,
                    @JsonProperty("host") String host,
                    @JsonProperty("port") int port) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
    }
}
