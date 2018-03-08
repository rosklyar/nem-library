package io.nem.client.common.transaction.importance;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
public class ImportanceTransferTransaction {

    public final int timeStamp;
    public final long fee;
    public final int type;
    public final int deadline;
    public final int version;
    public final String signer;
    public final String remoteAccount;
    public final Action action;

}
