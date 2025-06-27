package com.example.feedbacker.mapper;

import com.example.feedbacker.dto.MerchantDto;
import com.example.feedbacker.dto.request.merchant.CircleListRequest;
import com.example.feedbacker.dto.response.PagedResult;
import com.example.feedbacker.entity.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MerchantMapper {

    /** 根据外部来源和 ID 查商家 */
    Merchant findBySourceExternal(@Param("externalId") String externalId);

    /** 新增商家，插入后会回填 id */
    int insert(Merchant merchant);

    /** 查询某用户自己创建的商家列表 */
    List<Merchant> findByCreator(@Param("userId") Long userId);

    /** 根据主键查商家 */
    Merchant findById(@Param("id") Long id);

    /** 合并帖子到指定商家 */
    void mergePost(@Param("targetId") Long targetId,
                   @Param("postId") Long postId);

    List<Merchant> selectByIds(@Param("ids") List<Long> ids);

}
