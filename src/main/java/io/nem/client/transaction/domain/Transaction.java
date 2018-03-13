package io.nem.client.transaction.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.nem.client.account.domain.Signature;
import io.nem.client.account.domain.Hash;
import io.nem.client.account.domain.Message;
import io.nem.client.transaction.domain.mosaic.MosaicTransfer;
import io.nem.client.transaction.domain.multisig.Modification;
import io.nem.client.transaction.domain.multisig.RelativeChange;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = Transaction.TransactionBuilder.class)
public class Transaction {

    public final int timeStamp;
    public final long amount;
    public final String signature;
    public final long fee;
    public final String recipient;
    public final int type;
    public final int deadline;
    public final Message message;
    public final int version;
    public final String signer;
    public final List<Modification> modifications;
    public final RelativeChange minCosignatories;
    public final Transaction otherTrans;
    public final List<Signature> signatures;
    public final List<MosaicTransfer> mosaics;
    public final String otherAccount;
    public final Hash otherHash;

    @JsonPOJOBuilder(withPrefix = "")
    public static class TransactionBuilder {

    }
}