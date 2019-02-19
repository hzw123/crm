package cn.mauth.crm.boss.controller;


import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @GetMapping("/login")
    @ApiOperation(value = "登录")
    public Result login(){
        Subject subject=SecurityUtils.getSubject();

        if(subject.isAuthenticated()){
            return Result.success("已经登录成功");
        }

        return Result.success("请先登录");
    }


    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public Result login(UsernamePasswordToken token){
        Subject subject=SecurityUtils.getSubject();

        if(subject.isAuthenticated()){
            return Result.success("已经登录成功");
        }

        subject.login(token);

        return Result.success("登录成功");
    }

    @GetMapping("/logout")
    @ApiOperation(value = "注销")
    public Result logout(){

        SecurityUtils.getSubject().logout();

        return Result.success("注销成功");
    }
}
