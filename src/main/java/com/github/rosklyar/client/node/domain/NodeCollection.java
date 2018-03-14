package com.github.rosklyar.client.node.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class NodeCollection {

    public final List<Node> data;

    @JsonCreator
    public NodeCollection(@JsonProperty("data") List<Node> data) {
        this.data = data;
    }
}
