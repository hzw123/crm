package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * 合同
 */
@Entity
public class Contract extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**合同名称*/
    @Column(updatable = false,length = 100)
    private String name;

    /**合同编码*/
    @Column(unique = true,updatable = false,length = 100)
    private String code;

    /**合同类型*/
    @Column(updatable = false,length = 100)
    private String type;

    /**合同标题*/
    @Column(updatable = false)
    private String title;

    /**所属客户Id*/
    private Long clientId;

    /**所属商机ID*/
    private Long busId;

    /**合同金额*/
    private double contractAmount;

    /**开始日期*/
    @Column(updatable = false,columnDefinition = "datetime")
    private Date startAt;

    /**结束日期*/
    @Column(updatable = false,columnDefinition = "datetime")
    private Date endAt;

    /**详情*/
    @Column(columnDefinition = "text")
    private String details;

    /**状态*/
    private int status;

    /**状态数量*/
    private int statusTotal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getBusId() {
        return busId;
    }

    public void setBusId(Long busId) {
        this.busId = busId;
    }

    public double getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatusTotal() {
        return statusTotal;
    }

    public void setStatusTotal(int statusTotal) {
        this.statusTotal = statusTotal;
    }
}
