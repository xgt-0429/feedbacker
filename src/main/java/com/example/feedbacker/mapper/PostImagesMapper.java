package com.example.feedbacker.mapper;

import com.example.feedbacker.entity.PostImages;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostImagesMapper {
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
    int insert(PostImages record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(PostImages record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    PostImages selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(PostImages record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(PostImages record);
}