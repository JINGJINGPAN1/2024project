package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

public interface SysRoleService {

    // 1 角色列表的方法
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);

    // 2 角色添加的方法
    void saveSysRole(SysRole sysRole);

    // 3 角色修改的方法
    void updateSysRole(SysRole sysRole);
}
