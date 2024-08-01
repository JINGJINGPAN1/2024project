package com.atguigu.spzx.common.log.aspect;

import com.atguigu.spzx.common.log.annotation.Log;
import com.atguigu.spzx.common.log.service.AsyncOperLogService;
import com.atguigu.spzx.common.log.utils.LogUtil;
import com.atguigu.spzx.model.entity.system.SysOperLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private AsyncOperLogService operLogService;


    //环绕通知
    @Around(value = "@annotation(sysLog)")
    public Object doAroundAdvice(ProceedingJoinPoint  joinPoint, Log sysLog){
//        int businessType = sysLog.businessType();
//        String title = sysLog.title();
//        System.out.println("title: " + title + " :: businessType " + businessType);

        //在调用业务方法之前，封装数据
        SysOperLog sysOperLog = new SysOperLog();
        LogUtil.beforeHandleLog(sysLog, joinPoint, sysOperLog);


        //业务方法调用
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();

            //在调用业务方法之后，封装数据
            LogUtil.afterHandlLog(sysLog, proceed, sysOperLog, 0, null);
//            System.out.println("在业务方法之后执行....");
        } catch (Throwable e) {
            e.printStackTrace();
            LogUtil.afterHandlLog(sysLog, proceed, sysOperLog, 1, e.getMessage());
            throw new RuntimeException();
        }

        //保存日志数据
        operLogService.saveSysOperLog(sysOperLog);

        return proceed;
    }


}
