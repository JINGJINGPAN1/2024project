package com.atguigu.spzx.manager.task;

import cn.hutool.core.date.DateUtil;
import com.atguigu.spzx.manager.mapper.OrderInfoMapper;
import com.atguigu.spzx.manager.mapper.OrderStatisticsMapper;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class OrderStatisticsTask {

    // 定义定时任务，每5秒使用@Scheduled注解指定调度时间表达式
//    @Scheduled(cron = "0/5 * * * * ? ")
//    public void helloWorldTask(){
//        System.out.println(new Date().toInstant() + "Hello World");
//    }
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    //每天凌晨两天得到前一天日期统计数据，把统计之后的数据添加到统计结果表里
    @Scheduled(cron = "0 0 2 * * ? ")
    //@Scheduled(cron = "0/10 * * * * ? ") // 测试
    public void orderTotalAmountStatistics(){
        // 得到前一天日期
        String createDate = DateUtil.offsetDay(new Date(), -1)
                .toString(new SimpleDateFormat("yyyy-MM-dd"));
        // 得到统计数据
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(createDate);

        //添加到统计结果表里
        if(orderStatistics != null){
            orderStatisticsMapper.insert(orderStatistics);
        }
    }
}
