package io.nem.client.account.response.mosaic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nem.client.common.transaction.mosaic.MosaicId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class OwnedMosaic {

    public final MosaicId mosaicId;
    public final long quantity;

    @JsonCreator
    public OwnedMosaic(@JsonProperty("mosaicId") MosaicId mosaicId, @JsonProperty("quantity") long quantity) {
        this.mosaicId = mosaicId;
        this.quantity = quantity;
    }
}
