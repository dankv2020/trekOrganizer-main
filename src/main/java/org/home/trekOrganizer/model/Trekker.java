package org.home.trekOrganizer.model;

import lombok.*;
import org.home.trekOrganizer.request.TrekkerRequest;

import javax.persistence.*;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name = "trekker")
public class Trekker {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String fullName;

    @Column
    private String email;

    @Column
    private Integer experience;

    public Trekker(TrekkerRequest trekkerRequest) {
        this.fullName = trekkerRequest.getFullName();
        this.email = trekkerRequest.getEmail();
        this.experience = trekkerRequest.getExperience();
    }
}
