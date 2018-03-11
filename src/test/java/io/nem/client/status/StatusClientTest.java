package io.nem.client.status;

import io.nem.client.DefaultNemClientFactory;
import io.nem.client.status.response.Heartbeat;
import io.nem.client.status.response.Status;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.netflix.config.ConfigurationManager.getConfigInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StatusClientTest {

    private final StatusClient statusClient = new DefaultNemClientFactory().createStatusClient();

    @BeforeAll
    static void init() {
        getConfigInstance().setProperty("statusApi.ribbon.listOfServers", "153.122.112.137:7890");
        getConfigInstance().setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", 20000);
    }

    @Test
    void returnHeartbeatForNode() {
        Heartbeat heartbeat = new Heartbeat(1, 2, "ok");
        assertEquals(heartbeat, statusClient.heartbeat());
    }

    @Test
    void returnStatusForNode() {
        Status status = new Status(6, 4, "status");
        assertEquals(status, statusClient.status());
    }
}