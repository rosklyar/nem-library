package io.nem.client.transaction.fee;

import io.nem.client.account.AccountClient;
import io.nem.client.account.response.mosaic.OwnedMosaicsResponse;
import io.nem.client.common.transaction.mosaic.MosaicTransfer;
import io.nem.client.mosaic.MosaicClient;
import io.nem.client.mosaic.response.MosaicMetaData;
import io.nem.client.mosaic.response.MosaicsMetaDataResponse;

import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.math.DoubleMath.roundToLong;
import static java.lang.Math.*;
import static java.math.BigInteger.TEN;
import static java.math.BigInteger.valueOf;
import static java.math.RoundingMode.DOWN;

public class DefaultFeeCalculator implements FeeCalculator {

    private final int feeUnit = 50000;
    private final long maxMosaicQuantity = 9000000000000000L;
    private final int smallBusinessMosaicSupply = 10000;
    private final int nemDivisibility = 6;

    private final MosaicClient mosaicClient;
    private final AccountClient accountClient;

    public DefaultFeeCalculator(MosaicClient mosaicClient, AccountClient accountClient) {
        this.mosaicClient = mosaicClient;
        this.accountClient = accountClient;
    }

    @Override
    public long fee(long microXemAmount, String payload) {
        long progressiveFee = calculateXemTransferFee(microXemAmount);
        long messageFee = getMessageFee(payload);
        return (progressiveFee + messageFee) * feeUnit;
    }

    @Override
    public long fee(List<MosaicTransfer> mosaics, int times, String payload) {
        long messageFee = getMessageFee(payload);
        long mosaicsFee = mosaics.stream().mapToLong(mosaic -> mosaicFee(mosaic, times)).sum();
        return (mosaicsFee + messageFee) * feeUnit;
    }

    @Override
    public long multisigAccountCreationFee() {
        return 10L * feeUnit;
    }

    @Override
    public long multisigTransactionFee() {
        return 3L * feeUnit;
    }

    @Override
    public long cosigningFee() {
        return 3L * feeUnit;
    }

    @Override
    public long namespaceProvisionFee() {
        return 3L * feeUnit;
    }

    @Override
    public long rentalFee(String parent, String namespace) {
        long microXemsInXem = TEN.pow(nemDivisibility).longValue();
        return isNullOrEmpty(parent) ? 100L * microXemsInXem : 10 * microXemsInXem;
    }

    @Override
    public long importanceTransferFee() {
        return 3L * feeUnit;
    }

    private long mosaicFee(MosaicTransfer mosaicTransfer, int times) {
        String namespaceId = mosaicTransfer.mosaicId.namespaceId;
        String mosaicName = mosaicTransfer.mosaicId.name;
        MosaicsMetaDataResponse mosaicsMetaDataResponse = mosaicClient.mosaics(namespaceId, null, 100);
        MosaicMetaData mosaicMetaData = mosaicsMetaDataResponse.data.stream()
                .filter(mmd -> mmd.mosaic.id.name.equals(mosaicName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("No mosaic definition with name=%s found in namespace=%s", mosaicName, namespaceId)));
        int divisibility = mosaicMetaData.mosaic.properties.stream().filter(pr -> pr.name.equals("divisibility")).findFirst().map(pr -> Integer.valueOf(pr.value)).orElseThrow(RuntimeException::new);
        OwnedMosaicsResponse ownedMosaicsResponse = accountClient.ownedMosaics(accountClient.getFromPublicKey(mosaicMetaData.mosaic.creator).account.address);

        long mosaicQuantity = ownedMosaicsResponse.data.stream().filter(ownedMosaic -> ownedMosaic.mosaicId.name.equals(mosaicName)).findFirst().map(ownedMosaic -> ownedMosaic.quantity).orElseThrow(RuntimeException::new);
        if (isSmallBusinessMosaic(divisibility, mosaicQuantity)) {
            return 1L;
        }

        long supplyRelatedAdjustment = roundToLong(0.8 * log(valueOf(maxMosaicQuantity).divide(valueOf(mosaicQuantity)).doubleValue()), DOWN);
        long xemEquivalent = roundToLong(valueOf(8999999999L * mosaicTransfer.quantity * times).divide(valueOf(mosaicQuantity)).doubleValue(), DOWN);
        long microXemEquivalent = xemEquivalent * TEN.pow(nemDivisibility).longValue();
        long xemFee = calculateXemTransferFee(microXemEquivalent);
        return max(1L, xemFee - supplyRelatedAdjustment);
    }

    private boolean isSmallBusinessMosaic(int divisibility, long mosaicQuantity) {
        return divisibility == 0 && mosaicQuantity <= smallBusinessMosaicSupply;
    }

    private long getMessageFee(String payload) {
        return isNullOrEmpty(payload) ?
                0 :
                (payload.length() / 32 + 1);
    }

    private long calculateXemTransferFee(long microXemAmount) {
        return min(25, max(1L, valueOf(microXemAmount).divide(TEN.pow(nemDivisibility + 4)).longValue()));
    }
}
