package cn.mauth.crm.boss.controller;

import cn.mauth.crm.common.service.SysUserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private SysUserInfoService userService;

    @GetMapping("/")
    public String index(){
        return "欢迎竟然销售管理系统";
    }

    @GetMapping("/home")
    public String home(){
        return "销售管理系统";
    }



}
