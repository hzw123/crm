package cn.mauth.crm.common.bean;

import cn.mauth.crm.common.domain.SysUserInfo;

import java.io.Serializable;

public class SessionInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long userId;

    private SysUserInfo user;

    private boolean isAdmin=false;

    private String sessionKey;

    private String openId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public SysUserInfo getUser() {
        return user;
    }

    public void setUser(SysUserInfo user) {
        this.user = user;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
