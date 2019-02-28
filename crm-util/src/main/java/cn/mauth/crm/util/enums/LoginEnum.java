package cn.mauth.crm.util.enums;

public enum  LoginEnum {

    PHONE(1,"手机登录"),
    WEIXIN(2,"微信登录");

    private int code;
    private String name;

    LoginEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
