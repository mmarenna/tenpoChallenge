package com.tenpo.adder.addition.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AdditionRequest {

    @JsonProperty("input_a")
    @NotNull(message = "Should not be null")
    private Long inputA;

    @JsonProperty("input_b")
    @NotNull(message = "Should not be null")
    private Long inputB;
}
