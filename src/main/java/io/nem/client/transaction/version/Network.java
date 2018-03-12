package io.nem.client.transaction.version;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Network {

    public final int code;
    public final String rentalFeeSink;

    public Network(int code, String rentalFeeSink) {
        this.code = code;
        this.rentalFeeSink = rentalFeeSink;
    }
}
