package com.tenpo.adder.history.service;

import com.tenpo.adder.history.rest.dto.HistoriesResponse;

public interface HistoryService {

    void createHistory(String uri, String response);

    void createHistory(String uri);

    HistoriesResponse getAllHistories(int pageNumber, int pageSize);

}
