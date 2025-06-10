package com.example.feedbacker.mapper;

import com.example.feedbacker.entity.MerchantFavorites;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MerchantFavoritesMapper {
    /**
     * delete by primary key
     * @param userId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("merchantId") Long merchantId);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(MerchantFavorites record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(MerchantFavorites record);

    /**
     * select by primary key
     * @param userId primary key
     * @return object by primary key
     */
    MerchantFavorites selectByPrimaryKey(@Param("userId") Long userId, @Param("merchantId") Long merchantId);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(MerchantFavorites record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(MerchantFavorites record);
}