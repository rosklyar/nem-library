package com.github.rosklyar.client.account.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = AccountInfo.AccountInfoBuilder.class)
public class AccountInfo {
    public final String address;
    public final long balance;
    public final long vestedBalance;
    public final double importance;
    public final String publicKey;
    public final String label;
    public final long harvestedBlocks;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AccountInfoBuilder {

    }
}
