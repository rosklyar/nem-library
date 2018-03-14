package com.github.rosklyar.client.transaction;

import com.github.rosklyar.client.mosaic.domain.Levy;
import com.github.rosklyar.client.transaction.domain.NemAnnounceResult;
import com.github.rosklyar.client.transaction.domain.importance.Action;
import com.github.rosklyar.client.transaction.domain.mosaic.MosaicId;
import com.github.rosklyar.client.transaction.domain.mosaic.MosaicProperties;
import com.github.rosklyar.client.transaction.domain.mosaic.MosaicTransfer;

import java.util.List;

public interface TransactionClient {

    NemAnnounceResult transferNem(String privateKey, String toAddress, long microXemAmount, String message, int timeToLiveInSeconds);

    NemAnnounceResult transferMosaics(String privateKey, String toAddress, List<MosaicTransfer> mosaics, int times, String message, int timeToLiveInSeconds);

    NemAnnounceResult createMultisigAccount(String privateKey, List<String> cosignatories, int minCosignatories, int timeToLiveInSeconds);

    NemAnnounceResult addCosignatoriesToMultisigAccount(String privateKey, List<String> cosignatories, int relativeChange, String multisigPublicKey, int timeToLiveInSeconds);

    NemAnnounceResult removeCosignatoriesFromMultisigAccount(String privateKey, List<String> cosignatories, int relativeChange, String multisigPublicKey, int timeToLiveInSeconds);

    NemAnnounceResult multisigTransferNem(String privateKey, String toAddress, long microXemAmount, String message, String multisigPublicKey, int timeToLiveInSeconds);

    NemAnnounceResult multisigTransferMosaics(String privateKey, String toAddress, List<MosaicTransfer> mosaics, int times, String message, String multisigPublicKey, int timeToLiveInSeconds);

    NemAnnounceResult cosignTransaction(String privateKey, String transactionHash, String multisigAddress, int timeToLiveInSeconds);

    NemAnnounceResult createNamespace(String privateKey, String parentNamespace, String namespace, int timeToLiveInSeconds);

    NemAnnounceResult importanceTransfer(String privateKey, Action action, String remoteAccountPublicKey, int timeToLiveInSeconds);

    NemAnnounceResult createMosaic(String privateKey, MosaicId mosaicId, String mosaicDescription, MosaicProperties mosaicProperties, Levy levy, int timeToLiveInSeconds);

}
