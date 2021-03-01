package org.home.trekOrganizer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.home.trekOrganizer.model.Trekker;

@Getter @Setter
public class TrekkerResponse {

    //    @JsonIgnore
    private Long id;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("trekking_experience")
    private Integer experience;

    @JsonProperty
    private String email;

    public TrekkerResponse(Trekker trekker) {
        this.id = trekker.getId();
        this.fullName = trekker.getFullName();
        this.experience = trekker.getExperience();
        this.email = trekker.getEmail();
    }
}
