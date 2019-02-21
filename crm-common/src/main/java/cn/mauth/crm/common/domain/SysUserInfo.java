package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * 系统用户
 */
@Entity
public class SysUserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(unique = true,nullable = false,length = 50)
    private String userName;

    @Column(nullable = false,length = 50)
    private String pwd;

    @Column(nullable = false,length = 50)
    private String salt;

    @Column(length = 11)
    private String phone;

    @Column(length = 50)
    private String email;

    @ManyToMany
    @JoinTable(name = "sys_user_role",
    joinColumns = {@JoinColumn(name = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<SysRole> sysRoles;

    /**状态*/
    private int status;

    /**是否删除*/
    private boolean isDelete;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Set<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(Set<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
