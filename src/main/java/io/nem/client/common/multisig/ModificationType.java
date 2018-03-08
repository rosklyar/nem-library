package io.nem.client.common.multisig;

public enum ModificationType {
    ADD_COSIGNATORY(1), REMOVE_COSIGNATORY(2);
    public final int type;

    ModificationType(int type) {
        this.type = type;
    }
}
