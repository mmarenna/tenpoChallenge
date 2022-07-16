package com.tenpo.adder.record.service;

import com.tenpo.adder.record.model.Record;
import com.tenpo.adder.record.repository.RecordRepository;
import com.tenpo.adder.record.rest.dto.RecordsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;

    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public void createRecord(String uri, String response) {
        Record newRecord = Record.builder()
                .uri(uri)
                .response(Objects.nonNull(response) ? response : null)
                .build();
        this.recordRepository.save(newRecord);
    }

    @Override
    public void createRecord(String uri) {
        this.recordRepository.save(Record.builder().uri(uri).build());
    }

    @Override
    public RecordsResponse getAllRecords(int pageNumber, int pageSize) {

        Page<Record> recordsFromPage = recordRepository.findAll(PageRequest.of(pageNumber, pageSize));
        final List<Record> records = recordsFromPage.getContent();

        return RecordsResponse.builder()
                .records(records).pageNumber(recordsFromPage.getNumber())
                .pageSize(recordsFromPage.getSize()).numberOfRecords(recordsFromPage.getTotalElements())
                .totalPages(recordsFromPage.getTotalPages())
                .build();
    }

}
