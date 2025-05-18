package com.zs.af.model;

import com.zs.af.Constant;

public class Order {

    OrderType orderType;
    Stock stock;
    Double buyPrice;
    Double sellPrice;
    Double targetPrice;
    Double stopLoss;


    public Order(OrderType orderType, Stock stock, Double buyPrice, Double sellPrice, Double targetPrice, Double stopLoss) {
        this.orderType = orderType;
        this.stock = stock;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.targetPrice = targetPrice;
        this.stopLoss = stopLoss;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(Double targetPrice) {
        this.targetPrice = targetPrice;
    }

    public Double getStopLoss() {
        return stopLoss;
    }

    public void setStopLoss(Double stopLoss) {
        this.stopLoss = stopLoss;
    }


    public Boolean checkIfTargetHit(Price p){

        if(this.getOrderType()==OrderType.BUY && p.getHigh()>=this.getTargetPrice()) {

            System.out.println("Target hit for Order type : "+this.getOrderType().toString()+" symbol = "+this.stock.getTradingSymbol()+ " "+this.getBuyPrice()+" "+p.getHigh() + "time :"+p.getDateTime());
            return true;
        }
        if(this.getOrderType()==OrderType.SELL && p.getHigh()<=this.getTargetPrice()){
            System.out.println("Target hit for Order type : "+this.getOrderType().toString()+" symbol = "+this.stock.getTradingSymbol()+ " "+this.getBuyPrice()+" "+p.getHigh()+ "time :"+p.getDateTime());
            return  true;
        }


        return  false;
    }

    public Boolean checkIfStopLossHit(Price p){

        if(this.orderType==OrderType.BUY && p.getHigh()<=this.getStopLoss()) {
            System.out.println("STop loss hit Order type : "+this.getOrderType().toString()+" symbol = "+this.stock.getTradingSymbol()+ " "+this.getBuyPrice()+" "+p.getHigh()+ "time :"+p.getDateTime());
            return true;
        }
        else if(this.orderType==OrderType.SELL && p.getHigh()>=this.getStopLoss()) {

            System.out.println("STop loss hit Order type : "+this.getOrderType().toString()+" symbol = "+this.stock.getTradingSymbol()+ " "+this.getSellPrice()+" "+p.getHigh()+ "time :"+p.getDateTime());
            return true;
        }

        return  false;
    }

    public void setTargetAndStopLossAndOrderType(OrderType orderType) throws Exception {


        if(orderType== OrderType.BUY){
            this.setOrderType(OrderType.BUY);
            this.setTargetPrice(this.getBuyPrice()+ Constant.TARGET_PROFIT);
            this.setStopLoss(this.getBuyPrice()-Constant.STOP_LOSS);
        }
        else {
            this.setOrderType(OrderType.SELL);
            this.setTargetPrice(this.getSellPrice()- Constant.TARGET_PROFIT);
            this.setStopLoss(this.getSellPrice()+Constant.STOP_LOSS);
        }
    }

}
