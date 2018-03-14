package com.github.rosklyar.client.blockchain.domain.block;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class BlockHeight {

    public final long height;

    @JsonCreator
    public BlockHeight(@JsonProperty("height") long height) {
        this.height = height;
    }
}
