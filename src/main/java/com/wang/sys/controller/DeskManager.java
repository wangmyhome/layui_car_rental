package com.wang.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shanpeng
 * @ClassName DeskManager
 * @description 工作台的控制器
 * @date 2020/1/5 14:56
 * @Version 1.0
 */
@Controller
@RequestMapping("desk")
public class DeskManager {

    /**
     * 跳转到工作台的页面
     */
    @RequestMapping("toDeskManager")
    public String toDeskManager(){
        return "system/main/deskManager";
    }
}
