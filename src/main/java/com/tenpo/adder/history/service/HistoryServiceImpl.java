package com.tenpo.adder.history.service;

import com.tenpo.adder.history.model.History;
import com.tenpo.adder.history.repository.HistoryRepository;
import com.tenpo.adder.history.rest.dto.HistoriesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class HistoryServiceImpl implements HistoryService{

    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public void createHistory(String uri, String response) {
        History history = new History(uri);
        history.setResponse(Objects.nonNull(response) ? response : null);
        this.historyRepository.save(history);
    }

    @Override
    public void createHistory(String uri) {
        this.historyRepository.save(new History(uri));
    }

    @Override
    public HistoriesResponse getAllHistories(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<History> historiesFromPage = historyRepository.findAll(pageable);
        List<History> histories = historiesFromPage.getContent();
        HistoriesResponse historiesResponse = new HistoriesResponse(histories,
                historiesFromPage.getNumber(),
                historiesFromPage.getSize(),
                historiesFromPage.getTotalElements(),
                historiesFromPage.getTotalPages());
        return historiesResponse;
    }

}
