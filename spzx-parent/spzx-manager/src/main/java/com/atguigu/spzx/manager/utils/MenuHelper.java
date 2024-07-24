package com.atguigu.spzx.manager.utils;

import com.atguigu.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {

    // 递归
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList){
        List<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList){
            if(sysMenu.getParentId().longValue() == 0){
                trees.add(findChildren(sysMenu, sysMenuList));
            }
        }

        return trees;
    }

    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        sysMenu.setChildren(new ArrayList<>());
        for(SysMenu it : sysMenuList){
            if(it.getParentId().longValue() == sysMenu.getId().longValue()){
                sysMenu.getChildren().add(findChildren(it, sysMenuList));
            }
        }
        return sysMenu;
    }


    // TODO 完成封装过程

//        //递归要有两个关键：1.入口 （找顶层数据 parentId = 0） 2. id和parentId的关系
//        // sysMenuList 所有菜单集合
//        List<SysMenu> trees = new ArrayList<>();
//        for(SysMenu sysMenu : sysMenuList){
//            if(sysMenu.getParentId().longValue() == 0){
//                trees.add(findChild(sysMenu, sysMenuList));
//            }
//        }
//        return trees;
//    }
//
//    public static SysMenu findChild(SysMenu sysMenu, List<SysMenu> sysMenuList){
//        // 在sysMenu 有属性 private List<SysMenu> children; 封装下一层数据
//        sysMenu.setChildren(new ArrayList<>());
//        // 递归
//        // sysMenu 的id值 sysMenuList中parentId相同
//        for(SysMenu it : sysMenuList){
//            if(sysMenu.getId().longValue() == it.getParentId().longValue()){
//                //it 就是下层数据，进行封装
//
//                sysMenu.getChildren().add(findChild(it, sysMenuList));
//            }
//        }
//        return sysMenu;
//    }
}
