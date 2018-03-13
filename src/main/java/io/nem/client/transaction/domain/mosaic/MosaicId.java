package io.nem.client.transaction.domain.mosaic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class MosaicId {

    public final String namespaceId;
    public final String name;

    @JsonCreator
    public MosaicId(@JsonProperty("namespaceId") String namespaceId,
                    @JsonProperty("name") String name) {
        this.namespaceId = namespaceId;
        this.name = name;
    }
}
