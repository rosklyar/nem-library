package com.github.rosklyar.client.account.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class UnlockedInfo {

    public final long numUnlocked;
    public final long maxUnlocked;

    @JsonCreator
    public UnlockedInfo(@JsonProperty("num-unlocked") long numUnlocked,
                        @JsonProperty("max-unlocked") long maxUnlocked) {
        this.numUnlocked = numUnlocked;
        this.maxUnlocked = maxUnlocked;
    }
}
