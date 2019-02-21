package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * 角色
 */
@Entity
public class SysRole extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**名称*/
    @Column(unique = true,updatable = false,length = 20)
    private String name;

    /**状态*/
    private int status;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "sys_role_auth",
    joinColumns = {@JoinColumn(name = "role_Id")},
    inverseJoinColumns = {@JoinColumn(name = "auth_id")})
    private Set<SysAuthority> sysAuthorities;

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

    public Set<SysAuthority> getSysAuthorities() {
        return sysAuthorities;
    }

    public void setSysAuthorities(Set<SysAuthority> sysAuthorities) {
        this.sysAuthorities = sysAuthorities;
    }
}
