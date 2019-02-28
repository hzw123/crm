package cn.mauth.crm.common.bean;

import java.io.Serializable;

public class AccountStats implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long accountId;
    /**总共合同数*/
    private int contractTotal;
    /**商机数*/
    private int busTotal;
    /**联系人数量*/
    private int contactTotal;
    /**拜访数量*/
    private int visitTotal;

    public AccountStats() {
    }

    public AccountStats(Long accountId, int contractTotal, int busTotal, int contactTotal, int visitTotal) {
        this.accountId = accountId;
        this.contractTotal = contractTotal;
        this.busTotal = busTotal;
        this.contactTotal = contactTotal;
        this.visitTotal = visitTotal;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public int getContractTotal() {
        return contractTotal;
    }

    public void setContractTotal(int contractTotal) {
        this.contractTotal = contractTotal;
    }

    public int getBusTotal() {
        return busTotal;
    }

    public void setBusTotal(int busTotal) {
        this.busTotal = busTotal;
    }

    public int getContactTotal() {
        return contactTotal;
    }

    public void setContactTotal(int contactTotal) {
        this.contactTotal = contactTotal;
    }

    public int getVisitTotal() {
        return visitTotal;
    }

    public void setVisitTotal(int visitTotal) {
        this.visitTotal = visitTotal;
    }
}
