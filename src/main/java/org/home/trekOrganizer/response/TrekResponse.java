package org.home.trekOrganizer.response;


import lombok.Getter;
import lombok.Setter;
import org.home.trekOrganizer.model.Journey;
import org.home.trekOrganizer.model.Trek;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TrekResponse {

    private Long id;

    private String name;

    private Integer duration;

    private String description;

    private List<JourneyResponse> journeys;

    public TrekResponse(Trek trek) {
        this.id = trek.getId();
        this.name = trek.getName();
        this.duration = trek.getDuration();
        this.description = trek.getDescription();

        if (trek.getJourneys() != null) {
            journeys = new ArrayList<>();
            trek.getJourneys().stream().forEach(
                    journey -> {
                        journeys.add(new JourneyResponse(journey));
                    }
            );
        }
    }
}
