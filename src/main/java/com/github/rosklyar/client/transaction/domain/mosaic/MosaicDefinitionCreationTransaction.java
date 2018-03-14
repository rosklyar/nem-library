package com.github.rosklyar.client.transaction.domain.mosaic;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
public class MosaicDefinitionCreationTransaction {
    public final int timeStamp;
    public final long fee;
    public final int type;
    public final int deadline;
    public final int version;
    public final String signer;
    public final long creationFee;
    public final String creationFeeSink;
    public final MosaicDefinition mosaicDefinition;
}
