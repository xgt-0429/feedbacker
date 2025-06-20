package com.example.feedbacker.mapper;

import com.example.feedbacker.entity.CircleApplication;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CircleApplicationMapper {
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
    int insert(CircleApplication record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(CircleApplication record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    CircleApplication selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(CircleApplication record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(CircleApplication record);

    /** 圈主查看待处理申请 */
    List<CircleApplication> findPendingByCircle(@Param("circleId") Long circleId);

    /** 同意或拒绝申请 */
    int updateStatus(@Param("id")     Long id,
                     @Param("status") String status);

}