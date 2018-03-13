package io.nem.client.transaction.domain.mosaic;

public enum LevyType {
    ABSOLUTE(1), PERCENTILE(2);
    public final int type;

    LevyType(int type) {
        this.type = type;
    }
}
