package cn.mauth.crm.common.bean;

import java.io.Serializable;
import java.util.List;

public class BusStats implements Serializable {

    private static final long serialVersionUID = 1L;

    /**商机总数*/
    private int busTotal;

    /**商机总金额*/
    private double busTotalAmount;

    /**成交量*/
    private int turnover;

    /**成交总金额*/
    private double turnoverAmount;

    /**流失数*/
    private int lossNumber;

    /**赢单率*/
    private String winRate;

    /**流失率*/
    private String lossRate;

    private List<StageStats> list;

    public BusStats() {
    }

    public BusStats(int busTotal, double busTotalAmount, int turnover, double turnoverAmount, int lossNumber) {
        this.busTotal = busTotal;
        this.busTotalAmount = busTotalAmount;
        this.turnover = turnover;
        this.turnoverAmount = turnoverAmount;
        this.lossNumber = lossNumber;
        this.init();
    }

    private void init(){
        if(busTotal>0){
            double rate=this.turnover*1.00*100/this.busTotal;
            this.winRate=rate+"%";
            rate=this.lossNumber*1.00*100/this.busTotal;
            this.lossRate=rate+"%";
        }else{
            this.winRate="0.00%";
            this.lossRate="0.00";
        }
    }

    public double getBusTotalAmount() {
        return busTotalAmount;
    }

    public void setBusTotalAmount(double busTotalAmount) {
        this.busTotalAmount = busTotalAmount;
    }

    public double getTurnoverAmount() {
        return turnoverAmount;
    }

    public void setTurnoverAmount(double turnoverAmount) {
        this.turnoverAmount = turnoverAmount;
    }

    public int getBusTotal() {
        return busTotal;
    }

    public void setBusTotal(int busTotal) {
        this.busTotal = busTotal;
    }

    public int getTurnover() {
        return turnover;
    }

    public void setTurnover(int turnover) {
        this.turnover = turnover;
    }

    public int getLossNumber() {
        return lossNumber;
    }

    public void setLossNumber(int lossNumber) {
        this.lossNumber = lossNumber;
    }

    public String getWinRate() {
        return winRate;
    }

    public void setWinRate(String winRate) {
        this.winRate = winRate;
    }

    public String getLossRate() {
        return lossRate;
    }

    public void setLossRate(String lossRate) {
        this.lossRate = lossRate;
    }

    public List<StageStats> getList() {
        return list;
    }

    public void setList(List<StageStats> list) {
        this.list = list;
    }
}
