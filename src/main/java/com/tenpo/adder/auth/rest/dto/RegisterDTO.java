package com.tenpo.adder.auth.rest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RegisterDTO {

    @NotEmpty(message = "Should not be null")
    private String username;
    @NotEmpty(message = "Should not be null")
    private String email;
    @NotEmpty(message = "Should not be null")
    private String password;

}
