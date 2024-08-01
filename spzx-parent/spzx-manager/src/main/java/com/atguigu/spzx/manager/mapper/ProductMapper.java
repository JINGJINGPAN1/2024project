package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    //条件分页查询接口
    List<Product> findByPage(ProductDto productDto);

    // 保存商品基本信息 product表
    void save(Product product);

    // 根据id查询商品信息 product表
    Product selectById(Long id);

    // 根据id修改商品信息 product表
    void updateById(Product product);

    // 删除信息
    void deleteById(Long id);
}
