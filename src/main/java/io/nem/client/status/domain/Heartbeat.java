package io.nem.client.status.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Heartbeat {

    public final int code;
    public final int type;
    public final String message;

    @JsonCreator
    public Heartbeat(@JsonProperty("code") int code,
                     @JsonProperty("type") int type,
                     @JsonProperty("message") String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }
}
