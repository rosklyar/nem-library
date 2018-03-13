package io.nem.client.account.domain.mosaic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class OwnedMosaicsResponse {

    public final List<OwnedMosaic> data;

    @JsonCreator
    public OwnedMosaicsResponse(@JsonProperty("data") List<OwnedMosaic> data) {
        this.data = data;
    }
}
