package com.wang.sys.mapper;

import com.wang.sys.domain.SysLogLogin;

import java.util.List;

public interface SysLogLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLogLogin record);

    int insertSelective(SysLogLogin record);

    SysLogLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLogLogin record);

    int updateByPrimaryKey(SysLogLogin record);
    /**
     * 查询日志
     * @param logInfo
     * @return
     */
    List<SysLogLogin> queryAllLogInfo(SysLogLogin logInfo);
}