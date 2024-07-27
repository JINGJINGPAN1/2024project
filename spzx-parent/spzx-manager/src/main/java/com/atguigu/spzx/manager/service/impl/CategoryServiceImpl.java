package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.model.entity.product.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> findCategoryList(Long id) {

        //根据id查询每一层数据，返回list
        List<Category> categoryList = categoryMapper.selectCategoryById(id);

        // 遍历返回的list， 是否有下一层数据，如果有就设置hasChildren为true
        if(!CollectionUtils.isEmpty(categoryList)){
            categoryList.forEach(category -> {
                int count = categoryMapper.selectCountByParentId(category.getId());
                if(count > 0){
                    category.setHasChildren(true);
                }else{
                    category.setHasChildren(false);
                }
            });
        }

        return categoryList;
    }
}
