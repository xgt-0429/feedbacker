package com.example.feedbacker.service;

import com.example.feedbacker.dto.MerchantDto;
import com.example.feedbacker.dto.request.merchant.FavoriteMerchantRequest;
import com.example.feedbacker.dto.request.merchant.UnfavoriteMerchantRequest;

import java.util.List;

public interface MerchantService {

    void favorite(FavoriteMerchantRequest req);

    void unfavorite(UnfavoriteMerchantRequest req);

    List<MerchantDto> listFavorites();

}
