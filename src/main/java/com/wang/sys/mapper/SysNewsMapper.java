package com.wang.sys.mapper;

import com.wang.sys.domain.SysNews;

import java.util.List;

public interface SysNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysNews record);

    int insertSelective(SysNews record);

    SysNews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysNews record);

    int updateByPrimaryKey(SysNews record);

    List<SysNews> queryAllNews(SysNews news);
}