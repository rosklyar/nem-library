package io.nem.client.status;

import io.nem.client.status.domain.Heartbeat;
import io.nem.client.status.domain.Status;

public interface StatusClient {
    Heartbeat heartbeat();

    Status status();
}
