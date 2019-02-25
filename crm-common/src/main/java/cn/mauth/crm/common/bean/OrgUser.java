package cn.mauth.crm.common.bean;

import cn.mauth.crm.common.domain.OrganizeEmployee;
import cn.mauth.crm.common.domain.SysUserInfo;

import java.io.Serializable;

public class OrgUser implements Serializable{

    private static final long serialVersionUID = 1L;

    private SysUserInfo sysUserInfo;

    private OrganizeEmployee organizeEmployee;

    public OrgUser() {
    }

    public OrgUser(SysUserInfo sysUserInfo, OrganizeEmployee organizeEmployee) {
        this.sysUserInfo = sysUserInfo;
        this.organizeEmployee = organizeEmployee;
    }

    public SysUserInfo getSysUserInfo() {
        return sysUserInfo;
    }

    public void setSysUserInfo(SysUserInfo sysUserInfo) {
        this.sysUserInfo = sysUserInfo;
    }

    public OrganizeEmployee getOrganizeEmployee() {
        return organizeEmployee;
    }

    public void setOrganizeEmployee(OrganizeEmployee organizeEmployee) {
        this.organizeEmployee = organizeEmployee;
    }
}
