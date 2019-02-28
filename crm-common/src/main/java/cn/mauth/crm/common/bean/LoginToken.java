package cn.mauth.crm.common.bean;

import java.io.Serializable;

public class LoginToken implements Serializable {

    private static final long serialVersionUID = 1L;

    /**登录手机号*/
    private String phone;

    /**登录密码*/
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
