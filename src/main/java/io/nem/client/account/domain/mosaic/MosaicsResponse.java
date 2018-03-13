package io.nem.client.account.domain.mosaic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nem.client.transaction.domain.mosaic.MosaicDefinition;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class MosaicsResponse {

    public final List<MosaicDefinition> data;

    @JsonCreator
    public MosaicsResponse(@JsonProperty("data") List<MosaicDefinition> data) {
        this.data = data;
    }
}
