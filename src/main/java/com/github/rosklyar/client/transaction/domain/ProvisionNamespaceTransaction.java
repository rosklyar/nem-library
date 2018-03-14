package com.github.rosklyar.client.transaction.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
public class ProvisionNamespaceTransaction {

    public final int timeStamp;
    public final long fee;
    public final int type;
    public final int deadline;
    public final int version;
    public final String signer;
    public final String rentalFeeSink;
    public final long rentalFee;
    public final String newPart;
    public final String parent;

}
