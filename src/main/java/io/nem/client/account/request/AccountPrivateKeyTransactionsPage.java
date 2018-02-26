package io.nem.client.account.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountPrivateKeyTransactionsPage {
    public final String value;
    public final String hash;
    public final String id;
}
