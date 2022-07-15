package com.tenpo.adder.auth.rest.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class RegisterRequest {

    @NotEmpty(message = "Should not be null")
    private String username;
    @NotEmpty(message = "Should not be null")
    private String email;
    @NotEmpty(message = "Should not be null")
    private String password;

}
