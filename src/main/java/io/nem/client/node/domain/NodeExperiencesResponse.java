package io.nem.client.node.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class NodeExperiencesResponse {

    public final List<NodeExperience> data;

    @JsonCreator
    public NodeExperiencesResponse(@JsonProperty("data") List<NodeExperience> data) {
        this.data = data;
    }
}
