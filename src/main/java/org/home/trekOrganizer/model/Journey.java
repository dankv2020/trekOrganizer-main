package org.home.trekOrganizer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "journey")
public class Journey {

    @Id
    @GeneratedValue
    private Long id;

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
}
