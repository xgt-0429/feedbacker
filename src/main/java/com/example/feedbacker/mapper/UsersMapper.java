package com.example.feedbacker.mapper;

import com.example.feedbacker.entity.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
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
    int insert(Users record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Users record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Users selectByPrimaryKey(Long id);

    /**
     * select by userName
     * @param userName the userName
     * @return object by userName
     */
    Users selectByUserName(String userName);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Users record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Users record);
}