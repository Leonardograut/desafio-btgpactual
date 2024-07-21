package com.btgpactual.desafio.controller.dto;

import java.util.List;

public class ApiResponse<T> {

    private List<T> data;

    private PaginationResponse pagination;

    public ApiResponse(List<T> data, PaginationResponse pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<T> getData() {
        return data;
    }

    public PaginationResponse getPagination() {
        return pagination;
    }

}
