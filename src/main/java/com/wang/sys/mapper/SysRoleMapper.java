package com.wang.sys.mapper;

import com.wang.sys.domain.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 查询角色
     * @param role
     * @return
     */
    List<SysRole> queryAllRole(SysRole role);

    /**
     * 根据角色id删除sys_role_menu里面的数据
     * @param roleid
     */
    void deleteRoleMenuByRid(Integer roleid);

    /**
     * 根据角色id删除sys_role_user里面的数据
     * @param roleid
     */
    void deleteRoleUserByRid(Integer roleid);

    /**
     * 保存角色和菜单的关系sys_role_menu
     * @param rid
     * @param mid
     */
    void insertRoleMenu(@Param("rid") Integer rid, @Param("mid") Integer mid);

    /**
     * 根据用户id删除sys_role_user里面的数据
     * @param userid
     */
    void deleteRoleUserByUid(Integer userid);

    /**
     * 根据用户id查询角色
     * @param available
     * @param userid
     * @return
     */
    List<SysRole> queryRoleByUid(@Param("available") Integer available,@Param("uid") Integer userid);
}