package com.tenpo.adder.history.rest;


import com.tenpo.adder.history.service.HistoryService;
import com.tenpo.adder.history.rest.dto.HistoriesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.tenpo.adder.utils.ApiTenpoConstants.DEFAULT_PAGE_NUMBER;
import static com.tenpo.adder.utils.ApiTenpoConstants.DEFAULT_PAGE_SIZE;

@Slf4j
@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping()
    public ResponseEntity<HistoriesResponse> getHistories(@RequestParam(value = "pageNumber", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
                                                          @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.historyService.getAllHistories(pageNumber, pageSize));
    }

}
