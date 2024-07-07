package com.atguigu.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleUserMapper {
    void deleteByUserId(Long userId);

    void doAssign(Long userId, Long roleId);

    //根据userId 查询用户分配过的角色id
    List<Long> selectRoleIdsByUserId(Long userId);
}
