package org.home.trekOrganizer.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.home.trekOrganizer.model.Trek;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class JourneyRequest {

    @NotBlank(message = "name is required")
    private String name;

    @JsonProperty("trek_id")
    @NotNull(message = "trek_id is required")
    private Long trekId;

    @JsonProperty("manager_id")
    private Long managerId;


//    public JourneyRequest(@NotNull(message = "Name is required") String name) {
//        this.name = name;
//    }
}
