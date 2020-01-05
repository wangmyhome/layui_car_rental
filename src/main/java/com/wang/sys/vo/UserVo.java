package com.wang.sys.vo;

import com.wang.sys.domain.SysUser;

/**
 * @author shanpeng
 * @ClassName UserVo
 * @description TODO
 * @date 2020/1/5 13:20
 * @Version 1.0
 */
public class UserVo extends SysUser {

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    private String code;

    /**
     * 接受多个角色的id
     */
    private Integer [] ids;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
