package com.wang.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wang.sys.mapper.SysLogLoginMapper;
import com.wang.sys.domain.SysLogLogin;
import com.wang.sys.service.SysLogInfoService;
import com.wang.sys.utils.DataGridView;
import com.wang.sys.vo.LogInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shanpeng
 * @ClassName SysLogInfoServiceImpl
 * @description 日志管理
 * @date 2020/1/5 13:56
 * @Version 1.0
 */
@Service
public class SysLogInfoServiceImpl implements SysLogInfoService {
    @Resource
    private SysLogLoginMapper logInfoMapper;

    /**
     * 查询日志
     * @param logInfoVo
     * @return
     */
    @Override
    public DataGridView queryAllLogInfo(LogInfoVo logInfoVo) {
        Page<Object> page= PageHelper.startPage(logInfoVo.getPage(),logInfoVo.getLimit());
        List<SysLogLogin> data = this.logInfoMapper.queryAllLogInfo(logInfoVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 添加日志
     * @param logInfoVo
     */
    @Override
    public void addLogInfo(LogInfoVo logInfoVo) {
        this.logInfoMapper.insertSelective(logInfoVo);
    }

    /**
     * 删除单个日志
     * @param logInfoid
     */
    @Override
    public void deleteLogInfo(Integer logInfoid) {
        this.logInfoMapper.deleteByPrimaryKey(logInfoid);
    }

    /**
     * 批量删除日志
     * @param ids
     */
    @Override
    public void deleteBatchLogInfo(Integer[] ids) {
        for (Integer id : ids) {
            this.deleteLogInfo(id);
        }
    }
}
