package com.tenpo.adder.record.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.adder.auth.security.CustomUserDetailService;
import com.tenpo.adder.record.rest.dto.RecordsResponse;
import com.tenpo.adder.record.service.RecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.tenpo.adder.utils.TestUtils.PAGINATED_RECORDS_PAGE0_SIZE2;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RecordController.class)
class RecordControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private CustomUserDetailService customUserDetailService;

    ObjectMapper objectMapper;

    @MockBean
    private RecordService recordService;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void getRecordsTest() throws Exception {

        final RecordsResponse recordsResponse = RecordsResponse.builder()
                            .pageNumber(0)
                            .pageSize(2)
                            .records(PAGINATED_RECORDS_PAGE0_SIZE2)
                            .build();

        when(recordService.getAllRecords(0,2)).thenReturn(recordsResponse);

        mvc.perform(get("/api/record")
                .param("pageNumber", "0")
                .param("pageSize", "2") )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.records[0].id").value(1L))
                .andExpect(jsonPath("$.records[0].response").value(recordsResponse.getRecords().get(0).getResponse()))
                .andExpect(jsonPath("$.pageNumber").value(recordsResponse.getPageNumber()))
                .andExpect(jsonPath("$.pageSize").value(recordsResponse.getPageSize()))
                .andExpect(jsonPath("$.records[1].id").value(2L))
                .andExpect(jsonPath("$.records[1].response").value(recordsResponse.getRecords().get(1).getResponse()))
                .andExpect(jsonPath("$.pageNumber").value(recordsResponse.getPageNumber()))
                .andExpect(jsonPath("$.pageSize").value(recordsResponse.getPageSize()));

        verify(recordService).getAllRecords(0,2);
    }
}