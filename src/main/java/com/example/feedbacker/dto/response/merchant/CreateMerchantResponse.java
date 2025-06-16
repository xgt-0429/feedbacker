package com.example.feedbacker.dto.response.merchant;

public class CreateMerchantResponse {

    public Long merchantId;
    public boolean isNew;

    public Long getMerchantId() {
        return merchantId;
    }

    public boolean isNew() {
        return isNew;
    }

}
