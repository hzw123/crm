package cn.mauth.crm.common.bean;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appId;

    /**微信openId*/
    private String wxOpenId;

    /**微信sessionKey*/
    private String sessionKey;

    /**微信UnionId*/
    private String wxUnionId;

    /**手机号*/
    private String phone;

    private String salt;

    /**加密后的密码*/
    private String password;

    /**真实姓名*/
    private String realName;
    /**昵称*/
    private String nickName;

    /**性别*/
    private String gender;

    /**邮箱*/
    private String email;


    /**状态*/
    private int status;

    /**入职时间*/
    private Date hireDate;

    /**是否显示*/
    private boolean disabled;

    /**创建者Id*/
    private String creatorId;

    /**修改者Id*/
    private String modifiedId;

    /**所有者Id*/
    private String ownerId;

    /**备注*/
    private String remark;

    /**角色*/
    private String roles;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getWxUnionId() {
        return wxUnionId;
    }

    public void setWxUnionId(String wxUnionId) {
        this.wxUnionId = wxUnionId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getModifiedId() {
        return modifiedId;
    }

    public void setModifiedId(String modifiedId) {
        this.modifiedId = modifiedId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

}
