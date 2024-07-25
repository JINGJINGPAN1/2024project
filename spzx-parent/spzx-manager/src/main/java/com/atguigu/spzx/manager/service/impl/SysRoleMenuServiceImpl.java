package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysRoleMenuMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.service.SysRoleMenuService;
import com.atguigu.spzx.model.dto.system.AssignMenuDto;
import com.atguigu.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysMenuService sysMenuService;
    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {
        // 查询所有菜单
        List<SysMenu> sysMenuList = sysMenuService.findNodes();
        // 查询当前角色的菜单数据
        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);
        Map<String, Object> map = new HashMap<>();
        map.put("sysMenuList", sysMenuList);
        map.put("roleMenuIds", roleMenuIds);

        return map;
    }

    // 分配菜单
    @Override
    public void doAssign(AssignMenuDto assignMenuDto) {
        // 根据角色id删除角色分配过的菜单id
        sysRoleMenuMapper.deleteByRoleId(assignMenuDto.getRoleId());

        //保存分配数据
        List<Map<String, Number>> menuInfo = assignMenuDto.getMenuIdList();
        if(menuInfo != null && menuInfo.size() > 0){
            sysRoleMenuMapper.doAssign(assignMenuDto);
        }

    }
}
