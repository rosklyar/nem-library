package io.nem.client.account.domain.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class UnconfirmedTransactionMetaData {

    public final String data;

    @JsonCreator
    public UnconfirmedTransactionMetaData(@JsonProperty("data") String data) {
        this.data = data;
    }
}
