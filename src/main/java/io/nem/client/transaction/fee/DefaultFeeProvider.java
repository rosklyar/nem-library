package io.nem.client.transaction.fee;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.Math.floorDiv;
import static java.lang.Math.min;

public class DefaultFeeProvider implements FeeProvider {

    private final int minimalFee = 50000;

    @Override
    public long fee(long amount, String payload) {
        long numberOf10thousands = floorDiv(amount, 10000000000L);
        long progressiveFee = numberOf10thousands > 0 ? numberOf10thousands * minimalFee : minimalFee;
        int feeCap = 1250000;
        long messageFee = isNullOrEmpty(payload) ?
                0 :
                (payload.length() / 32 + 1) * 50000;
        return min(progressiveFee, feeCap) + messageFee;
    }
}
