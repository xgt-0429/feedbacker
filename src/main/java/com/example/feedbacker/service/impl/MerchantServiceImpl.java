package com.example.feedbacker.service.impl;

import com.example.feedbacker.dto.MerchantDto;
import com.example.feedbacker.dto.request.merchant.FavoriteMerchantRequest;
import com.example.feedbacker.dto.request.merchant.UnfavoriteMerchantRequest;
import com.example.feedbacker.entity.Merchant;
import com.example.feedbacker.entity.MerchantFavorite;
import com.example.feedbacker.exception.ApiException;
import com.example.feedbacker.mapper.MerchantFavoriteMapper;
import com.example.feedbacker.mapper.MerchantMapper;
import com.example.feedbacker.service.MerchantService;
import com.example.feedbacker.utils.CurrentUserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantMapper merchantMapper;
    private final MerchantFavoriteMapper favMapper;

    public MerchantServiceImpl(MerchantMapper merchantMapper,
                               MerchantFavoriteMapper favMapper) {
        this.merchantMapper = merchantMapper;
        this.favMapper = favMapper;
    }

    @Override
    @Transactional
    public void favorite(FavoriteMerchantRequest req) {
        Long userId = CurrentUserUtil.getUserId();
        if (userId == null) throw new ApiException("请先登录");
        Long mid = req.getMerchantId();
        if (favMapper.exists(userId, mid) > 0) {
            return; // 已收藏，幂等
        }
        MerchantFavorite fav = new MerchantFavorite();
        fav.setMerchantId(req.getMerchantId());
        fav.setUserId(userId);
        favMapper.insert(fav);
    }

    @Override
    @Transactional
    public void unfavorite(UnfavoriteMerchantRequest req) {
        Long userId = CurrentUserUtil.getUserId();
        if (userId == null) throw new ApiException("请先登录");
        favMapper.deleteByUserIdAndMerchantId(userId, req.getMerchantId());
    }

    @Override
    public List<MerchantDto> listFavorites() {
        Long userId = CurrentUserUtil.getUserId();
        if (userId == null) {
            throw new ApiException("请先登录");
        }
        // 1) 查出所有收藏的商家ID
        List<Long> ids = favMapper.selectFavoriteMerchantIds(userId);
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        // 2) 批量查询商家实体
        return merchantMapper.selectByIds(ids).stream()
                .map(m -> new MerchantDto(
                        m.getId(),
                        m.getName(),
                        m.getDescription(),
                        m.getContactInfo(),
                        m.getCreatedAt(),
                        m.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

}
