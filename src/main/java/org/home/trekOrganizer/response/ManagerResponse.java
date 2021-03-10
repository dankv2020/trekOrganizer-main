package org.home.trekOrganizer.response;

import lombok.Getter;
import lombok.Setter;
import org.home.trekOrganizer.model.Manager;
import org.home.trekOrganizer.model.Trek;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ManagerResponse {

    private Long id;

    private String name;

    private String login;

    private String role;

    private Boolean enabled;

    private List<JourneyResponse> journeys;

    public ManagerResponse(Manager manager) {
        this.id = manager.getId();
        this.name = manager.getName();
        this.login = manager.getLogin();
        this.role = manager.getRole();
        this.enabled = manager.getEnabled();

        if (manager.getJourneys() != null) {
            journeys = new ArrayList<>();
            manager.getJourneys().stream().forEach(
                    journey -> {
                        journeys.add(new JourneyResponse(journey));
                    }
            );
        }
    }
}