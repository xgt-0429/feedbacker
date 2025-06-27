package com.example.feedbacker.mapper;

import com.example.feedbacker.entity.CircleMerchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CircleMerchantMapper {

    /**
     * 查询指定圈子列表下所有去重商家 ID
     */
    List<Long> selectMerchantIdsByCircles(@Param("circleIds") List<Long> circleIds);

    /**
     * 批量插入圈子-商家关联
     */
    int insert(CircleMerchant circleMerchant);

}
