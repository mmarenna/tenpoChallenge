package com.tenpo.adder.auth.rest.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    private String usernameOrEmail;
    private String password;

}
