package com.wang.sys.mapper;

import com.wang.sys.domain.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(@Param("rid") Integer rid, @Param("mid") Integer mid);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);
}