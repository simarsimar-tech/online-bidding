package com.demo.onlinebidding.model;

import lombok.Builder;

@Builder
public class SearchCriteria {

    private String status;

    private Integer start;

    private Integer count;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
