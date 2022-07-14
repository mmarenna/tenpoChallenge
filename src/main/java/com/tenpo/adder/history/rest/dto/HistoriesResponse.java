package com.tenpo.adder.history.rest.dto;

import com.tenpo.adder.history.model.History;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class HistoriesResponse {

    private List<History> histories;
    private int pageNumber;
    private int pageSize;
    private long numberOfHistories;
    private int totalPages;
}
