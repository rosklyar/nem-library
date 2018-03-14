package com.github.rosklyar.client.account.domain.transaction;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.rosklyar.client.account.domain.Hash;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = TransactionMetaData.TransactionMetaDataBuilder.class)
public class TransactionMetaData {

    public final long id;
    public final long height;
    public final Hash hash;
    public final Hash innerHash;

    @JsonPOJOBuilder(withPrefix = "")
    public static class TransactionMetaDataBuilder{

    }
}
