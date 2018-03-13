package io.nem.client.account.domain.importance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class ImportanceResponse {

    public final List<ImportanceInfo> data;

    @JsonCreator
    public ImportanceResponse(@JsonProperty("data") List<ImportanceInfo> data) {
        this.data = data;
    }
}
