package com.example.feedbacker.service.impl;

import com.example.feedbacker.dto.MerchantDto;
import com.example.feedbacker.dto.request.merchant.*;
import com.example.feedbacker.dto.response.PagedResult;
import com.example.feedbacker.dto.response.merchant.CreateMerchantResponse;
import com.example.feedbacker.dto.response.merchant.MerchantDetailResponse;
import com.example.feedbacker.dto.response.merchant.MerchantSummary;
import com.example.feedbacker.dto.response.merchant.Suggestion;
import com.example.feedbacker.entity.Merchant;
import com.example.feedbacker.entity.MerchantFavorite;
import com.example.feedbacker.exception.ApiException;
import com.example.feedbacker.mapper.MerchantFavoriteMapper;
import com.example.feedbacker.mapper.MerchantMapper;
import com.example.feedbacker.service.MerchantService;
import com.example.feedbacker.utils.CurrentUserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final RestTemplate rest = new RestTemplate();
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
    public void unfavorite(UnFavoriteMerchantRequest req) {
        Long userId = CurrentUserUtil.getUserId();
        if (userId == null) throw new ApiException("请先登录");
        favMapper.deleteByUserIdAndMerchantId(userId, req.getMerchantId());
    }

    @Override
    public PagedResult<MerchantDto> listFavorites(FavoriteListRequest req) {
        Long userId = CurrentUserUtil.getUserId();
        if (userId == null) {
            throw new ApiException("请先登录");
        }
        int page = req.getPage();
        int size = req.getSize();
        int offset = (page - 1) * size;

        // 1) 查 ID 列表
        List<Long> ids = favMapper.selectFavoriteMerchantIds(userId, offset, size);
        if (ids.isEmpty()) {
            return new PagedResult<>(Collections.emptyList(), page, size, 0L);
        }

        // 2) 批量查商家
        List<Merchant> merchants = merchantMapper.selectByIds(ids);

        // 3) 转 DTO
        List<MerchantDto> dtos = merchants.stream()
                .map(m -> new MerchantDto(
                        m.getId(),
                        m.getName(),
                        m.getDescription(),
                        m.getContactInfo(),
                        m.getCreatedAt(),
                        m.getUpdatedAt()
                ))
                .collect(Collectors.toList());

        // 4) 总数
        long total = favMapper.countFavoritesByUserId(userId);

        return new PagedResult<>(dtos, page, size, total);
    }

    @Override
    public List<Suggestion> suggest(SuggestRequest req){
        // TODO: 调用 Google Places Autocomplete
        return List.of();
    }

    @Override
    @Transactional
    public CreateMerchantResponse create(CreateMerchantRequest req){
        Merchant m = merchantMapper.findBySourceExternal(req.source, req.externalId);
        boolean isNew = (m==null);
        if(isNew){
            m = new Merchant();
            m.setName(req.name);
            m.setAddress(req.address);
            m.setLatitude(req.latitude);
            m.setLongitude(req.longitude);
            m.setExternalSource(req.source);
            m.setExternalId(req.externalId);
            m.setCreatedBy(CurrentUserUtil.getUserId());
            merchantMapper.insert(m);
        }
        CreateMerchantResponse r = new CreateMerchantResponse();
        r.merchantId = m.getId();
        r.isNew      = isNew;
        return r;
    }

    @Override
    @Transactional
    public void merge(MergeRequest req){
        merchantMapper.mergePost(req.targetId, req.postId);
    }

    @Override
    public List<MerchantSummary> myList(MyListRequest req){
        Long userId = CurrentUserUtil.getUserId();
        if(userId==null) throw new ApiException("请先登录");
        return merchantMapper.findByCreator(userId).stream()
                .map(m -> {
                    MerchantSummary s = new MerchantSummary();
                    s.id        = m.getId();
                    s.name      = m.getName();
                    s.address   = m.getAddress();
                    s.latitude  = m.getLatitude();
                    s.longitude = m.getLongitude();
                    return s;
                })
                .collect(Collectors.toList());
    }

    @Override
    public MerchantDetailResponse detail(Long id){
        Merchant m = merchantMapper.findById(id);
        if(m==null) throw new ApiException("商家不存在");
        MerchantDetailResponse d = new MerchantDetailResponse();
        d.id         = m.getId();
        d.name       = m.getName();
        d.address    = m.getAddress();
        d.latitude   = m.getLatitude();
        d.longitude  = m.getLongitude();
        d.externalId = m.getExternalId();
        d.source     = m.getExternalSource();
        d.createdBy  = m.getCreatedBy();
        return d;
    }

}
