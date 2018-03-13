package io.nem.client.blockchain.domain.block;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class BlocksAfterResponse {

    public final List<BlockInfo> data;

    @JsonCreator
    public BlocksAfterResponse(@JsonProperty("data") List<BlockInfo> data) {
        this.data = data;
    }
}
