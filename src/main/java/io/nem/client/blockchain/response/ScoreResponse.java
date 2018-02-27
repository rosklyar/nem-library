package io.nem.client.blockchain.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class ScoreResponse {

    public final String score;

    @JsonCreator
    public ScoreResponse(@JsonProperty("score") String score) {
        this.score = score;
    }

}
