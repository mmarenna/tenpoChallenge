package com.tenpo.adder.record.service;

import com.tenpo.adder.record.rest.dto.RecordsResponse;

public interface RecordService {

    void createRecord(String uri, String response);

    void createRecord(String uri);

    RecordsResponse getAllRecords(int pageNumber, int pageSize);

}
