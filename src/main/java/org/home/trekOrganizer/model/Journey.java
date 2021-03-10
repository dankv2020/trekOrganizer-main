package org.home.trekOrganizer.model;

import lombok.*;
import org.home.trekOrganizer.request.JourneyRequest;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "journey")
public class Journey {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @ManyToOne
//            (cascade = CascadeType.ALL)
//  (optional = false) //trek must exist
    @JoinColumn(name = "trek_id")
    private Trek trek;

    //owning side
    @ManyToMany
    @JoinTable(
            name = "journey_trekker",
            joinColumns = @JoinColumn(name = "journey_id"),
            inverseJoinColumns = @JoinColumn(name = "trekker_id"))
    private List<Trekker> trekkers;

    public Journey(JourneyRequest journeyRequest) {
        this.name = journeyRequest.getName();
    }
}
