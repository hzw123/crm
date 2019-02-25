package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 联系人
 */
@Entity
public class Contact extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**名字*/
    @Column(nullable = false,length = 50)
    private String name;

    /**电话*/
    @Column(unique = true,nullable = false,length = 11)
    private String telephone ;

    /**手机*/
    @Column(unique = true,nullable = false,length = 11)
    private String phone;

    /**部门*/
    @Column(nullable = false,length = 100)
    private String department;

    /**职位*/
    @Column(nullable = false,length = 100)
    private String job;

    /**上级*/
    @Column(nullable = false,length = 100)
    private String superior;

    /**邮箱*/
    private String email;

    /**生日*/
    private String birth;

    /**毕业院校*/
    private String school;

    /**兴趣爱好*/
    private String interest;

    /**所属客户ID*/
    private Long accountId;

    /**来源*/
    @Column(nullable = false)
    private String source;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
