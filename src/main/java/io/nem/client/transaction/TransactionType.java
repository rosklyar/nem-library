package io.nem.client.transaction;

public enum TransactionType {

    TRANSFER_NEM(0x0101),
    TRANSFER_MOSAICS(0x0101),
    IMPORTANCE_TRANSFER_TRANSACTION(0x0801),
    MULTISIG_AGGREGATE_MODIFICATION(0x1001),
    MULTISIG_SIGNATURE(0x1002),
    MULTISIG_TRANSACTION(0x1004),
    PROVISION_NAMESPACE(0x2001),
    MOSAIC_DEFINITION_CREATION(0x4001),
    MOSAIC_SUPPLY_CHANGE(0x4002);

    public final int type;

    TransactionType(int type) {
        this.type = type;
    }
}
