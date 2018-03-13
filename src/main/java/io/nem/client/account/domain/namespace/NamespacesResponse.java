package io.nem.client.account.domain.namespace;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nem.client.mosaic.domain.Namespace;
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
