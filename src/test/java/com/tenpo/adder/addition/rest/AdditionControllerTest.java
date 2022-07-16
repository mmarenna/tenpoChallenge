package com.tenpo.adder.addition.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.adder.addition.rest.dto.AdditionRequest;
import com.tenpo.adder.addition.service.AdditionService;
import com.tenpo.adder.auth.security.CustomUserDetailService;
import com.tenpo.adder.history.service.HistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.tenpo.adder.utils.ApiTenpoConstants.ADDITION_URI;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AdditionController.class)
class AdditionControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private CustomUserDetailService customUserDetailService;

    @MockBean
    private AdditionService additionService;
    @MockBean
    private HistoryService historyService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser(value = "testUser")
    void additionOkTest() throws Exception {

        when(additionService.calculateAddition(10.0, 20.0)).thenReturn(30.0);

        final AdditionRequest additionRequest = AdditionRequest.builder()
                .inputA(10.0)
                .inputB(20.0)
                .build();

        mvc.perform(post("/api/addition")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(additionRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.addition_result").value("30.0"))
                    .andReturn();

        verify(additionService).calculateAddition(10.0, 20.0);
        verify(historyService).createHistory(ADDITION_URI, "AdditionResponse(result=30.0)");
    }

    @Test
    void additionWithoutLoginTest() throws Exception {

        when(additionService.calculateAddition(10.0, 20.0)).thenReturn(30.0);

        final AdditionRequest additionRequest = AdditionRequest.builder()
                .inputA(10.0)
                .inputB(20.0)
                .build();

        mvc.perform(post("/api/addition")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(additionRequest)))
                    .andExpect(status().isForbidden())
                    .andExpect(jsonPath("$.message").value("Access is denied"))
                    .andReturn();
    }

    @Test
    @WithMockUser(value = "testUser")
    void additionBadRequestTest() throws Exception {
        final AdditionRequest additionRequest = AdditionRequest.builder()
                .inputA(10.0)
                .build();

        mvc.perform(post("/api/addition")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(additionRequest)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.inputB").value("Should not be null"))
                    .andReturn();
    }

}