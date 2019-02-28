package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.bean.LoginToken;
import cn.mauth.crm.common.service.SysUserInfoService;
import cn.mauth.crm.common.service.WxService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/crm/v1/login")
@ApiModel("登录模块")
public class LoginController extends BaseController{

    @Autowired
    private SysUserInfoService sysUserInfoService;

    @Autowired
    private WxService wxService;


    @GetMapping(value = "/wx", produces = "application/json")
    @ApiOperation("微信登录获取sessionId")
    public Result wxLogin(@RequestParam(value = "code") String wxCode){

        Map<String,Object> wxSessionMap = wxService.getWxSession(wxCode);

        if(null == wxSessionMap){
            return Result.of(50010, null);
        }

        //获取异常
        if(wxSessionMap.containsKey("errcode")){
            return Result.of(50020, wxSessionMap.get("errmsg").toString());
        }

        String wxOpenId = (String) wxSessionMap.get("openid");

        String wxSessionKey = (String) wxSessionMap.get("session_key");

        Long expires = Long.valueOf(String.valueOf(wxSessionMap.get("expires_in")));

        String sessionId = wxService.create3rdSession(wxOpenId, wxSessionKey, expires);

        return ok(sessionId);
    }

    @PostMapping("/phone")
    @ApiOperation("手机登录获取sessionId")
    public Result phoneLogin(LoginToken token){
        return sysUserInfoService.login(token);
    }

}
