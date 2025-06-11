package com.example.feedbacker.dto.request.merchant;

import jakarta.validation.constraints.NotNull;

public class DeleteMerchantRequest {

    @NotNull
    private Long merchantId;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
