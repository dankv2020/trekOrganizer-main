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

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "duration is required")
    @Range(min = 0, max = 49, message = "duration can be [0..49]")
    private Integer duration;

    @NotBlank(message = "description is required")
    private String description;

}
