package com.wang.bus.controller;

import com.wang.bus.domain.Rent;
import com.wang.bus.service.CheckService;
import com.wang.bus.service.RentService;
import com.wang.bus.vo.CheckVo;
import com.wang.sys.utils.DataGridView;
import com.wang.sys.utils.ResultObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 检查单管理的控制器
 */
@RestController
@RequestMapping("check")
public class CheckController {

    @Resource
    private RentService rentService;

    @Resource
    private CheckService checkService;

    /**
     * 根据出租单号查询出租单信息
     * @param rentid
     * @return
     */
    @RequestMapping("checkRentExist")
    public Rent checkRentExist(String rentid){
        //出租单号不存在，返回一个null，出租单号存在，返回一个rent对象
        Rent rent = rentService.queryRentByRentId(rentid);
        return rent;
    }

    /**
     * 根据出租单号加载检查单的表单数据
     * @param rentid
     * @return
     */
    @RequestMapping("initCheckFormData")
    public Map<String,Object> initCheckFormData(String rentid){
        return this.checkService.initCheckFormData(rentid);
    }

    /**
     * 保存检查单数据
     * @param checkVo
     * @return
     */
    @RequestMapping("saveCheck")
    public ResultObj saveCheck(CheckVo checkVo){
        try{
            checkVo.setCreatetime(new Date());
            this.checkService.addCheck(checkVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 查询所有检查单
     * @param checkVo
     * @return
     */
    @RequestMapping("loadAllCheck")
    public DataGridView loadAllCheck(CheckVo checkVo){
        return this.checkService.queryAllCheck(checkVo);
    }

    /**
     * 删除一个检查单
     * @param checkVo
     * @return
     */
    @RequestMapping("deleteCheck")
    public ResultObj deleteCheck(CheckVo checkVo){
        try{
            this.checkService.deleteCheck(checkVo);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除检查单
     * @return
     */
    @RequestMapping("deleteBatchCheck")
    public ResultObj deleteBatchCheck(CheckVo checkVo){
        try{
            this.checkService.deleteBatchCheck(checkVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 更新检查单
     * @param checkVo
     * @return
     */
    @RequestMapping("updateCheck")
    public ResultObj updateCheck(CheckVo checkVo){
        try {
            this.checkService.updateCheck(checkVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
}
