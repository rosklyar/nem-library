package io.nem.client.account.response.mosaic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class MosaicsResponse {

    public final List<MosaicInfo> data;

    @JsonCreator
    public MosaicsResponse(@JsonProperty("data") List<MosaicInfo> data) {
        this.data = data;
    }
}
