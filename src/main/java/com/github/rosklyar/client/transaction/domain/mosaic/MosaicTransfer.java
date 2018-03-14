package com.github.rosklyar.client.transaction.domain.mosaic;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
public class MosaicTransfer {

    public final MosaicId mosaicId;
    public final long quantity;

}
