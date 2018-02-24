package io.nem.client;

import io.nem.client.account.AccountClient;
import io.nem.client.status.StatusClient;

public interface NemClientFactory {
    StatusClient createStatusClient(String nodeUrl);
    AccountClient createAccountClient(String nodeUrl);
}
