package com.esnt.ferreconst.dto.response.company;

import java.util.ArrayList;
import java.util.List;

public class CompanyPageResponseDto {

    private List<CompanyResponseDto> items = new ArrayList<>();
    private int page;
    private int pageSize;
    private int totalItems;
    private int totalPages;

    public CompanyPageResponseDto() {
    }

    public List<CompanyResponseDto> getItems() {
        return items;
    }

    public void setItems(List<CompanyResponseDto> items) {
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
