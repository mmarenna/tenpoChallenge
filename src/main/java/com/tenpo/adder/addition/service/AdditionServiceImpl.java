package com.tenpo.adder.addition.service;

import org.springframework.stereotype.Service;

@Service
public class AdditionServiceImpl implements AdditionService{

    @Override
    public Integer calculateAddition(Integer numberOne, Integer numberTwo) {
        return Math.addExact(numberOne, numberTwo);
    }
}
