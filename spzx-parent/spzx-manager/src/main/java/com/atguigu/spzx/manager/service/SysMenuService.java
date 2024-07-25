package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.system.SysMenuVo;

import java.util.List;

public interface SysMenuService{
    void save(SysMenu sysMenu);

    List<SysMenu> findNodes();

    void update(SysMenu sysMenu);

    void removeById(Long id);

    //查询用户可以操作的菜单
    List<SysMenuVo> findMenusByUserId();
}
