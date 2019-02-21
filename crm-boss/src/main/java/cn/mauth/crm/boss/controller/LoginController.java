package cn.mauth.crm.boss.controller;


import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController extends BaseController{
//
//    @GetMapping("/login")
//    @ApiOperation(value = "登录")
//    public Result login(){
//        Subject subject=SecurityUtils.getSubject();
//
//        if(subject.isAuthenticated()){
//            return ok("已经登录成功");
//        }
//
//        log.info("请先登录");
//
//        return ok("请先登录");
//    }


    @GetMapping("/login")
    @ApiOperation(value = "登录")
    public Result login(UsernamePasswordToken token){
        Subject subject=SecurityUtils.getSubject();

        if(subject.isAuthenticated()){
            return ok("用户:"+token.getUsername()+"已经登录成功");
        }

        subject.login(token);

        log.info("用户:"+token.getUsername()+"登录成功");

        return ok("登录成功");
    }

    @GetMapping("/logout")
    @ApiOperation(value = "注销")
    public Result logout(){

        SecurityUtils.getSubject().logout();

        return ok("注销成功");
    }
}
