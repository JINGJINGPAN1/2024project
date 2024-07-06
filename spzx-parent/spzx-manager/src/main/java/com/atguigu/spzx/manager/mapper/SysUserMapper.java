package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {

    // 1 根据用户名查询数据库表 sys_user表
    SysUser selectUserInfoByUserName(String userName);

    // 1 用户条件分页查询接口
    List<SysUser> findByPage(SysUserDto sysUserDto);

    // 2 用户添加
    void save(SysUser sysUser);

    void update(SysUser sysUser);

    void delete(Long userId);
}
