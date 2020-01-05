package com.wang.sys.controller;

import com.wang.sys.constast.SysConstast;
import com.wang.sys.domain.SysMenu;
import com.wang.sys.domain.SysUser;
import com.wang.sys.service.SysMenuService;
import com.wang.sys.utils.*;
import com.wang.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shanpeng
 * @ClassName SysMenuController
 * @description 菜单管理控制器
 * @date 2020/1/5 15:21
 * @Version 1.0
 */
@RestController
@RequestMapping("menu")
public class SysMenuController {

    @Resource
    private SysMenuService menuService;

    /**
     * @Description 将左侧菜单加载成json格式
     * @Author shanpeng
     * @Date  18:33
     * @Param [menuVo]
     * @return java.util.List<com.wang.sys.utils.TreeNode>
     **/
    @RequestMapping("loadIndexleftMenuJson")
    public List<TreeNode> loadIndexLeftMenuJson(MenuVo menuVo){
        //得到当前登陆的用户对象
        SysUser user =(SysUser) WebUtils.getHttpSession().getAttribute("user");

        List<SysMenu> list = null;
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
        if(user.getType()==SysConstast.USER_TYPE_SUPER){
            list = this.menuService.queryAllMenuForList(menuVo);
        }else {
            list = this.menuService.queryMenuByUserIdForList(menuVo,user.getUserid());
        }
        List<TreeNode> nodes = new ArrayList<>();
        //把list里面的数据放到nodes中
        for (SysMenu menu: list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = menu.getSpread()==SysConstast.SPREAD_TRUE?true:false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id,pid,title,icon,href,spread,target));
        }
        return TreeNodeBuilder.builder(nodes,1);
    }


    /**
     * 加载菜单左边的菜单树
     * @param menuVo
     * @return
     */
    @RequestMapping("loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(MenuVo menuVo){
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
        List<SysMenu> list = this.menuService.queryAllMenuForList(menuVo);
        List<TreeNode> nodes = new ArrayList<>();
        //把list里面的数据放到nodes
        for (SysMenu menu : list){
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = menu.getSpread()==SysConstast.SPREAD_TRUE?true:false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id,pid,title,icon,href,spread,target));
        }
        return new DataGridView(nodes);
    }

    /**
     * 加载菜单列表返回DataGridView
     * @param menuVo
     * @return
     */
    @RequestMapping("loadAllMenu")
    public DataGridView loadAllMenu(MenuVo menuVo){
        return this.menuService.queryAllMenu(menuVo);
    }

    /**
     * 添加菜单
     * @param menuVo
     * @return
     */
    @RequestMapping("addMenu")
    public ResultObj addMenu(MenuVo menuVo){
        try{
            System.out.println(menuVo);
            this.menuService.addMenu(menuVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改菜单
     * @param menuVo
     * @return
     */
    @RequestMapping("updateMenu")
    public ResultObj updateMenu(MenuVo menuVo){
        try{
            this.menuService.updateMenu(menuVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据id判断当前菜单有没有子节点
     * 有 返回code>=0
     * 没有 返回code<0
     * @param menuVo
     * @return
     */
    @RequestMapping("checkMenuHasChildren")
    public ResultObj checkMenuHasChildren(MenuVo menuVo){
        //根据pid查询菜单数量
        Integer count = this.menuService.queryMenuByPid(menuVo.getId());
        System.out.println(count);
        if (count>0){
            return ResultObj.STATUS_TRUE;
        }else{
            return ResultObj.STATUS_FALSE;
        }
    }

    /**
     * 删除菜单
     * @param menuVo
     * @return
     */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(MenuVo menuVo){
        try{
            this.menuService.deleteMenu(menuVo);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


}
