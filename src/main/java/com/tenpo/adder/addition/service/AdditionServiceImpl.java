package com.tenpo.adder.addition.service;

import org.springframework.stereotype.Service;

@Service
public class AdditionServiceImpl implements AdditionService{

    @Override
    public Double calculateAddition(Double numberOne, Double numberTwo) {
        return Double.sum(numberOne, numberTwo);
    }
}
