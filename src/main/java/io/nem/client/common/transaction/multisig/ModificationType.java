package io.nem.client.common.transaction.multisig;

public enum ModificationType {
    ADD_COSIGNATORY(1), REMOVE_COSIGNATORY(2);
    public final int type;

    ModificationType(int type) {
        this.type = type;
    }
}
