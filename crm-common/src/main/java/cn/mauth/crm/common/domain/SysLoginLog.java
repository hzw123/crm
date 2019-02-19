package cn.mauth.crm.common.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/***
 * 登录日志
 */
@Entity
public class SysLoginLog implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**登录时间*/
    @Column(updatable = false,columnDefinition = "datetime")
    private Date loginAt;
    /**用户名*/
    private String userName;
    /**IP地址*/
    private String ip;
    /**详情*/
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(Date loginAt) {
        this.loginAt = loginAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
