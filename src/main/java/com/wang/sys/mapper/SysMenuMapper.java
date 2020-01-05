package com.wang.sys.mapper;

import com.wang.sys.domain.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    /**
     * 查询所有菜单
     */
    List<SysMenu> queryAllMenu(SysMenu menu);

    /**
     * 根据pid查询菜单数量
     * @param pid
     * @return
     */
    Integer queryMenuByPid(@Param("pid")Integer pid);

    /**
     * 根据菜单id删除sys_role_menu里面的数据
     * @param mid
     */
    void deleteRoleMenuByMid(@Param("mid") Integer mid);

    /**
     * 根据角色ID查询菜单
     * @param available
     * @param roleid
     * @return
     */
    List<SysMenu> queryMenuByRoleId(@Param("available") Integer available,@Param("rid") Integer roleid);

    /**
     * 根据用户id查询菜单
     * @param available
     * @param userId
     * @return
     */
    List<SysMenu> queryMenuByUid(@Param("available") Integer available,@Param("uid") Integer userId);
}