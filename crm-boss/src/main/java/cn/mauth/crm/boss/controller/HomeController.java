package cn.mauth.crm.boss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @RequestMapping("/")
    public String index(){
        return "欢迎竟然销售管理系统";
    }

    @RequestMapping("/home")
    public String home(){
        return "销售管理系统";
    }
}
