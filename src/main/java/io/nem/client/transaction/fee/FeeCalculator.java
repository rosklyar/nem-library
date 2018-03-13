package io.nem.client.transaction.fee;

import io.nem.client.transaction.domain.mosaic.MosaicTransfer;

import java.util.List;

public interface FeeCalculator {
    long fee(long microXemAmount, String payload);

    long fee(List<MosaicTransfer> mosaics, int times, String payload);

    long multisigAccountCreationFee();

    long multisigTransactionFee();

    long cosigningFee();

    long namespaceProvisionFee();

    long rentalFee(String parent, String namespace);

    long importanceTransferFee();

    long mosaicCreationFee();

    long mosaicRentalFee();
}
