package io.nem.client.account.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class NamespacesResponse {

    public final List<Namespace> data;

    @JsonCreator
    public NamespacesResponse(@JsonProperty("data") List<Namespace> data) {
        this.data = data;
    }
}
