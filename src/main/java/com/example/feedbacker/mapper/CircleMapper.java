package com.example.feedbacker.mapper;

import com.example.feedbacker.entity.Circle;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CircleMapper {
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
    int insert(Circle record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Circle record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Circle selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Circle record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Circle record);
}