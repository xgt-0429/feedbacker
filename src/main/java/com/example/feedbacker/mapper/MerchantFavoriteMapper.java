package com.example.feedbacker.mapper;

import com.example.feedbacker.entity.MerchantFavorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MerchantFavoriteMapper {
    /**
     * delete by primary key
     * @param userId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(@Param("userId") Long userId);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(MerchantFavorite record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(MerchantFavorite record);

    /**
     * select by primary key
     * @param userId primary key
     * @return object by primary key
     */
    MerchantFavorite selectByPrimaryKey(@Param("userId") Long userId, @Param("merchantId") Long merchantId);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(MerchantFavorite record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(MerchantFavorite record);


    int exists(@Param("userId") Long userId, @Param("merchantId") Long merchantId);

    void deleteByUserIdAndMerchantId(@Param("userId") Long userId, @Param("merchantId") Long merchantId);

    List<Long> selectFavoriteMerchantIds(
            @Param("userId") Long userId,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    /**
     * 统计当前用户收藏的商家总数
     */
    long countFavoritesByUserId(@Param("userId") Long userId);

}