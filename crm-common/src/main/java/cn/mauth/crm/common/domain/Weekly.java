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

    /**用户Id*/
    @Column(updatable = false,length = 50)
    private Long userId;

    /**上周总结*/
    @Column(updatable = false,length = 50)
    private String lastSummary;

    /**下周计划*/
    @Column(updatable = false,length = 50)
    private String nextPlan;
}
