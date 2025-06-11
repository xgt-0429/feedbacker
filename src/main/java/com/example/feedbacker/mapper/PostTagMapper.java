package com.example.feedbacker.mapper;

import com.example.feedbacker.entity.PostTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostTagMapper {
    /**
     * delete by primary key
     * @param postId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(@Param("postId") Long postId, @Param("tagId") Long tagId);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(PostTag record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(PostTag record);
}