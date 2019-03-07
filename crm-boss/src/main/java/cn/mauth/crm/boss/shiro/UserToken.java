package cn.mauth.crm.boss.shiro;

import cn.mauth.crm.util.enums.LoginEnum;
import org.apache.shiro.authc.UsernamePasswordToken;

public class UserToken extends UsernamePasswordToken{

    private LoginEnum loginType;

    public LoginEnum getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginEnum loginType) {
        this.loginType = loginType;
    }
}
