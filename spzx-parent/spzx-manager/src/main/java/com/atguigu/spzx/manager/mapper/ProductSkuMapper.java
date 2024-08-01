package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSkuMapper {

    // 获取商品sku 列表信息，保存到product_sku表
    void save(ProductSku productSku);

    // 根据商品id查询sku信息 product_sku表
    List<ProductSku> selectByProductId(Long id);

    // 修改sku信息 product_sku表
    void updateById(ProductSku productSku);

    // 删除product_sku表
    void deleteById(Long id);
}
