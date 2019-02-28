package cn.mauth.crm.common.bean;

import java.io.Serializable;

public class BusStats implements Serializable {

    private static final long serialVersionUID = 1L;

    /**商机总数*/
    private int busTotal;

    /**成交量*/
    private int turnover;

    /**流失数*/
    private int lossNumber;

    /**赢单率*/
    private String winRate;

    /**流失率*/
    private String lossRate;

    public BusStats() {
    }

    public BusStats(int busTotal, int turnover, int lossNumber) {
        this.busTotal = busTotal;
        this.turnover = turnover;
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
}
