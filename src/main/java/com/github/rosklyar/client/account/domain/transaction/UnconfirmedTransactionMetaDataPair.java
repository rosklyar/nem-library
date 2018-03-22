package com.github.rosklyar.client.account.domain.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class UnconfirmedTransactionMetaDataPair {

    public final UnconfirmedTransactionMetaData meta;
    public final TransactionData transaction;

    @JsonCreator
    public UnconfirmedTransactionMetaDataPair(@JsonProperty("meta") UnconfirmedTransactionMetaData meta,
                                              @JsonProperty("transaction") TransactionData transaction) {
        this.meta = meta;
        this.transaction = transaction;
    }
}
