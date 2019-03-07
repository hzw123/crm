package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.SysUserInfo;
import cn.mauth.crm.common.properties.WxAuth;
import cn.mauth.crm.util.common.*;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WxService {

    @Autowired
    private WxAuth wxAuth;

    @Autowired
    private SysUserInfoService sysUserInfoService;


    /**
     * 根据小程序登录返回的code获取openid和session_key
     * https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html?t=20161107
     * @param wxCode
     * @return
     */
    public Map<String,Object> getWxSession(String wxCode){

        StringBuffer sb = new StringBuffer();

        sb.append("appid=").append(wxAuth.getAppId());

        sb.append("&secret=").append(wxAuth.getSecret());

        sb.append("&js_code=").append(wxCode);


        String res = HttpRequest.sendGet(wxAuth.getSessionHost(), sb.toString());

        if(res == null || res.equals("")){
            return null;
        }

        return JSON.parseObject(res, Map.class);
    }

    public String getOpenId(String code) {

        Map<String,Object> wxSessionMap = this.getWxSession(code);

        if(null == wxSessionMap){
            return null;
        }

        //获取异常
        if(wxSessionMap.containsKey("errcode")){
            return null;
        }

        String wxOpenId = (String) wxSessionMap.get("openid");

        String wxSessionKey = (String) wxSessionMap.get("session_key");

        String wxUnionId=(String) wxSessionMap.get("unionid");


        this.addUser(wxOpenId,wxSessionKey,wxUnionId);

        return wxOpenId;
    }

    private void addUser(String openId,String sessionKey,String unionId){

        if(!sysUserInfoService.existByWxOpenId(openId)){

            SysUserInfo userInfo=new SysUserInfo();

            userInfo.setAppId(wxAuth.getAppId());

            userInfo.setWxOpenId(openId);

            userInfo.setWxUnionId(unionId);

            userInfo.setSalt(RandomStringUtils.randomAlphanumeric(32));

            userInfo.setPassword(HexUtil.md5Hex(userInfo.getSalt()+"123456"));

            userInfo.setSessionKey(sessionKey);

            sysUserInfoService.save(userInfo);
        }
    }

}
