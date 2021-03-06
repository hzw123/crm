package cn.mauth.crm.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *小程序配置
 */
@ConfigurationProperties("wxapp")
public class WxAuth {

    private String appId;

    private String secret;

    private String sessionHost;

    public WxAuth() {
    }

    public WxAuth(String appId, String secret, String sessionHost) {
        this.appId = appId;
        this.secret = secret;
        this.sessionHost = sessionHost;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSessionHost() {
        return sessionHost;
    }

    public void setSessionHost(String sessionHost) {
        this.sessionHost = sessionHost;
    }
}
