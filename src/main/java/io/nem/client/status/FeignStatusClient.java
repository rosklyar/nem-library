package io.nem.client.status;

import feign.Headers;
import feign.RequestLine;
import io.nem.client.status.domain.Heartbeat;
import io.nem.client.status.domain.Status;

@Headers({"Accept: application/json"})
public interface FeignStatusClient extends StatusClient {

    @Override
    @RequestLine("GET /heartbeat")
    Heartbeat heartbeat();

    @Override
    @RequestLine("GET /status")
    Status status();
}
