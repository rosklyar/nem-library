package com.github.rosklyar.client.node.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class ExtendedNodeInfo {

    public final Node node;
    public final NisInfo nisInfo;

    @JsonCreator
    public ExtendedNodeInfo(@JsonProperty("node") Node node, @JsonProperty("nisInfo") NisInfo nisInfo) {
        this.node = node;
        this.nisInfo = nisInfo;
    }
}
