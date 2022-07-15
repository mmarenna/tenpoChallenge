package com.tenpo.adder.history.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.adder.auth.security.CustomUserDetailService;
import com.tenpo.adder.history.rest.dto.HistoriesResponse;
import com.tenpo.adder.history.service.HistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.tenpo.adder.utils.TestUtils.PAGINATED_HISTORIES_PAGE0_SIZE2;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(HistoryController.class)
class HistoryControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private CustomUserDetailService customUserDetailService;

    ObjectMapper objectMapper;

    @MockBean
    private HistoryService historyService;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void getHistoriesTest() throws Exception {

        final HistoriesResponse historiesResponse = HistoriesResponse.builder()
                            .pageNumber(0)
                            .pageSize(2)
                            .histories(PAGINATED_HISTORIES_PAGE0_SIZE2)
                            .build();

        when(historyService.getAllHistories(0,2)).thenReturn(historiesResponse);

        mvc.perform(get("/api/history")
                .param("pageNumber", "0")
                .param("pageSize", "2") )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.histories[0].id").value(1L))
                .andExpect(jsonPath("$.histories[0].response").value(historiesResponse.getHistories().get(0).getResponse()))
                .andExpect(jsonPath("$.pageNumber").value(historiesResponse.getPageNumber()))
                .andExpect(jsonPath("$.pageSize").value(historiesResponse.getPageSize()))
                .andExpect(jsonPath("$.histories[1].id").value(2L))
                .andExpect(jsonPath("$.histories[1].response").value(historiesResponse.getHistories().get(1).getResponse()))
                .andExpect(jsonPath("$.pageNumber").value(historiesResponse.getPageNumber()))
                .andExpect(jsonPath("$.pageSize").value(historiesResponse.getPageSize()));

        verify(historyService).getAllHistories(0,2);
    }
}