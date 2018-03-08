package io.nem.client.common.transaction.mosaic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class MosaicTransfer {

    public final MosaicId mosaicId;
    public final long quantity;

    @JsonCreator
    public MosaicTransfer(@JsonProperty("mosaicId") MosaicId mosaicId,
                          @JsonProperty("quantity") long quantity) {
        this.mosaicId = mosaicId;
        this.quantity = quantity;
    }
}
