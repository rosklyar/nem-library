package com.github.rosklyar.client.transaction.domain.multisig;

import lombok.Builder;

@Builder
public class MultisigTransaction<T> {
    public final int timeStamp;
    public final long fee;
    public final int type;
    public final int deadline;
    public final int version;
    public final String signer;
    public final T otherTrans;
}
