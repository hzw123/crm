package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 周报
 */
@Entity
public class Weekly extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**用户名*/
    @Column(updatable = false,length = 50)
    private String userName;

    /**上周总结*/
    @Column(updatable = false,length = 50)
    private String lastSummary;

    /**下周计划*/
    @Column(updatable = false,length = 50)
    private String nextPlan;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getLastSummary() {
        return lastSummary;
    }

    public void setLastSummary(String lastSummary) {
        this.lastSummary = lastSummary;
    }

    public String getNextPlan() {
        return nextPlan;
    }

    public void setNextPlan(String nextPlan) {
        this.nextPlan = nextPlan;
    }
}
