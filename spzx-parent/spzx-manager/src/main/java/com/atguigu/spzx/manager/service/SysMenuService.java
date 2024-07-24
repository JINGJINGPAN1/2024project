package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.system.SysMenu;

import java.util.List;

public interface SysMenuService{
    void save(SysMenu sysMenu);

    List<SysMenu> findNodes();

    void update(SysMenu sysMenu);

    void removeById(Long id);
}
