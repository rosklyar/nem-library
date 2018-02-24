package io.nem.client.account.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = Signature.SignatureBuilder.class)
public class Signature {

    public final long timeStamp;
    public final String signature;
    public final long fee;
    public final int type;
    public final long deadline;
    public final long version;
    public final String signer;
    public final Hash otherHash;
    public final String otherAccount;

    @JsonPOJOBuilder(withPrefix = "")
    public static class SignatureBuilder {

    }
}
