package io.nem.client.account.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Message {
    public final String payload;
    public final int type;

    @JsonCreator
    public Message(@JsonProperty("payload") String payload,
                   @JsonProperty("type") int type) {
        this.payload = payload;
        this.type = type;
    }
}
