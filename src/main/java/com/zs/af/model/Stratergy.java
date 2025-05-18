package com.zs.af.model;

import com.zs.af.Market;
import java.util.ArrayList;

import java.util.List;

public class Stratergy extends BaseStratergy{

    Market market;
    Stock stock;
    List<Order> orderList=new ArrayList<>();

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Stratergy(Stock stock, Market market) throws Exception {

        this.market=market;
        this.stock=stock;
    }

    public Stratergy(){ }

    Boolean decideBuyOrSell() throws Exception {


        /*String fromDate=String.format("%s %s",this.formatDate(this.dateTime),Constant.INDEX_CHECK_START_TIME);

        String toDate=String.format("%s %s",this.formatDate(this.dateTime),Constant.INDEX_CHECK_END_TIME);

        //System.out.println("fromdate:"+fromDate+" todate : "+toDate);

        JSONArray jsa=market.fetchStockHistory(Constant.INDEX_SYMBOL_TOKEN,fromDate,toDate);

        Double ninethirty=jsa.getJSONArray(0).getDouble(2);
        Double tennineteen=jsa.getJSONArray(jsa.length()-1).getDouble(2);

        //System.out.println(ninethirty +   "         "+tennineteen);
        if((tennineteen-ninethirty)>=Constant.INDEX_OFFSET_CHECK){
            re turn true;
        }
        else if(ninethirty-tennineteen>=Constant.INDEX_OFFSET_CHECK){
            return false;
        }
        else{
            throw  new Exception("Skipped the trade");
        }*/


 /*       if(prevHigh==prevClose) return true;

        else if(prevLow==prevClose) return false;*/

        return true;
    }

    public boolean checkIfStratergyHitTime(Price p) {

        return  p.getDateTime().getHours()== 15 && p.getDateTime().getMinutes()==25;
    }


}
