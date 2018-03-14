package com.github.rosklyar.client.blockchain.domain.block;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.rosklyar.client.transaction.domain.Transaction;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class TransferInfo {

    public final Transaction tx;
    public final String hash;
    public final String innerHash;

    @JsonCreator
    public TransferInfo(@JsonProperty("tx") Transaction tx, @JsonProperty("hash") String hash, @JsonProperty("innerHash") String innerHash) {
        this.tx = tx;
        this.hash = hash;
        this.innerHash = innerHash;
    }
}
