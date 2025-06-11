package com.example.feedbacker.controller;

import com.example.feedbacker.dto.request.merchant.CreateMerchantRequest;
import com.example.feedbacker.dto.request.merchant.DeleteMerchantRequest;
import com.example.feedbacker.dto.request.merchant.UpdateMerchantRequest;
import com.example.feedbacker.service.AdminMerchantService;
import org.springframework.web.bind.annotation.RestController;
import com.example.feedbacker.dto.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/merchants")
public class AdminMerchantController {

    private final AdminMerchantService svc;
    public AdminMerchantController(AdminMerchantService svc) { this.svc = svc; }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Long>> create(
            @Valid @RequestBody CreateMerchantRequest req) {
        Long id = svc.create(req);
        return ResponseEntity.ok(ApiResponse.success(id));
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponse<Void>> delete(
            @Valid @RequestBody DeleteMerchantRequest req) {
        svc.delete(req);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse<Void>> update(
            @Valid @RequestBody UpdateMerchantRequest req) {
        svc.update(req);
        return ResponseEntity.ok(ApiResponse.ok());
    }
}