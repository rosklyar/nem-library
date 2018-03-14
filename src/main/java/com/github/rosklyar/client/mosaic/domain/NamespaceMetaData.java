package com.github.rosklyar.client.mosaic.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class NamespaceMetaData {

    public final MetaData meta;
    public final Namespace namespace;

    @JsonCreator
    public NamespaceMetaData(@JsonProperty("namespace") Namespace namespace,
                             @JsonProperty("meta") MetaData meta) {
        this.namespace = namespace;
        this.meta = meta;
    }

}
