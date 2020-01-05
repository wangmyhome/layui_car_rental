package com.wang.sys.controller;

import com.wang.sys.service.SysLogInfoService;
import com.wang.sys.utils.DataGridView;
import com.wang.sys.utils.ResultObj;
import com.wang.sys.vo.LogInfoVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 日志管理控制器
 */
@RestController
@RequestMapping("logInfo")
public class LogInfoController {
    @Resource
    private SysLogInfoService logInfoService;

    /**
     * 加载日志列表返回DataGridView
     * @param logInfoVo
     * @return
     */
    @RequestMapping("loadAllLogInfo")
    public DataGridView loadAllLogInfo(LogInfoVo logInfoVo){
        return this.logInfoService.queryAllLogInfo(logInfoVo);
    }

    /**
     * 删除一条日志
     * @param logInfoVo
     * @return
     */
    @RequestMapping("deleteLogInfo")
    public ResultObj deleteLogInfo(LogInfoVo logInfoVo){
        try {
            this.logInfoService.deleteLogInfo(logInfoVo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除日志
     * @param logInfoVo
     * @return
     */
    @RequestMapping("deleteBatchLogInfo")
    public ResultObj deleteBatchLogInfo(LogInfoVo logInfoVo){
        try {
            this.logInfoService.deleteBatchLogInfo(logInfoVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
