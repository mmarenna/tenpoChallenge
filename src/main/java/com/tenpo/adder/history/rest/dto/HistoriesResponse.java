package com.tenpo.adder.history.rest.dto;

import com.tenpo.adder.history.model.History;

import java.util.List;

public class HistoriesResponse {

    private List<History> histories;

    private int pageNumber;
    private int pageSize;
    private long numberOfHistories;
    private int totalPages;

    public HistoriesResponse(List<History> histories, int pageNumber, int pageSize, long numberOfHistories, int totalPages) {
        this.histories = histories;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.numberOfHistories = numberOfHistories;
        this.totalPages = totalPages;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getNumberOfHistories() {
        return numberOfHistories;
    }

    public void setNumberOfHistories(long numberOfHistories) {
        this.numberOfHistories = numberOfHistories;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
