package io.nem.client.transaction.domain.multisig;

public enum ModificationType {
    ADD_COSIGNATORY(1), REMOVE_COSIGNATORY(2);
    public final int type;

    ModificationType(int type) {
        this.type = type;
    }
}
