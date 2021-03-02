package org.home.trekOrganizer.model;

import lombok.*;
import org.home.trekOrganizer.request.TrekRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "trek")
public class Trek {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer duration;

    @Column
    private String description;

    @OneToMany(mappedBy = "trek", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Journey> journeys = new ArrayList<>();

    public Trek(TrekRequest trekRequest) {
        this.name = trekRequest.getName();
        this.duration = trekRequest.getDuration();
        this.description = trekRequest.getDescription();
    }

    /*public Trek addJourney(Journey journey) {
        journeys.add(journey);
        journey.setTrek(this);
        return this;
    }

    public Trek removeJourney(Journey journey) {
        journeys.remove(journey);
        journey.setTrek(null);
        return this;
    }*/
}
