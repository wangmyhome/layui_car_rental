package com.wang.bus.service;

import com.wang.bus.domain.Rent;
import com.wang.bus.vo.RentVo;
import com.wang.sys.utils.DataGridView;

/**
 * @author shanpeng
 * @ClassName SysRentService
 * @description TODO
 * @date 2020/1/5 19:09
 * @Version 1.0
 */
public interface RentService {
    /**
     * 保存出租单信息
     * @param rentVo
     */
    void addRent(RentVo rentVo);

    /**
     * 查询
     * @param rentVo
     */
    DataGridView queryAllRent(RentVo rentVo);

    /**
     * 修改出租单
     * @param rentVo
     */
    void updateRent(RentVo rentVo);

    /**
     * 删除出租单
     * @param rentid
     */
    void deleteRent(String rentid);

    /**
     * 根据出租单号查询出租单信息
     * @paraentid
     * @return
     */
    Rent queryRentByRentId(String rentid);
}
