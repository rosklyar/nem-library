package com.github.rosklyar.client.account.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
@Builder
@JsonDeserialize(builder = AccountMetaData.AccountMetaDataBuilder.class)
public class AccountMetaData {

    public final Status status;
    public final RemoteStatus remoteStatus;
    public final List<AccountInfo> cosignatoryOf;
    public final List<AccountInfo> cosignatories;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AccountMetaDataBuilder {

    }

    public enum Status {
        UNKNOWN, LOCKED, UNLOCKED
    }

    public enum RemoteStatus {
        REMOTE, ACTIVATING, ACTIVE, DEACTIVATING, INACTIVE
    }
}
