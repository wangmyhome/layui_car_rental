package com.wang.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wang.sys.mapper.SysMenuMapper;
import com.wang.sys.domain.SysMenu;
import com.wang.sys.service.SysMenuService;
import com.wang.sys.utils.DataGridView;
import com.wang.sys.vo.MenuVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shanpeng
 * @ClassName SysMenuServiceImpl
 * @description TODO
 * @date 2020/1/5 15:12
 * @Version 1.0
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {


    @Resource
    private SysMenuMapper menuMapper;
    /**
     * 查询所有菜单返回
     * @param menuVo
     * @return
     */
    @Override
    public List<SysMenu> queryAllMenuForList(MenuVo menuVo) {
        return menuMapper.queryAllMenu(menuVo);
    }


    /**
     * @param menuVo
     * @param userId
     * @return
     */
    @Override
    public List<SysMenu> queryMenuByUserIdForList(MenuVo menuVo, Integer userId) {
        return menuMapper.queryMenuByUid(menuVo.getAvailable(),userId);
    }

    /**
     * 查询所有菜单列表
     * @param menuVo
     * @return
     */
    @Override
    public DataGridView queryAllMenu(MenuVo menuVo) {
        Page<Object> page = PageHelper.startPage(menuVo.getPage(),menuVo.getLimit());
        List<SysMenu> data = this.menuMapper.queryAllMenu(menuVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 添加菜单
     * @param menuVo
     */
    @Override
    public void addMenu(MenuVo menuVo) {
        this.menuMapper.insertSelective(menuVo);
    }

    /**
     * 更新菜单
     * @param menuVo
     */
    @Override
    public void updateMenu(MenuVo menuVo) {
        this.menuMapper.updateByPrimaryKeySelective(menuVo);
    }

    /**
     * 根据pid查询菜单数量
     * @param pid
     * @return
     */
    @Override
    public Integer queryMenuByPid(Integer pid) {
        return this.menuMapper.queryMenuByPid(pid);
    }

    /**
     * 删除菜单
     * @param menuVo
     */
    @Override
    public void deleteMenu(MenuVo menuVo) {
        //删除菜单表的数据
        this.menuMapper.deleteByPrimaryKey(menuVo.getId());
        //根据菜单id删除sys_role_menu里面的数据
        this.menuMapper.deleteRoleMenuByMid(menuVo.getId());

    }
}
