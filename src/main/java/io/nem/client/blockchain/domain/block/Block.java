package io.nem.client.blockchain.domain.block;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.nem.client.account.domain.Hash;
import io.nem.client.transaction.domain.Transaction;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = Block.BlockBuilder.class)
public class Block {

    public final long timeStamp;
    public final String signature;
    public final Hash prevBlockHash;
    public final int type;
    public final List<Transaction> transactions;
    public final long version;
    public final String signer;
    public final long height;

    @JsonPOJOBuilder(withPrefix = "")
    public static class BlockBuilder {

    }
}
