package com.zs.af.model;

public class Result {

    int targetHit;
    int stopLossHit;
    long percentage;
    String startDate;
    String endDate;
    int totalDays;
    Stock stock;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Result(String symbolToken, String tradingSymbol, int targetHit, int stopLossHit, long percentage, int totalDays) {

        this.stock= new Stock(tradingSymbol,symbolToken);
        this.targetHit = targetHit;
        this.stopLossHit = stopLossHit;
        this.percentage = percentage;
        this.startDate = "";
        this.endDate = "";
        this.totalDays=totalDays;
    }

    @Override
    public String toString() {

        return String.format("%s target hit = %s stop Loss= %s total Trading days= %s", this.getStock().getTradingSymbol(), this.targetHit, this.stopLossHit,this.totalDays);
    }


    public int getTargetHit() {
        return targetHit;
    }

    public void setTargetHit(int targetHit) {
        this.targetHit = targetHit;
    }

    public int getStopLossHit() {
        return stopLossHit;
    }

    public void setStopLossHit(int stopLossHit) {
        this.stopLossHit = stopLossHit;
    }

    public long getPercentage() {
        return percentage;
    }

    public void setPercentage(long percentage) {
        this.percentage = percentage;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


}
