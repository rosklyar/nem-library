package io.nem.client.transaction.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.nem.client.common.Hash;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = NemAnnounceResult.NemAnnounceResultBuilder.class)
public class NemAnnounceResult {

    public final int type;
    public final int code;
    public final String message;
    public final Hash transactionHash;
    public final Hash innerTransactionHash;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NemAnnounceResultBuilder {

    }
}
