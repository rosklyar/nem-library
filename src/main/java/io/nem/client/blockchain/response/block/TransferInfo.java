package io.nem.client.blockchain.response.block;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nem.client.common.Transaction;
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
