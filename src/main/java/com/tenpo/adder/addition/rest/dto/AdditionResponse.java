package com.tenpo.adder.addition.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdditionResponse {

    public AdditionResponse(Long result) {
        this.result = result;
    }

    @JsonProperty("addition_result")
    private Long result;

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}
