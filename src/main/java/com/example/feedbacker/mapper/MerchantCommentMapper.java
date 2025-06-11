package com.example.feedbacker.mapper;

import com.example.feedbacker.entity.MerchantComment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MerchantCommentMapper {
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
    int insert(MerchantComment record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(MerchantComment record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    MerchantComment selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(MerchantComment record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(MerchantComment record);
}