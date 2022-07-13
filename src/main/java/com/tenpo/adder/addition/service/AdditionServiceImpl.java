package com.tenpo.adder.addition.service;

import org.springframework.stereotype.Service;

@Service
public class AdditionServiceImpl implements AdditionService{

    @Override
    public Long calculateAddition(Long numberOne, Long numberTwo) {
        return Math.addExact(numberOne, numberTwo);
    }
}
