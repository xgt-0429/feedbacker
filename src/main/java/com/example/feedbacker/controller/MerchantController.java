package com.example.feedbacker.controller;

import com.example.feedbacker.dto.MerchantDto;
import com.example.feedbacker.dto.response.ApiResponse;
import com.example.feedbacker.service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

    // … favorite/unfavorite/list …

    /**
     * 查看当前用户收藏的商家列表（两步查询版）
     */
    @PostMapping("/favorites/list")
    public ResponseEntity<ApiResponse<List<MerchantDto>>> listFavorites() {
        List<MerchantDto> favorites = svc.listFavorites();
        return ResponseEntity.ok(ApiResponse.success(favorites));
    }

}
