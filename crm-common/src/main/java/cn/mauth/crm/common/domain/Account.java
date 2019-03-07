package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

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
    private String type;

    /**场馆类型*/
    private String venueType;

    /**客户编码*/
    private String code;

    /**地址简称*/
    private String addressOr;

    /**地址*/
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

    /**供应商*/
    private String provider;

    /**购买时间*/
    @Column(columnDefinition = "datetime")
    private Date buyAt;

    /**微信公众号名称*/
    private String wxMp;

    /**信息来源*/
    private String source;

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

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Date getBuyAt() {
        return buyAt;
    }

    public void setBuyAt(Date buyAt) {
        this.buyAt = buyAt;
    }

    public String getWxMp() {
        return wxMp;
    }

    public void setWxMp(String wxMp) {
        this.wxMp = wxMp;
    }

    public String getVenueType() {
        return venueType;
    }

    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
