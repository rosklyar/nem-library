package com.github.rosklyar.client.account.domain.harvest;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = HarvestInfo.HarvestInfoBuilder.class)
public class HarvestInfo {

    public final long timeStamp;
    public final long difficulty;
    public final long totalFee;
    public final long id;
    public final long height;

    @JsonPOJOBuilder(withPrefix = "")
    public static class HarvestInfoBuilder {

    }
}
