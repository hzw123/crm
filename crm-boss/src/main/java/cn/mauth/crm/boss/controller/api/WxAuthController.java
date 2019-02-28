package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.bean.SessionInfo;
import cn.mauth.crm.common.service.RedisService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.AES;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;

@RestController
@RequestMapping("/crm/v1/wx")
@ApiModel("微信用户认证相关API")
public class WxAuthController extends BaseController{


    @Autowired
    private RedisService redisService;

    /**
     * 验证用户信息完整性
     * @param rawData	微信用户基本信息
     * @param signature	数据签名
     * @param sessionId	会话ID
     * @return
     */
    @GetMapping(value = "/checkUserInfo", produces = "application/json")
    @ApiOperation("验证用户信息完整性API")
    public Result checkUserInfo( String rawData, String signature, String sessionId){

        SessionInfo sessionInfo = redisService.getSessionInfo(sessionId);

        if(null == sessionInfo){
            return Result.of(40008, null);
        }

        byte[] encryData = DigestUtils.sha1(rawData+sessionInfo.getSessionKey());

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
    public Result decodeUserInfo( String encryptedData, String iv, String sessionId){
        //从缓存中获取session_key
        SessionInfo sessionInfo = redisService.getSessionInfo(sessionId);

        if(null == sessionInfo){
            return Result.of(40008, null);
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
