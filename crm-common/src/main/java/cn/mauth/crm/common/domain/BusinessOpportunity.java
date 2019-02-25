package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 商机
 */
@Entity
public class BusinessOpportunity extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**来源*/
    @Column(columnDefinition = "text")
    private String source;

    /**所属客户Id*/
    private Long accountId;

    /**联系人*/
    @OneToMany
    @JoinTable(name = "bus_contact",
    joinColumns = {@JoinColumn(name = "bus_id")},
    inverseJoinColumns = {@JoinColumn(name = "contact_id")})
    private Set<Contact> contacts;

    @Column(columnDefinition = "datetime")
    private Date endAt;

    /**金额*/
    private double amount;

    /**
     * 状态
     * 进行中,成功，失败
     */
    private int status;

    /**阶段*/
    @ManyToOne(cascade = {CascadeType.REMOVE})
    private Stage stage;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
