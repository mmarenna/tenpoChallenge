package com.tenpo.adder.record.rest.dto;

import com.tenpo.adder.record.model.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class RecordsResponse {

    private List<Record> records;
    private int pageNumber;
    private int pageSize;
    private long numberOfRecords;
    private int totalPages;
}
