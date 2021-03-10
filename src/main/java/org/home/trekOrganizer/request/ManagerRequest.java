package org.home.trekOrganizer.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerRequest {

    @NotBlank (message = "name is required")
    private String name;

    @NotBlank (message = "login is required")
    private String login;

    @NotNull (message = "password is required")
    private String password;

    @NotBlank (message = "role is required")
    private String role;

    private Boolean enabled;


}
