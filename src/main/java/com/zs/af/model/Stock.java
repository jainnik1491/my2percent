package com.zs.af.model;

public class Stock {

    String tradingSymbol;
    String symbolToken;

    public Stock(String tradingSymbol, String symbolToken) {
        this.tradingSymbol = tradingSymbol;
        this.symbolToken = symbolToken;
    }

    public String getTradingSymbol() {
        return tradingSymbol;
    }

    public void setTradingSymbol(String tradingSymbol) {
        this.tradingSymbol = tradingSymbol;
    }

    public String getSymbolToken() {
        return symbolToken;
    }

    public void setSymbolToken(String symbolToken) {
        this.symbolToken = symbolToken;
    }
}
