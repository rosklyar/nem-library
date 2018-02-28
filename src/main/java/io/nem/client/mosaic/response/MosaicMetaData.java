package io.nem.client.mosaic.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class MosaicMetaData {

    public final MetaData meta;
    public final MosaicInfo mosaic;

    @JsonCreator
    public MosaicMetaData(@JsonProperty("mosaic") MosaicInfo mosaic,
                          @JsonProperty("meta") MetaData meta) {
        this.mosaic = mosaic;
        this.meta = meta;
    }

}
