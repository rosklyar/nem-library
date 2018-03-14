package com.github.rosklyar.client.mosaic.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class MosaicsMetaDataResponse {

    public final List<MosaicMetaData> data;

    @JsonCreator
    public MosaicsMetaDataResponse(@JsonProperty("data") List<MosaicMetaData> data) {
        this.data = data;
    }

}
