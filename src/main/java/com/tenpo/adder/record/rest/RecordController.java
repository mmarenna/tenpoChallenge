package com.tenpo.adder.record.rest;


import com.tenpo.adder.record.rest.dto.RecordsResponse;
import com.tenpo.adder.record.service.RecordService;
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
@RequestMapping("/api/record")
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping()
    public ResponseEntity<RecordsResponse> getRecords(@RequestParam(value = "pageNumber", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
                                                      @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.recordService.getAllRecords(pageNumber, pageSize));
    }
}
