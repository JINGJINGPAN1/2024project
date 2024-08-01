package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.order.OrderStatisticsDto;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderStatisticsMapper {
    //添加到统计结果表里
    void insert(OrderStatistics orderStatistics);

    // 查询dto统计结果数据返回list
    List<OrderStatistics> selectList(OrderStatisticsDto orderStatisticsDto);
}
