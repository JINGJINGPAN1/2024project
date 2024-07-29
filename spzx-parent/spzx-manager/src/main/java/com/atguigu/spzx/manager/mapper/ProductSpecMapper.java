package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSpecMapper {
    //商品特点分页查询
    List<ProductSpec> findByPage();

    //添加保存
    void save(ProductSpec productSpec);

    //修改
    void updateById(ProductSpec productSpec);

    //删除
    void deleteById(Long id);

    // 查询商品所有规格
    List<ProductSpec> findAll();
}
