package io.nem.client.account.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = Namespace.NamespaceBuilder.class)
public class Namespace {

    public final String fqn;
    public final String owner;
    public final long height;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NamespaceBuilder {

    }
}
