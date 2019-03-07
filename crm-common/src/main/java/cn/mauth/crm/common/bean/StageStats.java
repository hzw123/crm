package cn.mauth.crm.common.bean;

import java.io.Serializable;

public class StageStats implements Serializable {

    private static final long serialVersionUID = 1L;

    /**阶段名称*/
    private String name;
    /**阶段排序*/
    private int sort;
    /**阶段商机数*/
    private int total;
    /**阶段总金额*/
    private double totalAmount;

    public StageStats() {
    }

    public StageStats(String name, int sort, int total, double totalAmount) {
        this.name = name;
        this.sort = sort;
        this.total = total;
        this.totalAmount = totalAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
