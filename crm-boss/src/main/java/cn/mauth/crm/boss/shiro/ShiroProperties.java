package cn.mauth.crm.boss.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "spring.shiro")
public class ShiroProperties {

    private Map<String, String> filterChainDefinitionMap;
    private String loginUrl;
    private String successUrl;
    private String unauthorizedUrl;

    public ShiroProperties() {
    }

    public Map<String, String> getFilterChainDefinitionMap() {
        return this.filterChainDefinitionMap;
    }

    public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {
        this.filterChainDefinitionMap = filterChainDefinitionMap;
    }

    public String getLoginUrl() {
        return this.loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getSuccessUrl() {
        return this.successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getUnauthorizedUrl() {
        return this.unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }
}
