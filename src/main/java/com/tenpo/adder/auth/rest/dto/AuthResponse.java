package com.tenpo.adder.auth.rest.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uri;

    public AuthResponse(String message) {
        this.message = message;
    }

}
