package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.product.ProductSpec;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductSpecService {
    //商品特点分页查询
    PageInfo<ProductSpec> findByPage(Integer page, Integer limit);

    //添加保存
    void save(ProductSpec productSpec);

    // 修改
    void updateById(ProductSpec productSpec);

    //删除
    void deleteById(Long id);

    // 查询商品所有规格
    List<ProductSpec> findAll();
}
