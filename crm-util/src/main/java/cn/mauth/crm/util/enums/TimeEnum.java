package cn.mauth.crm.util.enums;

public enum  TimeEnum {
    DAY(1,"%Y%m%d"),
    WEEK(2,"%Y%u"),
    MONTH(3,"%Y%m"),
    YEAR(4,"%Y");
    private int code;
    private String name;

    TimeEnum(int code, String name) {
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
