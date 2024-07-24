package com.btgpactual.desafio.controller.dto;

import java.util.List;
import java.util.Map;

public class ApiResponse<T> {

    private Map<String, Object> summary;
    private List<T> data;
    private PaginationResponse pagination;

    public ApiResponse(Map<String,Object> summary, List<T> data, PaginationResponse pagination) {
        this.data = data;
        this.pagination = pagination;
        this.summary = summary;
    }

    public List<T> getData() {
        return data;
    }

    public Map<String,Object> getSummary(){
        return summary;
    }

    public PaginationResponse getPagination() {
        return pagination;
    }

}
