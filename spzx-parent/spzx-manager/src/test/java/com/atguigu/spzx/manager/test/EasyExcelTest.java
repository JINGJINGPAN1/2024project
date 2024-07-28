package com.atguigu.spzx.manager.test;

import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelTest {
    public static void main(String[] args) {
        //read();
        write();

    }

    private static void write() {
        String fileName =  "/Users/jingjingpan/Downloads/10895651.xlsx";
        List<CategoryExcelVo> list = new ArrayList<>();
        list.add(new CategoryExcelVo(1L , "数码办公" , "",0L, 1, 1)) ;
        list.add(new CategoryExcelVo(11L , "华为手机" , "",1L, 1, 2)) ;
        EasyExcel.write(fileName, CategoryExcelVo.class).sheet().doWrite(list);
    }

    private static void read() {
        String fileName = "/Users/jingjingpan/Downloads/10895659.xlsx";
        // 创建一个监听器对象,类型是categoryVo
        ExcelListener<CategoryExcelVo> categoryExcelVoExcelListener = new ExcelListener<>();
        EasyExcel.read(fileName, CategoryExcelVo.class, categoryExcelVoExcelListener).sheet().doRead();
        List<CategoryExcelVo> categoryExcelVoList = categoryExcelVoExcelListener.getData();
        for(CategoryExcelVo categoryExcelVo: categoryExcelVoList){
            System.out.println(categoryExcelVo);
        }

    }
}
