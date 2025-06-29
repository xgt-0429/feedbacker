package com.example.feedbacker.mapper;

import com.example.feedbacker.entity.MerchantImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MerchantImageMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(MerchantImage record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(MerchantImage record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    MerchantImage selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(MerchantImage record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(MerchantImage record);
}