package com.github.rosklyar.client.account.domain.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class Transactions {

    public final List<TransactionMetaDataPair> data;

    @JsonCreator
    public Transactions(@JsonProperty("data") List<TransactionMetaDataPair> data) {
        this.data = data;
    }
}
