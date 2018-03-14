package com.github.rosklyar.client.node.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BootNodeRequest {
    public final ApplicationMetaData metaData;
    public final Endpoint endpoint;
    public final PrivateIdentity identity;
}
