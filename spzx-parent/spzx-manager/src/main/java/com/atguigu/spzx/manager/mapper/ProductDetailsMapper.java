package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDetailsMapper {

    // 保存商品详情信息， 保存到product_details表
    void save(ProductDetails productDetails);

    // 根据id查询商品details信息 product_details表
    ProductDetails selectByProductId(Long id);

    // 修改商品details信息 product_details表
    void updateById(ProductDetails productDetails);

    // 删除product_details表
    void deleteById(Long id);
}
