package org.home.trekOrganizer.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.home.trekOrganizer.model.Trek;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class JourneyRequest {

    @NotNull(message = "Name is required")
    private String name;

    private Trek trek;

}
