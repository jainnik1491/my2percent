package com.zs.af.model;

import com.zs.af.Constant;
import com.zs.af.Market;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BaseStratergy {

    Market market;
    Stock stock;
    int totaldays=0;
    int targetHitday=0;
    int stoplossHit=0;
    List<Order> orderList=new ArrayList<>();

    abstract  boolean checkIfStratergyHitTime(Price p);
    abstract  Boolean decideBuyOrSell() throws Exception;

    public void placeOrder(Stock stock, Double buyPrice, Double sellPrice,Date datetime) throws Exception {

        Order order= new Order(OrderType.BUY,stock,buyPrice,sellPrice,0.0,0.0);

        order.setStock(stock);
        if(decideBuyOrSell())
            order.setTargetAndStopLossAndOrderType(OrderType.BUY);
        else
            order.setTargetAndStopLossAndOrderType(OrderType.SELL);

        this.orderList.add(order);
    }


    public Result applyStratergy(Stock stock,List<Price> priceList){

        Boolean checking=false;
        Constant.TOTAL_LENGTH=priceList.size();
        for(int i=0;i<Constant.TOTAL_LENGTH;i++){
            Price p =priceList.get(i);

            if(checkIfStratergyHitTime(p) && checking==false ){
                ++i;
                p= priceList.get(i);
                try {
                    this.placeOrder(stock, p.getHigh(), p.getHigh(), p.getDateTime());
                    checking=true;
                    this.totaldays++;
                }catch (Exception e ){
                    e.printStackTrace();
                    System.out.println("skipped the trade");
                    checking=false;
                }
            }
            else if(checking==true && this.getCurrentOrder().checkIfTargetHit(p)){
                checking=false;
                this.targetHitday++;
            }
            else if(checking==true && this.getCurrentOrder().checkIfStopLossHit(p)){
                checking=false;
                this.stoplossHit++;
            }
        }

        return new Result(this.getStock().getSymbolToken(),this.getStock().getTradingSymbol(),this.targetHitday,this.stoplossHit,0,this.totaldays);
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Order getCurrentOrder(){
        return this.orderList.get(this.orderList.size()-1);
    }


}
