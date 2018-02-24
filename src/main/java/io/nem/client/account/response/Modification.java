package io.nem.client.account.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Modification {

    public final int modificationType;
    public final String cosignatoryAccount;

    @JsonCreator
    public Modification(@JsonProperty("modificationType") int modificationType,
                        @JsonProperty("cosignatoryAccount") String cosignatoryAccount) {
        this.modificationType = modificationType;
        this.cosignatoryAccount = cosignatoryAccount;
    }
}
