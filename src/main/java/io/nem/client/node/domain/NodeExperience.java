package io.nem.client.node.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class NodeExperience {

    public final Node node;
    public final int syncs;
    public final Experience experience;

    @JsonCreator
    public NodeExperience(@JsonProperty("node") Node node,
                          @JsonProperty("syncs") int syncs,
                          @JsonProperty("experience") Experience experience) {
        this.node = node;
        this.syncs = syncs;
        this.experience = experience;
    }
}
