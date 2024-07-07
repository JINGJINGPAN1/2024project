package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.github.pagehelper.PageInfo;

public interface SysUserService {


    // 用户登录
    LoginVo login(LoginDto loginDto);

    // 获取当前用户信息
    SysUser getUserInfo(String token);
    // 用户退出方法
    void logout(String token);

    // 1 用户条件分页查询接口
    PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto);

    //添加用户
    void saveSysUser(SysUser sysUser);

    // 修改用户
    void updateSysUser(SysUser sysUser);

    // 4 用户删除
    void deleteById(Long userId);

    void doAssign(AssginRoleDto assginRoleDto);
}
