package io.nem.client.account.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = Transaction.TransactionBuilder.class)
public class Transaction {

    public final long timeStamp;
    public final long amount;
    public final String signature;
    public final long fee;
    public final String recipient;
    public final int type;
    public final long deadline;
    public final Message message;
    public final long version;
    public final String signer;
    public final List<Modification> modifications;
    public final Transaction otherTrans;
    public final List<Signature> signatures;

    @JsonPOJOBuilder(withPrefix = "")
    public static class TransactionBuilder {

    }
}
