package com.wang.sys.service;

import com.wang.sys.domain.SysMenu;
import com.wang.sys.utils.DataGridView;
import com.wang.sys.vo.MenuVo;

import java.util.List;

public interface SysMenuService {
    /**
     * 查询所有菜单返回
     * @param menuVo
     * @return
     */
    public List<SysMenu> queryAllMenuForList(MenuVo menuVo);

    /**
     * 根据用户id查询用户的可用菜单
     */
    public List<SysMenu> queryMenuByUserIdForList(MenuVo menuVo,Integer userId);

    /**
     * 查询所有菜单
     * @param menuVo
     * @return
     */
    public DataGridView queryAllMenu(MenuVo menuVo);

    /**
     * 添加菜单
     * @param menuVo
     */
    public void addMenu(MenuVo menuVo);

    /**
     * 修改菜单
     * @param menuVo
     */
    public void updateMenu(MenuVo menuVo);

    /**
     * 根据pid查询菜单数量
     * @param pid
     * @return
     */
    public Integer queryMenuByPid(Integer pid);

    /**
     * 根据id删除菜单
     * @param menuVo
     */
    public void deleteMenu(MenuVo menuVo);
}
