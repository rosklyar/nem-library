package io.nem.client.status;

import io.nem.client.status.response.Heartbeat;
import io.nem.client.status.response.Status;

public interface StatusClient {
    Heartbeat heartbeat();

    Status status();
}
