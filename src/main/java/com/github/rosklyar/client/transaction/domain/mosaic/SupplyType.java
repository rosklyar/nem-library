package com.github.rosklyar.client.transaction.domain.mosaic;

public enum SupplyType {
    INCREASE(1), DECREASE(2);
    public final int type;

    SupplyType(int type) {
        this.type = type;
    }
}
