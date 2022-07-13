package com.tenpo.adder.auth.rest.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

public class AuthResponse {

    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uri;

    public AuthResponse(String message) {
        this.message = message;
    }

    public AuthResponse(String message, String uri) {
        this.message = message;
        this.uri = uri;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "message='" + message + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
