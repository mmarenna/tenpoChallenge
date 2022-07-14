package com.tenpo.adder.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class DetailErrors {

    private Date timestamp;
    private String message;
    private String details;
}
