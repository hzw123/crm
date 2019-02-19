package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 机构
 */
@Entity
public class Organization extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**机构名称*/
    @Column(unique = true,updatable = false,length = 10)
    private String name;
    /**机构代码*/
    @Column(unique = true,updatable = false,length = 10)
    private String code;
    /**机构状态*/
    private int status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
