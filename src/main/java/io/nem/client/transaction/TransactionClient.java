package io.nem.client.transaction;

import io.nem.client.transaction.response.NemAnnounceResult;

public interface TransactionClient {

    NemAnnounceResult transferNem(String privateKey, String toAddress, double amount, String message, int timeToLiveInSeconds);

}
