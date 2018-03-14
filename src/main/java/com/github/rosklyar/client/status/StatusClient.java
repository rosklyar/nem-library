package com.github.rosklyar.client.status;

import com.github.rosklyar.client.status.domain.Heartbeat;
import com.github.rosklyar.client.status.domain.Status;

public interface StatusClient {
    Heartbeat heartbeat();

    Status status();
}
