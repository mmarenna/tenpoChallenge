package com.tenpo.adder.addition.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AdditionServiceImplTest {

    @InjectMocks
    AdditionServiceImpl additionService;

    @Test
    void calculateAdditionTest() {
        assertEquals(30, additionService.calculateAddition(10,20));
    }

}