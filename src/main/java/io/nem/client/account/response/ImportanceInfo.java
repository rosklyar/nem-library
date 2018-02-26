package io.nem.client.account.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class ImportanceInfo {

    public final String address;
    public final Importance importance;

    @JsonCreator
    public ImportanceInfo(@JsonProperty("address") String address,
                          @JsonProperty("importance") Importance importance) {
        this.address = address;
        this.importance = importance;
    }
}
