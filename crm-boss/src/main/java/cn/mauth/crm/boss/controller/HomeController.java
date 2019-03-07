package cn.mauth.crm.boss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("/")
    public String index(){
        return "欢迎竟然销售管理系统";
    }

    @GetMapping("/home")
    public String home(){
        return "销售管理系统";
    }

}
