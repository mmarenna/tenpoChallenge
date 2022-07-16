package com.tenpo.adder.record.service;

import com.tenpo.adder.record.model.Record;
import com.tenpo.adder.record.repository.RecordRepository;
import com.tenpo.adder.record.rest.dto.RecordsResponse;
import com.tenpo.adder.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecordServiceImplTest {

    @InjectMocks
    private RecordServiceImpl recordService;
    @Mock
    private RecordRepository recordRepository;

    @Test
    void getAllRecordsTest() {
        final Page<Record> pagedRecords = new PageImpl<>(TestUtils.PAGINATED_RECORDS_PAGE0_SIZE2);
        when(recordRepository.findAll(PageRequest.of(0, 2))).thenReturn(pagedRecords);

        final RecordsResponse recordsResponse= this.recordService.getAllRecords(0, 2);
        assertNotNull(recordsResponse);
        assertEquals(0, recordsResponse.getPageNumber());
        assertEquals(2, recordsResponse.getPageSize());
        assertEquals(2, recordsResponse.getNumberOfRecords());
    }

}