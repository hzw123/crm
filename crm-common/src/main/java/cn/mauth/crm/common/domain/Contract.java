package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * 合同
 */
@Entity
public class Contract extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 合同名称
     */
    private String name;

    /**
     * 合同编号
     */
    private String code;

    /**
     * 合同类型
     */
    private String type;

    /**
     * 合同标题
     */
    private String title;

    /**
     * 合同签约时间
     */
    @Column(columnDefinition = "datetime")
    private Date signAt;

    /**
     * 所属客户Id
     */
    private Long accountId;

    /**
     * 所属商机ID
     */
    private Long busId;

    /**
     * 合同金额
     */
    private double contractAmount;

    /**
     * 开生效时间
     */
    @Column(columnDefinition = "datetime")
    private Date startAt;

    /**
     * 结束日期
     */
    @Column(columnDefinition = "datetime")
    private Date endAt;

    /**
     * 合同明细
     */
    @Column(columnDefinition = "text")
    private String details;

    /**
     * 状态
     */
    private int status;

    /**
     * 合同阶段
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Stage stage;

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

    public Date getSignAt() {
        return signAt;
    }

    public void setSignAt(Date signAt) {
        this.signAt = signAt;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
