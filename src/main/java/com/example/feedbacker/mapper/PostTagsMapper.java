package com.example.feedbacker.mapper;

import com.example.feedbacker.entity.PostTags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostTagsMapper {
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
    int insert(PostTags record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(PostTags record);
}