package io.nem.client.account.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = KeyPair.KeyPairBuilder.class)
public class KeyPair {

    public final String privateKey;
    public final String address;
    public final String publicKey;

    @JsonPOJOBuilder(withPrefix = "")
    static class KeyPairBuilder {

    }
}
