package io.nem.client.account.response.mosaic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nem.client.mosaic.response.MosaicId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class OwnedMosaic {

    public final MosaicId mosaicId;
    public final double quantity;

    @JsonCreator
    public OwnedMosaic(@JsonProperty("mosaicId") MosaicId mosaicId, @JsonProperty("quantity") double quantity) {
        this.mosaicId = mosaicId;
        this.quantity = quantity;
    }
}
