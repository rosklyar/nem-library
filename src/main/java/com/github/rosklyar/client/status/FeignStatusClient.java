package com.github.rosklyar.client.status;

import com.github.rosklyar.client.status.domain.Heartbeat;
import com.github.rosklyar.client.status.domain.Status;
import feign.Headers;
import feign.RequestLine;


@Headers({"Accept: application/json"})
public interface FeignStatusClient extends StatusClient {

    @Override
    @RequestLine("GET /heartbeat")
    Heartbeat heartbeat();

    @Override
    @RequestLine("GET /status")
    Status status();
}
