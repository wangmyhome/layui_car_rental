package com.wang.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wang.bus.mapper.CarMapper;
import com.wang.bus.mapper.RentMapper;
import com.wang.bus.domain.Car;
import com.wang.bus.domain.Rent;
import com.wang.bus.service.RentService;
import com.wang.bus.vo.RentVo;
import com.wang.sys.constast.SysConstast;
import com.wang.sys.utils.DataGridView;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    @Resource
    private RentMapper rentMapper;

    @Resource
    private CarMapper carMapper;

    @Override
    public void addRent(RentVo rentVo) {
        this.rentMapper.insertSelective(rentVo);
        //更改车辆的出租状态
        Car car = new Car();
        car.setCarnumber(rentVo.getCarnumber());
        car.setIsrenting(SysConstast.RENT_CAR_TRUE);    //设置车辆为已出租状态
        carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public DataGridView queryAllRent(RentVo rentVo) {
        Page<Object> page = PageHelper.startPage(rentVo.getPage(),rentVo.getLimit());
        List<Rent> data = this.rentMapper.queryAllRent(rentVo);
        return new DataGridView(page.getTotal(),data);

    }

    @Override
    public void updateRent(RentVo rentVo) {
        this.rentMapper.updateByPrimaryKeySelective(rentVo);
    }

    @Override
    public void deleteRent(String rentid) {
        //更改汽车状态，将已出租的状态转换成未出租的状态
        Rent rent = this.rentMapper.selectByPrimaryKey(rentid);
        Car car = new Car();
        car.setCarnumber(rent.getCarnumber());
        //转换成未出租的状态
        car.setIsrenting(SysConstast.RENT_CAR_FALSE);
        carMapper.updateByPrimaryKeySelective(car);
        this.rentMapper.deleteByPrimaryKey(rentid);
    }

    /**
     * 根据出租单好查询出租单信息
     * @param rentid
     * @return
     */
    @Override
    public Rent queryRentByRentId(String rentid) {
        return this.rentMapper.selectByPrimaryKey(rentid);
    }

}
