package io.nem.client.account.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class UnconfirmedTransactions {

    public final List<UnconfirmedTransactionMetaDataPair> data;

    @JsonCreator
    public UnconfirmedTransactions(@JsonProperty("data") List<UnconfirmedTransactionMetaDataPair> data) {
        this.data = data;
    }
}
