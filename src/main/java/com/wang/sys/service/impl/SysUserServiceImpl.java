package com.wang.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wang.sys.constast.SysConstast;
import com.wang.sys.mapper.SysRoleMapper;
import com.wang.sys.mapper.SysUserMapper;
import com.wang.sys.domain.SysRole;
import com.wang.sys.domain.SysUser;
import com.wang.sys.service.SysUserService;
import com.wang.sys.utils.DataGridView;
import com.wang.sys.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shanpeng
 * @ClassName SysUserServiceImpl
 * @description TODO
 * @date 2020/1/5 13:15
 * @Version 1.0
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;

    /**
     * 用户登陆
     * @param userVo
     * @return
     */
    @Override
    public SysUser login(UserVo userVo) {
        //明文123456
        //生成密文
        String pwd = DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes());
        System.out.println(pwd);
        userVo.setPwd(pwd);
        return sysUserMapper.login(userVo);
    }

    /**
     * 查询所有用户
     * @param userVo
     * @return
     */
    @Override
    public DataGridView queryAllUser(UserVo userVo) {
        Page<Object> page = PageHelper.startPage(userVo.getPage(),userVo.getLimit());
        List<SysUser> data = this.sysUserMapper.queryAllUser(userVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 添加用户
     * @param userVo
     */
    @Override
    public void addUser(UserVo userVo) {
        //设置默认密码
        userVo.setPwd(DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes()));
        //设置用户类型 都是普通用户 type=2
        userVo.setType(SysConstast.USER_TYPE_NORMAL);
        this.sysUserMapper.insertSelective(userVo);
    }

    /**
     * 更新用户
     * @param userVo
     */
    @Override
    public void updateUser(UserVo userVo) {
        this.sysUserMapper.updateByPrimaryKeySelective(userVo);
    }

    /**
     * 删除用户
     * @param userid
     */
    @Override
    public void deleteUser(Integer userid) {
        this.sysUserMapper.deleteByPrimaryKey(userid);
        //根据用户id删除sys_role_user里面的数据
        this.sysRoleMapper.deleteRoleUserByUid(userid);
    }

    /**
     * 批量删除用户
     * @param ids
     */
    @Override
    public void deleteBatchUser(Integer[] ids) {
        for (Integer uid : ids) {
            this.deleteUser(uid);
        }
    }

    /**
     * 重置用户的密码
     * @param userid
     */
    @Override
    public void resetUserPwd(Integer userid) {
        SysUser user = new SysUser();
        user.setUserid(userid);
        //设置默认密码
        user.setPwd(DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes()));
        //更新
        this.sysUserMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 加载用户管理的分配角色的数据
     * @param userid
     * @return
     */
    @Override
    public DataGridView queryUserRole(Integer userid) {
        //1.查询所有可用的角色
        SysRole role = new SysRole();
        role.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<SysRole> allRole = this.sysRoleMapper.queryAllRole(role);
        //2.根据用户ID查询已拥有的角色
        List<SysRole> userRole=this.sysRoleMapper.queryRoleByUid(SysConstast.AVAILABLE_TRUE,userid);

        List<Map<String,Object>> data = new ArrayList<>();

        for (SysRole r1 : allRole){
            Boolean LAY_CHECKED=false;
            for (SysRole r2 : userRole) {
                if (r1.getRoleid()==r2.getRoleid()){
                    LAY_CHECKED=true;
                }
            }
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("roleid",r1.getRoleid());
            map.put("rolename",r1.getRolename());
            map.put("roledesc",r1.getRoledesc());
            map.put("LAY_CHECKED",LAY_CHECKED);
            data.add(map);

        }

        return new DataGridView(data);
    }

    /**
     * 保存用户和角色的关系
     * @param userVo
     */
    @Override
    public void saveUserRole(UserVo userVo) {
        Integer userid = userVo.getUserid();
        Integer[] roleIds = userVo.getIds();
        //根据用户id删除sys_role_user里面的数据
        this.sysRoleMapper.deleteRoleUserByUid(userid);
        //保存关系
        if (roleIds!=null&&roleIds.length>0){
            for (Integer rid : roleIds){
                this.sysUserMapper.insertUserRole(userid,rid);
            }
        }
    }
}
