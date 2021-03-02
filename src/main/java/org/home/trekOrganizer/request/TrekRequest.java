package org.home.trekOrganizer.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrekRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Duration is required")
    @Range(min = 0, max = 49, message = "Duration can be [0..49]")
    private Integer duration;

    @NotBlank(message = "Description is required")
    private String description;

}
