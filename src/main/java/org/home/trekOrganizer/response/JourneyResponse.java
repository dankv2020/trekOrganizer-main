package org.home.trekOrganizer.response;

import lombok.Getter;
import lombok.Setter;
import org.home.trekOrganizer.model.Journey;

@Setter
@Getter
public class JourneyResponse {

    private Long id;

    private String name;

    public JourneyResponse(Journey journey) {

        this.id = journey.getId();
        this.name = journey.getName();
    }
}
