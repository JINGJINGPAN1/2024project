package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.github.pagehelper.PageInfo;

public interface ProductService {
    //条件分页查询接口
    PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);
}
