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
    @NotBlank(message = "Full name is required")
    private String fullName;

    @JsonProperty("email")
    @Email(message = "Email is required")
    private String email;

    @JsonProperty("trekking_experience")
    @NotNull(message = "Experience is required")
    @Range(min = 0, max = 99, message = "Experience can be [0..99]")
//    @Digits(integer=2, fraction=0, message="Experience can't be more than 99 years )")
    private Integer experience;

}
