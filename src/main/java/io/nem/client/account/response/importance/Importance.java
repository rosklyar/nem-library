package io.nem.client.account.response.importance;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = Importance.ImportanceBuilder.class)
public class Importance {

    public final double score;
    public final double ev;
    public final boolean isSet;
    public final long height;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ImportanceBuilder {

    }
}
