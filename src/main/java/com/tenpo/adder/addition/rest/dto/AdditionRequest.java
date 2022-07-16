package com.tenpo.adder.addition.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class AdditionRequest {

    @JsonProperty("input_a")
    @NotNull(message = "Should not be null")
    private Double inputA;

    @JsonProperty("input_b")
    @NotNull(message = "Should not be null")
    private Double inputB;
}
