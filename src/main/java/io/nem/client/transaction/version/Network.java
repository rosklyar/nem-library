package io.nem.client.transaction.version;

public enum Network {
    MAIN(0x68), TEST(0x98), MIJIN(0x60);

    public final int code;

    Network(int code) {
        this.code = code;
    }
}
