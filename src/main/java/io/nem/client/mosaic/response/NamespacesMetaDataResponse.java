package io.nem.client.mosaic.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class NamespacesMetaDataResponse {

    public final List<NamespaceMetaData> data;

    @JsonCreator
    public NamespacesMetaDataResponse(@JsonProperty("data") List<NamespaceMetaData> data) {
        this.data = data;
    }

}
