package io.nem.client.blockchain.response.block;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = BlockInfo.BlockInfoBuilder.class)
public class BlockInfo {

    public final List<TransferInfo> txes;
    public final Block block;
    public final String hash;
    public final long difficulty;

    @JsonPOJOBuilder(withPrefix = "")
    public static class BlockInfoBuilder {

    }
}
