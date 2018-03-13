package io.nem.client.transaction.version;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Network {

    public final int code;
    public final String rentalFeeSink;
    public final String creationFeeSink;

    public Network(int code, String rentalFeeSink, String creationFeeSink) {
        this.code = code;
        this.rentalFeeSink = rentalFeeSink;
        this.creationFeeSink = creationFeeSink;
    }
}
