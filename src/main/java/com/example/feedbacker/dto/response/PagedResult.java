package com.example.feedbacker.dto.response;

import java.util.List;

public class PagedResult<T> {

    private final List<T> items;
    private final int page;
    private final int size;
    private final long total;

    public PagedResult(List<T> items, int page, int size, long total) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }
    public int getPage() {
        return page;
    }
    public int getSize() {
        return size;
    }
    public long getTotal() {
        return total;
    }
    
}
