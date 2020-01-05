package com.wang.sys.domain;

import java.io.Serializable;

public class SysRoleUser implements Serializable {
    private Integer uid;

    private Integer rid;

    private static final long serialVersionUID = 1L;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}