package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 目标
 */
@Entity
public class Target extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**名称*/
    private String name;

    /**团队*/
    private String team;

    /**团队负责人*/
    private String teamWheel;

    /**目标金额*/
    private double targetAmount;

    /**目标有效的客户数*/
    private int validTotal;

    /**统计方式*/
    private String method;

    /**状态*/
    private int status;

    /**状态数*/
    private int statusTotal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeamWheel() {
        return teamWheel;
    }

    public void setTeamWheel(String teamWheel) {
        this.teamWheel = teamWheel;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public int getValidTotal() {
        return validTotal;
    }

    public void setValidTotal(int validTotal) {
        this.validTotal = validTotal;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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
