package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.SysRole;
import cn.mauth.crm.common.domain.SysUserInfo;
import cn.mauth.crm.common.properties.WxAuth;
import cn.mauth.crm.common.bean.SessionInfo;
import cn.mauth.crm.util.common.HexUtil;
import cn.mauth.crm.util.common.HttpRequest;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WxService {

    private final static Logger log= LoggerFactory.getLogger(WxService.class);

    @Autowired
    private WxAuth wxAuth;

    @Autowired
    private RedisService redisService;

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

        sb.append("&grant_type=").append(wxAuth.getGrantType());

        String res = HttpRequest.sendGet(wxAuth.getSessionHost(), sb.toString());

        if(res == null || res.equals("")){
            return null;
        }

        return JSON.parseObject(res, Map.class);
    }

    /**
     * 缓存微信openId和session_key
     * @param wxOpenId		微信用户唯一标识
     * @param wxSessionKey	微信服务器会话密钥
     * @param expires		会话有效期, 以秒为单位, 例如2592000代表会话有效期为30天
     * @return
     */
    public String create3rdSession(String wxOpenId, String wxSessionKey, Long expires){
        SysUserInfo userInfo=null;
        if(!sysUserInfoService.existByWxOpenId(wxOpenId)){
            userInfo=new SysUserInfo();

            userInfo.setAppId(wxAuth.getAppId());
            userInfo.setWxOpenId(wxOpenId);
            userInfo.setSalt(RandomStringUtils.randomAlphanumeric(32));
            userInfo.setPassword(HexUtil.md5Hex("123456"+userInfo.getSalt()));
            userInfo.setSessionKey(wxSessionKey);

            sysUserInfoService.add(userInfo);
        }

        String thirdSessionKey = RandomStringUtils.randomAlphanumeric(64);

        SessionInfo sessionInfo=new SessionInfo();

        userInfo=sysUserInfoService.findByWxOpenId(wxOpenId);

        sessionInfo.setOpenId(wxOpenId);
        sessionInfo.setSessionKey(wxSessionKey);
        sessionInfo.setUserId(userInfo.getId());
        sessionInfo.setUser(userInfo);

        for (SysRole role:userInfo.getSysRoles()) {
            if(role.getName().equals("admin")){
                sessionInfo.setAdmin(true);
                break;
            }
        }

        redisService.add(thirdSessionKey, expires, JSON.toJSONString(sessionInfo));

        return thirdSessionKey;
    }


}
