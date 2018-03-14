package com.github.rosklyar.client.mosaic.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.rosklyar.client.transaction.domain.mosaic.MosaicDefinition;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class MosaicMetaData {

    public final MetaData meta;
    public final MosaicDefinition mosaic;

    @JsonCreator
    public MosaicMetaData(@JsonProperty("mosaic") MosaicDefinition mosaic,
                          @JsonProperty("meta") MetaData meta) {
        this.mosaic = mosaic;
        this.meta = meta;
    }

}
