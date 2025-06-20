package com.example.feedbacker.mapper;

import com.example.feedbacker.entity.CircleMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CircleMemberMapper {
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
    int insert(CircleMember record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(CircleMember record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    CircleMember selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(CircleMember record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(CircleMember record);

    /** 本圈成员列表（只返回 userId） */
    List<Long> findUserIdsByCircle(@Param("circleId") Long circleId);

    /** 用户所在圈子列表（只返回 circleId） */
    List<Long> findCircleIdsByUser(@Param("userId") Long userId);

    /** 判断用户是否已在圈内 */
    int exists(@Param("circleId") Long circleId,
               @Param("userId")    Long userId);

}