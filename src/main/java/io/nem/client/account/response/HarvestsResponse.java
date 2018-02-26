package io.nem.client.account.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class HarvestsResponse {

    public final List<HarvestInfo> data;

    @JsonCreator
    public HarvestsResponse(@JsonProperty("data") List<HarvestInfo> data) {
        this.data = data;
    }
}
