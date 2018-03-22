package com.github.rosklyar.client.account.domain.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class TransactionMetaDataPair {

    public final TransactionMetaData meta;
    public final TransactionData transaction;

    @JsonCreator
    public TransactionMetaDataPair(@JsonProperty("meta") TransactionMetaData meta,
                                   @JsonProperty("transaction") TransactionData transaction) {
        this.meta = meta;
        this.transaction = transaction;
    }
}
