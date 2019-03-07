package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.boss.shiro.UserToken;
import cn.mauth.crm.common.bean.LoginToken;
import cn.mauth.crm.common.service.WxService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import cn.mauth.crm.util.enums.LoginEnum;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crm/v1/login")
@ApiModel("登录模块")
public class LoginController extends BaseController{

    @Autowired
    private WxService wxService;

    @GetMapping
    @ApiOperation(value = "登录首页")
    public Result login(){
        if(SecurityUtils.getSubject().isAuthenticated()){
            return ok("用户已经登录");
        }
        return Result.UNAUTHORIZED;
    }

    @PostMapping
    @ApiOperation(value = "手机登录")
    public Result login(LoginToken loginToken){
        Subject subject= SecurityUtils.getSubject();

        if(subject.isAuthenticated()){
            return ok("用户:"+loginToken.getPhone()+"已经登录成功");
        }

        UserToken token=new UserToken();

        token.setUsername(loginToken.getPhone());

        token.setPassword(loginToken.getPassword().toCharArray());

        token.setLoginType(LoginEnum.PHONE);

        subject.login(token);

        log.info("用户:"+loginToken.getPhone()+"登录成功");

        JSONObject jsonObject=new JSONObject();

        jsonObject.put("sessionId",subject.getSession().getId());

        return ok(jsonObject);
    }


    @GetMapping(value = "/wx",produces = "application/json")
    @ApiOperation(value = "微信登录")
    public Result wxLogin(@RequestParam("code") String code){

        Subject subject= SecurityUtils.getSubject();

        String openId=wxService.getOpenId(code);

        if(openId==null)
            return error("没有找到openId");

        if(subject.isAuthenticated()){
            return ok("用户openId:"+openId+"已经登录成功");
        }

        UserToken token=new UserToken();

        token.setUsername(openId);

        token.setLoginType(LoginEnum.WEIXIN);

        token.setPassword("111111".toCharArray());//为了防止异常,密码没有意义

        subject.login(token);

        log.info("用户微信登录成功");

        JSONObject jsonObject=new JSONObject();

        jsonObject.put("sessionId",subject.getSession().getId());

        return ok(jsonObject);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "注销")
    public Result logout(){

        SecurityUtils.getSubject().logout();

        return ok("注销成功");
    }

}
