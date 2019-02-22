package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.bean.SessionInfo;
import cn.mauth.crm.common.service.RedisService;
import cn.mauth.crm.common.service.WxService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.AES;
import cn.mauth.crm.util.common.Result;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/crm/v1/wx")
@ApiModel("微信用户认证相关API")
public class WxAuthController extends BaseController{

    @Autowired
    private WxService wxService;

    @Autowired
    private RedisService redisService;

    @GetMapping(value = "/getSession", produces = "application/json")
    @ApiOperation("获取sessionId")
    public Result createSessionId(@RequestParam(value = "code") String wxCode){

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


    /**
     * 验证用户信息完整性
     * @param rawData	微信用户基本信息
     * @param signature	数据签名
     * @param sessionId	会话ID
     * @return
     */
    @GetMapping(value = "/checkUserInfo", produces = "application/json")
    @ApiOperation("验证用户信息完整性API")
    public Result checkUserInfo(@RequestParam(value = "rawData") String rawData,
                                            @RequestParam(value = "signature") String signature,
                                            @RequestParam(value = "sessionId") String sessionId){

        Object wxSessionObj = redisService.get(sessionId);

        if(null == wxSessionObj){
            return Result.of(40008, null);
        }

        String wxSessionStr = (String) wxSessionObj;

        SessionInfo sessionInfo = JSON.parseObject(wxSessionStr, SessionInfo.class);

        if(sessionInfo==null){
            return error("错误");
        }

        byte[] encryData = DigestUtils.sha1(sessionInfo.getSessionKey());

        byte[] signatureData = signature.getBytes();

        boolean checkStatus = Arrays.equals(encryData, signatureData);

        return ok(checkStatus);

    }

    /**
     * 获取用户openId和unionId数据(如果没绑定微信开放平台，解密数据中不包含unionId)
     * @param encryptedData 加密数据
     * @param iv			加密算法的初始向量
     * @param sessionId		会话ID
     * @return
     */
    @GetMapping(value = "/decodeUserInfo", produces = "application/json")
    @ApiOperation("获取用户openId和unionId数据")
    public Result decodeUserInfo(@RequestParam(value = "encryptedData") String encryptedData,
                                             @RequestParam(value = "iv") String iv,
                                             @RequestParam(value = "sessionId") String sessionId){
        //从缓存中获取session_key
        Object wxSessionObj = redisService.get(sessionId);

        if(null == wxSessionObj){
            return Result.of(40008, null);
        }

        String wxSessionStr = (String) wxSessionObj;
        SessionInfo sessionInfo = JSON.parseObject(wxSessionStr, SessionInfo.class);

        if(sessionInfo==null){
            return error("错误");
        }
        try {
            AES aes = new AES();

            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionInfo.getSessionKey()), Base64.decodeBase64(iv));

            if(null != resultByte && resultByte.length > 0){

                String userInfo = new String(resultByte, "UTF-8");

                return ok(userInfo);
            }
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Result.of(50021, null);
    }
}
