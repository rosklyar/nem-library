package io.nem.client.transaction.version;


public enum Network {
    MAIN(0x68, "NAMESPACEWH4MKFMBCVFERDPOOP4FK7MTBXDPZZA"),
    TEST(0x98, "TAMESPACEWH4MKFMBCVFERDPOOP4FK7MTDJEYP35"),
    MIJIN(0x60, "UNKNOWN");

    public final int code;
    public final String rentalFeeSink;

    Network(int code, String rentalFeeSink) {
        this.code = code;
        this.rentalFeeSink = rentalFeeSink;
    }
}
