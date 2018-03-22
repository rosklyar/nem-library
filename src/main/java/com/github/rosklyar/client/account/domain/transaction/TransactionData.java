package com.github.rosklyar.client.account.domain.transaction;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.rosklyar.client.account.domain.Message;
import com.github.rosklyar.client.account.domain.Signature;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = TransactionData.TransactionDataBuilder.class)
public class TransactionData {

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
    public final TransactionData otherTrans;
    public final List<Signature> signatures;

    @JsonPOJOBuilder(withPrefix = "")
    public static class TransactionDataBuilder {

    }
}
