package com.example.feedbacker.controller;

import com.example.feedbacker.dto.MerchantDto;
import com.example.feedbacker.dto.request.merchant.*;
import com.example.feedbacker.dto.response.ApiResponse;
import com.example.feedbacker.dto.response.PagedResult;
import com.example.feedbacker.dto.response.merchant.CreateMerchantResponse;
import com.example.feedbacker.dto.response.merchant.MerchantDetailResponse;
import com.example.feedbacker.dto.response.merchant.MerchantSummary;
import com.example.feedbacker.dto.response.merchant.Suggestion;
import com.example.feedbacker.service.MerchantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    private final MerchantService svc;
    public MerchantController(MerchantService svc){
        this.svc = svc;
    }

    @PostMapping("/favorite")
    public ResponseEntity<ApiResponse<Void>> favorite(
            @Valid @RequestBody FavoriteMerchantRequest req) {
        svc.favorite(req);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PostMapping("/unfavorite")
    public ResponseEntity<ApiResponse<Void>> unfavorite(
            @Valid @RequestBody UnFavoriteMerchantRequest req) {
        svc.unfavorite(req);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    /**
     * 查看当前用户收藏的商家列表
     */
    @PostMapping("/favorites/list")
    public ResponseEntity<ApiResponse<PagedResult<MerchantDto>>> listFavorites(
            @Valid @RequestBody FavoriteListRequest req) {
        PagedResult<MerchantDto> page = svc.listFavorites(req);
        return ResponseEntity.ok(ApiResponse.success(page));
    }

    @PostMapping("/suggest")
    public ResponseEntity<ApiResponse<List<Suggestion>>> suggest(
            @Valid @RequestBody SuggestRequest req) {
        return ResponseEntity.ok(ApiResponse.success(svc.suggest(req)));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateMerchantResponse>> create(
            @Valid @RequestBody CreateMerchantRequest req) {
        return ResponseEntity.ok(ApiResponse.success(svc.create(req)));
    }

    @PostMapping("/merge")
    public ResponseEntity<ApiResponse<Void>> merge(
            @Valid @RequestBody MergeRequest req) {
        svc.merge(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PostMapping("/mylist")
    public ResponseEntity<ApiResponse<List<MerchantSummary>>> myList(
            @RequestBody MyListRequest req) {
        return ResponseEntity.ok(ApiResponse.success(svc.myList(req)));
    }

    @PostMapping("/detail")
    public ResponseEntity<ApiResponse<MerchantDetailResponse>> detail(
            @Valid @RequestBody Long req) {
        return ResponseEntity.ok(ApiResponse.success(svc.detail(req)));
    }
}
