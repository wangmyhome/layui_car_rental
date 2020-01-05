package com.wang.sys.vo;

import com.wang.sys.domain.SysMenu;

/**
 * @author shanpeng
 * @ClassName MenuVo
 * @description TODO
 * @date 2020/1/5 15:11
 * @Version 1.0
 */
public class MenuVo extends SysMenu {

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;


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
}
