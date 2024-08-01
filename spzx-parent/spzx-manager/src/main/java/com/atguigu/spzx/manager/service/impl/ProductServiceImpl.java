package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.ProductDetailsMapper;
import com.atguigu.spzx.manager.mapper.ProductMapper;
import com.atguigu.spzx.manager.mapper.ProductSkuMapper;
import com.atguigu.spzx.manager.service.ProductService;
import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductDetails;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    //条件分页查询接口
    @Override
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto) {
        PageHelper.startPage(page, limit);
        List<Product> list = productMapper.findByPage(productDto);
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    //添加
    @Override
    public void save(Product product) {
        // 保存商品基本信息 product表
        product.setStatus(0);
        product.setAuditStatus(0);
        productMapper.save(product);

        // 获取商品sku 列表信息，保存到product_sku表
        List<ProductSku> productSkuList = product.getProductSkuList();
        for(int i = 0; i < productSkuList.size(); i++){
            ProductSku productSku = productSkuList.get(i);
            // 构建编号
            productSku.setSkuCode(product.getId() + "" + i);
            // 构建商品id
            productSku.setProductId(product.getId());
            // 构建商品名字
            productSku.setSkuName(product.getName());
            //构建销量
            productSku.setSaleNum(0);
            // 初始状态值
            productSku.setStatus(0);
            productSkuMapper.save(productSku);
        }

        // 保存商品详情信息， 保存到product_details表
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.save(productDetails);



    }

    //根据商品id查询商品信息
    @Override
    public Product getById(Long id) {
        // 根据id查询商品信息 product表
        Product product = productMapper.selectById(id);

        // 根据商品id查询sku信息 product_sku表
        List<ProductSku> list = productSkuMapper.selectByProductId(id);
        product.setProductSkuList(list);

        // 根据id查询商品details信息 product_details表
        ProductDetails productDetails = productDetailsMapper.selectByProductId(id);
        String imageUrls = productDetails.getImageUrls();
        product.setDetailsImageUrls(imageUrls);

        return product;
    }

    //保存修改数据
    @Override
    public void updateById(Product product) {
        // 商品信息 product表
        productMapper.updateById(product);

        // 修改sku信息 product_sku表
        List<ProductSku> productSkuList = product.getProductSkuList();
        for(ProductSku productSku : productSkuList){
            productSkuMapper.updateById(productSku);
        }


        // 修改商品details信息 product_details表
        String detailsImageUrls = product.getDetailsImageUrls();
        ProductDetails productDetails = productDetailsMapper.selectByProductId(product.getId());
        productDetails.setImageUrls(detailsImageUrls);
        productDetailsMapper.updateById(productDetails);


    }

    @Override
    public void deleteById(Long id) {
        // 删除product表
        productMapper.deleteById(id);
        // 删除product_sku表
        productSkuMapper.deleteById(id);
        // 删除product_details表
        productDetailsMapper.deleteById(id);

    }

    // 审核
    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);
        if(auditStatus == 1){
            product.setAuditStatus(1);
            product.setAuditMessage("审核通过");
        } else{
            product.setAuditStatus(-1);
            product.setAuditMessage("审核不通过");
        }
        productMapper.updateById(product);
    }

    //商品上下架
    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        if(status == 1){
            product.setStatus(1);
        }else{
            product.setStatus(-1);
        }

        productMapper.updateById(product);
    }
}
