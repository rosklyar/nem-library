package io.nem.client.transaction.domain.mosaic;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
public class MosaicProperties {
    public final int divisibility;
    public final long initialSupply;
    public final boolean supplyMutable;
    public final boolean transferable;
}
