package io.nem.client.account.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrivateKey {
    public final String value;
}
