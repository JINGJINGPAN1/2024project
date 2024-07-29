package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {
    //分页查询
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    //添加
    void save(Brand brand);

    List<Brand> findAll();
}
