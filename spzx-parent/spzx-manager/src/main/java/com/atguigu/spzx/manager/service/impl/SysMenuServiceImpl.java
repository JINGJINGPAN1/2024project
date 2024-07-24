package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.mapper.SysMenuMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.utils.MenuHelper;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.ehcache.core.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    //修改菜单
    @Override
    public void update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }

    //删除菜单
    @Override
    public void removeById(Long id) {
        //根据菜单id，判断是否有子菜单
        int count = sysMenuMapper.selectCount(id);
        if(count > 0){
            throw new GuiguException(ResultCodeEnum.SUCCESS.NODE_ERROR);
        }

        if(count == 0){
            sysMenuMapper.delete(id);
        }
    }

    // 添加菜单
    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.save(sysMenu);

    }

    @Override
    public List<SysMenu> findNodes() {
        // 1 查询所有菜单，返回list集合
        List<SysMenu> sysMenuList = sysMenuMapper.findAll();
        if(CollectionUtils.isEmpty(sysMenuList)){
            return null;
        }

        // 2 调用工具类的方法，把返回list集合封装要求数据格式
        List<SysMenu> treeList = MenuHelper.buildTree(sysMenuList);
        return treeList;
    }

}
