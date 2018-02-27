package io.nem.client.account.response.history;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = HistoryData.HistoryDataBuilder.class)
public class HistoryData {

    public final long height;
    public final String address;
    public final long balance;
    public final long vestedBalance;
    public final long unvestedBalance;
    public final double importance;
    public final double pageRank;

    @JsonPOJOBuilder(withPrefix = "")
    public static class HistoryDataBuilder {

    }
}
