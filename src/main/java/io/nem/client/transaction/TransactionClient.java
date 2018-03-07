package io.nem.client.transaction;

import io.nem.client.common.MosaicTransfer;
import io.nem.client.transaction.response.NemAnnounceResult;

import java.util.List;

public interface TransactionClient {

    NemAnnounceResult transferNem(String privateKey, String toAddress, long microXemAmount, String message, int timeToLiveInSeconds);

    NemAnnounceResult transferMosaics(String privateKey, String toAddress, String message, int timeToLiveInSeconds, List<MosaicTransfer> mosaics, int times);

    NemAnnounceResult createMultisigAccount(String privateKey, int timeToLiveInSeconds, List<String> cosignatories, int minCosignatories);

}
