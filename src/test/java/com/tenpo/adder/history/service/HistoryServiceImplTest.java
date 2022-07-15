package com.tenpo.adder.history.service;

import com.tenpo.adder.history.model.History;
import com.tenpo.adder.history.repository.HistoryRepository;
import com.tenpo.adder.history.rest.dto.HistoriesResponse;
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
class HistoryServiceImplTest {

    @InjectMocks
    private HistoryServiceImpl historyService;
    @Mock
    private HistoryRepository historyRepository;

    @Test
    void getAllHistoriesTest() {
        final Page<History> pagedHistories = new PageImpl<>(TestUtils.PAGINATED_HISTORIES_PAGE0_SIZE2);
        when(historyRepository.findAll(PageRequest.of(0, 2))).thenReturn(pagedHistories);

        final HistoriesResponse histories= this.historyService.getAllHistories(0, 2);
        assertNotNull(histories);
        assertEquals(0, histories.getPageNumber());
        assertEquals(2, histories.getPageSize());
        assertEquals(2, histories.getNumberOfHistories());
    }

}