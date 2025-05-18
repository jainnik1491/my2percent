package com.zs.af.model;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Price {

    String exchange;
    Double ltp;
    Double open;
    Double high;
    Double low;
    Double close;
    Date dateTime;
    Stock stock;
    long volume;

    public Price(){

    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public Price(String exchange, String tradingSymbol, String symbolToken, long volume, Double ltp, Double open, Double high, Double low, Double close, String time) {
        this.volume=volume;
        this.exchange = exchange;
        this.stock = new Stock(tradingSymbol,symbolToken);
        this.ltp = ltp;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.setDateTime(time);

    }


    public void setDateTime(String timeStamp){

        String isoDate = timeStamp;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        try {
            // Parse the string into a Date object
            Date date = sdf.parse(isoDate);
            //System.out.println("Converted java.util.Date: " + date);
            this.dateTime=date;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Date getDateTime(){
        return this.dateTime;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "exchange='" + exchange + '\'' +
                ", tradingSymbol='" + this.stock.getTradingSymbol() + '\'' +
                ", symbolToken='" + this.stock.getSymbolToken() + '\'' +
                ", ltp=" + ltp +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", time="+dateTime+
                '}';
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }


    public Double getLtp() {
        return ltp;
    }

    public void setLtp(Double ltp) {
        this.ltp = ltp;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public void setStock(String tradingSymbol, String symbolToken){
        this.stock= new Stock(tradingSymbol,symbolToken);
    }

    public Stock getStock(){
        return this.stock;
    }


}