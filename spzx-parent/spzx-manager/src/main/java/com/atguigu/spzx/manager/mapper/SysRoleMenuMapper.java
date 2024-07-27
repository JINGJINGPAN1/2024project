package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.AssignMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper {
    List<Long> findSysRoleMenuByRoleId(Long roleId);

    // 根据角色id删除角色分配过的菜单id
    void deleteByRoleId(Long roleId);

    //保存分配数据
    void doAssign(AssignMenuDto assignMenuDto);

    // 把父菜单的状态改为1
    void updateSysRoleMenuIsHalf(Long id);
}
