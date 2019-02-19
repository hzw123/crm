package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * 联系人
 */
@Entity
public class Contacts extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**名字*/
    @Column(nullable = false,length = 50)
    private String name;

    /**手机*/
    @Column(unique = true,nullable = false,length = 11)
    private String phone;

    /**邮箱*/
    private String email;

    /**生日*/
    private String birth;

    /**毕业院校*/
    private String school;

    /**兴趣爱好*/
    private String interest;

    /**所属客户*/
    @ManyToOne
    private Client client;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
