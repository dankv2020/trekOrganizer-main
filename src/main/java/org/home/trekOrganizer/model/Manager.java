package org.home.trekOrganizer.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.home.trekOrganizer.request.ManagerRequest;

import javax.persistence.*;
import java.util.List;


@Setter @Getter @ToString
@NoArgsConstructor

@Entity
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue
    Long id;

    @Column
    private String name;

    @Column(unique = true)
    private String login;

    @Column
    private char[] password;

    @Column
    private String role;

    @Column
    private Boolean enabled;

    @OneToMany (mappedBy = "manager", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Journey> journeys;

    public Manager(ManagerRequest managerRequest) {
        this.name = managerRequest.getName();
        this.login = managerRequest.getLogin();
        this.password = managerRequest.getPassword().toCharArray();
        this.role = managerRequest.getRole();
        this.enabled = managerRequest.getEnabled();

    }
}
