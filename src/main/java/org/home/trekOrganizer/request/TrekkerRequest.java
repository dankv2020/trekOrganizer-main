package org.home.trekOrganizer.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrekkerRequest {

    @JsonProperty("full_name")
    @NotBlank(message = "full_name is required")
    private String fullName;

    @JsonProperty("email")
    @NotBlank(message = "email is required")
    @Email(message = "email is incorrect")
    private String email;

    @JsonProperty("trekking_experience")
    @NotNull(message = "trekking_experience is required")
    @Range(min = 0, max = 99, message = "trekking_experience can be [0..99]")
//    @Digits(integer=2, fraction=0, message="Experience can't be more than 99 years )")
    private Integer experience;

}
