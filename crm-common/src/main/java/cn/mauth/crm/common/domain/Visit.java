package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * 拜访
 */
@Entity
public class Visit extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**终端名称*/
    @Column(updatable = false,length = 50)
    private String terminalName;

    /**终端地址*/
    @Column(updatable = false,length = 100)
    private String terminalAddress;

    /**计划时间*/
    @Column(updatable = false,columnDefinition = "datetime")
    private Date planAt;

    /**签到时间*/
    @Column(columnDefinition = "datetime")
    private Date signIn;

    /**签退时间*/
    @Column(columnDefinition = "datetime")
    private Date signOut;

    /**拜访计划*/
    @Column(updatable = false,columnDefinition = "text")
    private String plan;

    /**拜访内容*/
    @Column(columnDefinition = "text")
    private String content;

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public String getTerminalAddress() {
        return terminalAddress;
    }

    public void setTerminalAddress(String terminalAddress) {
        this.terminalAddress = terminalAddress;
    }

    public Date getPlanAt() {
        return planAt;
    }

    public void setPlanAt(Date planAt) {
        this.planAt = planAt;
    }

    public Date getSignIn() {
        return signIn;
    }

    public void setSignIn(Date signIn) {
        this.signIn = signIn;
    }

    public Date getSignOut() {
        return signOut;
    }

    public void setSignOut(Date signOut) {
        this.signOut = signOut;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
