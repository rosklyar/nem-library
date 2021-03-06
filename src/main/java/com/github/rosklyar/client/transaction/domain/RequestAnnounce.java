package com.github.rosklyar.client.transaction.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestAnnounce {
    public final String data;
    public final String signature;
}
