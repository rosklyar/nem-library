package com.github.rosklyar.client.node.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class ApplicationMetaData {

    public final String application;

    @JsonCreator
    public ApplicationMetaData(@JsonProperty("application") String application) {
        this.application = application;
    }
}
