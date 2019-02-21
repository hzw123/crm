package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class SysAuthority extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**权限名称*/
    @Column(updatable = false,length = 100)
    private String name;

    /**权限路径*/
    private String path;

    /**状态*/
    private int status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
