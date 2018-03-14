package com.github.rosklyar.client.account.domain.history;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class HistoryResponse {

    public final List<HistoryData> data;

    @JsonCreator
    public HistoryResponse(@JsonProperty("data") List<HistoryData> data) {
        this.data = data;
    }
}
