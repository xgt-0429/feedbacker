package com.example.feedbacker.dto.request.merchant;

import jakarta.validation.constraints.NotNull;

public class UnfavoriteMerchantRequest {

    @NotNull(message = "merchantId 不能为空")
    private Long merchantId;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
