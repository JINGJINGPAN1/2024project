package com.atguigu.spzx.manager.service;


import com.atguigu.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    List<Category> findCategoryList(Long id);

    //导出
    void exportData(HttpServletResponse httpServletResponse);

    // 导入
    void importData(MultipartFile file);
}
