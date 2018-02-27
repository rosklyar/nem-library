package io.nem.client.blockchain.request.block;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlockHeight {
    public final long height;
}
