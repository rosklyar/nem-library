package io.nem.client.node.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.nem.client.node.response.Endpoint;
import lombok.Value;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BootNodeRequest {
    public final ApplicationMetaData metaData;
    public final Endpoint endpoint;
    public final PrivateIdentity identity;
}
