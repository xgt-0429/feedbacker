package com.example.feedbacker.dto.request.merchant;

import jakarta.validation.constraints.Min;

public class FavoriteListRequest {

    @Min(value = 1, message = "page 必须 >= 1")
    private int page;
    @Min(value = 1, message = "size 必须 >= 1")
    private int size;

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

}
