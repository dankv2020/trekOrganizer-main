package org.home.trekOrganizer.response;


import lombok.Getter;
import lombok.Setter;
import org.home.trekOrganizer.model.Trek;

@Setter
@Getter
public class TrekResponse {

    private Long id;

    private String name;

    private Integer duration;

    private String description;

    public TrekResponse(Trek trek) {
        this.id = trek.getId();
        this.name = trek.getName();
        this.duration = trek.getDuration();
        this.description = trek.getDescription();
    }
}
