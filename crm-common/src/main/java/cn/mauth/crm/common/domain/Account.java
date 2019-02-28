package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 客户
 */
@Entity
public class Account extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**客户名称*/
    private String name;

    /**网站*/
    @Column(length = 200)
    private String website;

    /**客户类型*/
    @Column(updatable = false)
    private String type;

    /**客户编码*/
    private String code;

    /**地址简称*/
    @Column(updatable = false,length = 100)
    private String addressOr;

    /**地址*/
    @Column(updatable = false,length = 200)
    private String address;

    /**客户联系电话*/
    private String phone;

    /**行业*/
    private String profession;

    /**年收入*/
    private double annualIncome;

    /**面积*/
    private String acreage;

    /**员工数*/
    private int staffNumber;

    /**会员数*/
    private int memberNumber;

    /**是否属于公海*/
    private boolean isCommon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public String getAddressOr() {
        return addressOr;
    }

    public void setAddressOr(String addressOr) {
        this.addressOr = addressOr;
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

    public double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    public int getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(int staffNumber) {
        this.staffNumber = staffNumber;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public boolean isCommon() {
        return isCommon;
    }

    public void setCommon(boolean common) {
        isCommon = common;
    }
}
