package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.mapper.SysMenuMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.utils.MenuHelper;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.SysMenuVo;
import com.atguigu.spzx.utils.AuthContextUtil;
import org.ehcache.core.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
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

    //查询用户可以操作的菜单
    @Override
    public List<SysMenuVo> findMenusByUserId() {
        // TODO: 获取userId
        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();

        // TODO: 根据userId查询可以操作的订单
        List<SysMenu> sysMenuList = sysMenuMapper.findMenusByUserId(userId);

        // TODO: 封装要求数据格式 返回
        List<SysMenu> sysMenuList1 = MenuHelper.buildTree(sysMenuList);

        List<SysMenuVo> sysMenuVos = this.buildMenus(sysMenuList1);
        return sysMenuVos;
    }

    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {
        List<SysMenuVo> sysMenuVoList = new LinkedList<>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
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
