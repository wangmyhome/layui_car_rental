package com.wang.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wang.sys.constast.SysConstast;
import com.wang.sys.mapper.SysMenuMapper;
import com.wang.sys.mapper.SysRoleMapper;
import com.wang.sys.domain.SysMenu;
import com.wang.sys.domain.SysRole;
import com.wang.sys.service.SysRoleService;
import com.wang.sys.utils.DataGridView;
import com.wang.sys.utils.TreeNode;
import com.wang.sys.vo.RoleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shanpeng
 * @ClassName SysRoleUserServiceImpl
 * @description TODO
 * @date 2020/1/5 18:04
 * @Version 1.0
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleMapper roleMapper;

    @Resource
    private SysMenuMapper menuMapper;

    /**
     * 查询所有菜单返回
     * @param roleVo
     * @return
     */
    @Override
    public List<SysRole> queryAllRoleForList(RoleVo roleVo) {
        return roleMapper.queryAllRole(roleVo);
    }

    /**
     * @param roleVo
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> queryRoleByUserIdForList(RoleVo roleVo, Integer userId) {
        return roleMapper.queryAllRole(roleVo);
    }

    /**
     * 查询所有角色列表
     * @param roleVo
     * @return
     */
    @Override
    public DataGridView queryAllRole(RoleVo roleVo) {
        Page<Object> page = PageHelper.startPage(roleVo.getPage(),roleVo.getLimit());
        List<SysRole> data = this.roleMapper.queryAllRole(roleVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 添加角色
     * @param roleVo
     */
    @Override
    public void addRole(RoleVo roleVo) {
        this.roleMapper.insertSelective(roleVo);
    }

    /**
     * 更新角色
     * @param roleVo
     */
    @Override
    public void updateRole(RoleVo roleVo) {
        this.roleMapper.updateByPrimaryKeySelective(roleVo);
    }


    /**
     * 根据角色roleid单个删除角色
     * @param roleid
     */
    @Override
    public void deleteRole(Integer roleid) {
        //删除角色表的数据
        this.roleMapper.deleteByPrimaryKey(roleid);
        //根据角色id删除sys_role_menu里面的数据
        this.roleMapper.deleteRoleMenuByRid(roleid);
        //根据角色id删除sys_role_user里面的数据
        this.roleMapper.deleteRoleUserByRid(roleid);

    }

    /**
     * 根据前台页面传来的数组批量删除角色
     * @param ids
     */
    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer rid : ids){
            deleteRole(rid);
        }
    }

    @Override
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        //查询所有的可用的菜单
        SysMenu menu = new SysMenu();
        menu.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<SysMenu> allMenu = menuMapper.queryAllMenu(menu);
        //根据角色ID查询当前角色拥有的菜单
        List<SysMenu> roleMenu = menuMapper.queryMenuByRoleId(SysConstast.AVAILABLE_TRUE,roleid);

        List<TreeNode> data = new ArrayList<>();
        for (SysMenu m1 : allMenu) {
            String checkArr = SysConstast.CODE_ZERO+"";
            for (SysMenu m2 : roleMenu) {
                if (m1.getId()==m2.getId()){
                    checkArr=SysConstast.CODE_ONE+"";
                    break;
                }
            }
            Integer id = m1.getId();
            Integer pid = m1.getPid();
            String title = m1.getTitle();
            Boolean spread = m1.getSpread()==SysConstast.SPREAD_TRUE?true:false;
            data.add(new TreeNode(id,pid,title,spread,checkArr));
        }

        return new DataGridView(data);
    }

    @Override
    public void saveRoleMenu(RoleVo roleVo) {
        Integer rid=roleVo.getRoleid();
        Integer [] mids=roleVo.getIds();
        //根据rid删除sys_role_menu里面的所有数据
        this.roleMapper.deleteRoleMenuByRid(rid);
        //保存角色和菜单的关系
        for (Integer mid : mids) {
            this.roleMapper.insertRoleMenu(rid,mid);
        }
    }
}
