package com.wang.sys.vo;

import com.wang.sys.domain.SysLogLogin;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author shanpeng
 * @ClassName LogInfoVo
 * @description TODO
 * @date 2020/1/5 13:34
 * @Version 1.0
 */
public class LogInfoVo extends SysLogLogin {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    /**
     * 扩展表单参数  将前台时间提交到后台
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    //接受多个id
    private Integer [] ids;

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

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
