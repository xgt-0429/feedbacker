package com.example.feedbacker.service.impl;

import com.example.feedbacker.dto.request.merchant.CreateMerchantRequest;
import com.example.feedbacker.dto.request.merchant.DeleteMerchantRequest;
import com.example.feedbacker.dto.request.merchant.UpdateMerchantRequest;

import com.example.feedbacker.entity.Merchant;
import com.example.feedbacker.entity.MerchantImage;
import com.example.feedbacker.exception.ApiException;
import com.example.feedbacker.mapper.AdminMerchantMapper;
import com.example.feedbacker.mapper.MerchantImageMapper;
import com.example.feedbacker.service.AdminMerchantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminMerchantServiceImpl implements AdminMerchantService {

    private final AdminMerchantMapper adminMerchantMapper;
    private final MerchantImageMapper imageMapper;

    public AdminMerchantServiceImpl(AdminMerchantMapper mm, MerchantImageMapper im) {
        this.adminMerchantMapper = mm;
        this.imageMapper = im;
    }

    @Override
    @Transactional
    public Long create(CreateMerchantRequest req) {
        Merchant m = new Merchant();
        m.setName(req.getName());
        adminMerchantMapper.insert(m);
        return m.getId();
    }

    @Override
    @Transactional
    public void delete(DeleteMerchantRequest req) {
        adminMerchantMapper.deleteByPrimaryKey(req.getMerchantId());
    }

    @Override
    @Transactional
    public void update(UpdateMerchantRequest req) {
        Merchant m = new Merchant();
        m.setId(req.getMerchantId());
        m.setDescription(req.getDescription());
        m.setContactInfo(req.getContactInfo());
        int updated = adminMerchantMapper.updateByPrimaryKey(m);
        if (updated == 0) {
            throw new ApiException("商家不存在或已被删除");
        }
        // 处理图片：先删后增
        imageMapper.deleteByPrimaryKey(req.getMerchantId());
        List<String> urls = req.getImageUrls();
        if (urls != null) {
            for (int i = 0; i < urls.size(); i++) {
                MerchantImage img = new MerchantImage();
                img.setMerchantId(req.getMerchantId());
                img.setUrl(urls.get(i));
                imageMapper.insert(img);
            }
        }
    }

}
