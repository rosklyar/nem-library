package io.nem.client.common.transaction.multisig;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class RelativeChange {

    public final int relativeChange;

    @JsonCreator
    public RelativeChange(@JsonProperty("relativeChange") int relativeChange) {
        this.relativeChange = relativeChange;
    }
}
