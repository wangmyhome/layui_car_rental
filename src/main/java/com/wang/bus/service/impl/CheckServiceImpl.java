package com.wang.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wang.bus.mapper.CarMapper;
import com.wang.bus.mapper.CheckMapper;
import com.wang.bus.mapper.CustomerMapper;
import com.wang.bus.mapper.RentMapper;
import com.wang.bus.domain.Car;
import com.wang.bus.domain.Check;
import com.wang.bus.domain.Customer;
import com.wang.bus.domain.Rent;
import com.wang.bus.service.CheckService;
import com.wang.bus.vo.CheckVo;
import com.wang.sys.constast.SysConstast;
import com.wang.sys.domain.SysUser;
import com.wang.sys.utils.DataGridView;
import com.wang.sys.utils.RandomUtils;
import com.wang.sys.utils.WebUtils;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckServiceImpl implements CheckService {

    @Resource
    private CheckMapper checkMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private RentMapper rentMapper;

    @Resource
    private CarMapper carMapper;

    @Override
    public Map<String, Object> initCheckFormData(String rentid) {
        //查询出租单
        Rent rent = this.rentMapper.selectByPrimaryKey(rentid);
        //查询客户
        Customer customer = this.customerMapper.selectByPrimaryKey(rent.getIdentity());
        //查询车辆
        Car car = this.carMapper.selectByPrimaryKey(rent.getCarnumber());
        //组装check
        Check check = new Check();
        check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_JC));
        check.setRentid(rentid);
        check.setCheckdate(new Date());
        SysUser user =(SysUser) WebUtils.getHttpSession().getAttribute("user");
        check.setOpername(user.getRealname());
        Map<String, Object> map = new HashMap<>();
        map.put("rent",rent);
        map.put("customer",customer);
        map.put("car",car);
        map.put("check",check);

        return map;
    }

    /**
     * 保存检查单数据
     * @param checkVo
     */
    @Override
    public void addCheck(CheckVo checkVo) {
        this.checkMapper.insertSelective(checkVo);
        //更改出租单的状态
        Rent rent = this.rentMapper.selectByPrimaryKey(checkVo.getRentid());
        //更改为已归还
        rent.setRentflag(SysConstast.RENT_BACK_TRUE);
        this.rentMapper.updateByPrimaryKeySelective(rent);
        //更改汽车的状态
        Car car = this.carMapper.selectByPrimaryKey(rent.getCarnumber());
        //更改汽车状态为未出租
        car.setIsrenting(SysConstast.RENT_CAR_FALSE);
        this.carMapper.updateByPrimaryKeySelective(car);
    }

    /**
     * 查询所有检查单
     * @param checkVo
     * @return
     */
    @Override
    public DataGridView queryAllCheck(CheckVo checkVo) {
        Page<Object> page = PageHelper.startPage(checkVo.getPage(), checkVo.getLimit());
        List<Check> data = this.checkMapper.queryAllCheck(checkVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 批量删除检查单
     * @param ids
     */
    @Override
    public void deleteBatchCheck(String[] ids) {
        for (String id : ids) {
            this.checkMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 删除检查单
     * @param checkVo
     */
    @Override
    public void deleteCheck(CheckVo checkVo) {
        this.checkMapper.deleteByPrimaryKey(checkVo.getCheckid());
    }

    /**
     * 更新检查单
     * @param checkVo
     */
    @Override
    public void updateCheck(CheckVo checkVo) {
        this.checkMapper.updateByPrimaryKeySelective(checkVo);
    }
}