package com.wang.sys.mapper;

import com.wang.sys.domain.SysRoleUser;
import org.apache.ibatis.annotations.Param;

public interface SysRoleUserMapper {
    int deleteByPrimaryKey(@Param("uid") Integer uid, @Param("rid") Integer rid);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);
}