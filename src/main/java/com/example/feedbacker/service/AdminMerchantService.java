package com.example.feedbacker.service;

import com.example.feedbacker.dto.request.merchant.CreateMerchantRequest;
import com.example.feedbacker.dto.request.merchant.DeleteMerchantRequest;
import com.example.feedbacker.dto.request.merchant.UpdateMerchantRequest;
import jakarta.validation.Valid;


public interface AdminMerchantService {

    Long create(@Valid CreateMerchantRequest req);

    void delete(@Valid DeleteMerchantRequest req);

    void update(@Valid UpdateMerchantRequest req);

}
