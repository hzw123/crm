package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * 客户
 */
@Entity
public class Client extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**客户名字*/
    @Column(updatable = false,length = 50)
    private String name;

    /**客户类型*/
    @Column(updatable = false)
    private String type;

    /**客户编码*/
    @Column(updatable = false,length = 100)
    private String code;

    /**区域*/
    private String area;

    /**地址*/
    private String address;

    /**客户联系电话*/
    @Column(unique = true,updatable = false,length = 11)
    private String phone;

    /**行业*/
    private String profession;

    /**职位*/
    private String job;

    /**所属公海*/
    @ManyToOne
    private HighSeas highSeas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public HighSeas getHighSeas() {
        return highSeas;
    }

    public void setHighSeas(HighSeas highSeas) {
        this.highSeas = highSeas;
    }
}
