package com.tenpo.adder.addition.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AdditionResponse {

    @JsonProperty("addition_result")
    private Double result;

}
