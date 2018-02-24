package io.nem.client.account.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class AccountMetaDataPair {

    public final AccountInfo account;
    public final AccountMetaData meta;

    @JsonCreator
    public AccountMetaDataPair(@JsonProperty("account") AccountInfo account,
                               @JsonProperty("meta") AccountMetaData meta) {
        this.account = account;
        this.meta = meta;
    }
}
