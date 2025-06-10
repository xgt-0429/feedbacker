package com.example.feedbacker.mapper;

import com.example.feedbacker.entity.CircleApplications;


public interface CircleApplicationsMapper {
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
    int insert(CircleApplications record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(CircleApplications record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    CircleApplications selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(CircleApplications record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(CircleApplications record);
}