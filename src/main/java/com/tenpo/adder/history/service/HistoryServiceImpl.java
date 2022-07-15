package com.tenpo.adder.history.service;

import com.tenpo.adder.history.model.History;
import com.tenpo.adder.history.repository.HistoryRepository;
import com.tenpo.adder.history.rest.dto.HistoriesResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HistoryServiceImpl implements HistoryService{

    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public void createHistory(String uri, String response) {
        History history = History.builder()
                .uri(uri)
                .response(Objects.nonNull(response) ? response : null)
                .build();
        this.historyRepository.save(history);
    }

    @Override
    public void createHistory(String uri) {
        this.historyRepository.save(History.builder().uri(uri).build());
    }

    @Override
    public HistoriesResponse getAllHistories(int pageNumber, int pageSize) {

        Page<History> historiesFromPage = historyRepository.findAll(PageRequest.of(pageNumber, pageSize));
        final List<History> histories = historiesFromPage.getContent();

        return HistoriesResponse.builder()
                .histories(histories).pageNumber(historiesFromPage.getNumber())
                .pageSize(historiesFromPage.getSize()).numberOfHistories(historiesFromPage.getTotalElements())
                .totalPages(historiesFromPage.getTotalPages())
                .build();
    }

}
