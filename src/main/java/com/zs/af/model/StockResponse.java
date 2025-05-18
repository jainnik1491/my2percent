package com.zs.af.model;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;

public class StockResponse {

    String status;
    String message;
    String errorcode;
    Double[][] data= new Double[8000][6];

    @Override
    public String toString() {
        return "StockResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", errorcode='" + errorcode + '\'' +
                '}';
    }

    public  StockResponse(){


    }
    public StockResponse(String status, String message, String errorcode) {
        this.status = status;
        this.message = message;
        this.errorcode = errorcode;
        //this.data = data;
    }

    public Double[][] getData() {
        return data;
    }

    public void setData(Double[][] data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

   /* public List<stockdata> getData() {
        return data;
    }

    public void setData(List<stockdata> data) {
        this.data = data;
    }*/


    //List<stockdata> data;
}


