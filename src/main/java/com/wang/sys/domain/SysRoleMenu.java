package com.wang.sys.domain;

import java.io.Serializable;

public class SysRoleMenu implements Serializable {
    private Integer rid;

    private Integer mid;

    private static final long serialVersionUID = 1L;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}