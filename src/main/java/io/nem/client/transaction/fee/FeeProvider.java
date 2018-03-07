package io.nem.client.transaction.fee;

import io.nem.client.common.MosaicTransfer;

import java.util.List;

public interface FeeProvider {
    long fee(long microXemAmount, String payload);

    long fee(List<MosaicTransfer> mosaics, int times, String payload);

    long multisigAccountCreationFee();

    long multisigTransactionFee();

    long cosigningFee();
}
