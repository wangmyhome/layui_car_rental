package com.wang.bus.mapper;


import com.wang.bus.domain.Check;
import com.wang.bus.vo.CheckVo;

import java.util.List;

public interface CheckMapper {
    int deleteByPrimaryKey(String checkid);

    int insert(Check record);

    int insertSelective(Check record);

    Check selectByPrimaryKey(String checkid);

    int updateByPrimaryKeySelective(Check record);

    int updateByPrimaryKey(Check record);

    List<Check> queryAllCheck(CheckVo checkVo);
}