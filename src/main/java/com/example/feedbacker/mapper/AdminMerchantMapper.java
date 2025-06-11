package com.example.feedbacker.mapper;

import com.example.feedbacker.entity.Merchant;

public interface AdminMerchantMapper {
    void insert(Merchant m);

    void deleteByPrimaryKey(Long merchantId);

    int updateByPrimaryKey(Merchant m);
}
