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

    @OneToMany(mappedBy = "trek", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Journey> journeys;

    public Trek(TrekRequest trekRequest) {
        this.name = trekRequest.getName();
        this.duration = trekRequest.getDuration();
        this.description = trekRequest.getDescription();
    }

}
