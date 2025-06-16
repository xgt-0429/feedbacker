package com.example.feedbacker.service;

import com.example.feedbacker.dto.MerchantDto;
import com.example.feedbacker.dto.request.merchant.*;
import com.example.feedbacker.dto.response.PagedResult;
import com.example.feedbacker.dto.response.merchant.CreateMerchantResponse;
import com.example.feedbacker.dto.response.merchant.MerchantDetailResponse;
import com.example.feedbacker.dto.response.merchant.MerchantSummary;
import com.example.feedbacker.dto.response.merchant.Suggestion;

import java.util.List;

public interface MerchantService {

    void favorite(FavoriteMerchantRequest req);

    void unfavorite(UnFavoriteMerchantRequest req);

    PagedResult<MerchantDto> listFavorites(FavoriteListRequest req);

    List<Suggestion> suggest(SuggestRequest req);

    CreateMerchantResponse create(CreateMerchantRequest req);

    void merge(MergeRequest req);

    List<MerchantSummary> myList(MyListRequest req);

    MerchantDetailResponse detail(Long id);

}

