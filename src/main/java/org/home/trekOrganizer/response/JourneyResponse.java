package org.home.trekOrganizer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.home.trekOrganizer.model.Journey;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class JourneyResponse {

    private Long id;

    private String name;

    @JsonProperty("trek_name")
    private String trekName;

    @JsonProperty("trekkers")
    private List<String> trekkerNames;

    public JourneyResponse(Journey journey) {

        this.id = journey.getId();
        this.name = journey.getName();
        this.trekName = journey.getTrek().getName();

        if (journey.getTrekkers() != null) {
            this.trekkerNames = journey.getTrekkers().stream().
                    map(x->x.getFullName()).collect(Collectors.toList());
        }

    }
}
