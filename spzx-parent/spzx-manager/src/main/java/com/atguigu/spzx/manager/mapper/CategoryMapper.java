package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    //根据id查询每一层数据，返回list
    List<Category> selectCategoryById(Long id);

    // 遍历返回的list， 是否有下一层数据，如果有就设置hasChildren为true
    int selectCountByParentId(Long id);

    List<Category> selectAll();

    //批量储存
    void batchInsert(List<CategoryExcelVo> categoryList);
}
