package cn.mauth.crm.common.bean;

import java.io.Serializable;

public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**名称*/
    private String name;

    /**状态*/
    private int status;

    /**创建者Id*/
    private String creatorId;

    /**修改者Id*/
    private String modifiedId;

    /**所有者Id*/
    private String ownerId;

    /**备注*/
    private String remark;

    /**权限Id,以 ，隔开*/
    private String auth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
