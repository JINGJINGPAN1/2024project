package com.atguigu.spzx.manager.test;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener<T> extends AnalysisEventListener<T> {
    //可以通过实例获取该值
    private List<T> data = new ArrayList<>();
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {// 每解析一行数据就会调用一次该方法
        data.add(t);
    }

    public List<T> getData(){
        return data;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
