package com.zs.af;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zs.af.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Automate {


    final static String APIKEY = "KrcoL3QO";

    public static void main(String args[]) throws Exception {

        Market m = new Market("nse", APIKEY, "");
        System.out.println("1 : Analyze from stock json");
        System.out.println("2 : Analyze from stock API");
        System.out.println("Please enter your choice :");

        Scanner sc = new Scanner(System.in);
        int choice= sc.nextInt();
        switch (choice){

            case 1 : analyzefromstockjson(m);
                break;
            case 2 : analyzebyAPI(m);
                break;
            case 3 :fetchandDisplayStock(m);

        }
    }

    public static void analyzebyAPI(Market m) {
        List<Stock> sckList=GetStockList();

        sckList.forEach(stock -> {
            try {
                Thread.sleep(2000);
                Stratergy mystrat= new Stratergy(stock,m);

                JSONArray dataArray = m.fetchStockHistory(stock.getSymbolToken(), Constant.FROM_DATE,Constant.TO_DATE);
                if(dataArray.length()>0) {
                    List <Price> priceList=getPriceListByJSON(dataArray,stock);
                    Result r = mystrat.applyStratergy(stock,priceList);
                    System.out.println(r);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    private static List<Price> getPriceListByJSON(JSONArray dataArray, Stock stock) {

        List<Price> priceList = new ArrayList<>();

        Constant.TOTAL_LENGTH=dataArray.length();
        for(int i=0;i<Constant.TOTAL_LENGTH;i++){
            JSONArray row = dataArray.getJSONArray(i);
            priceList.add(new Price("",stock.getSymbolToken(),stock.getTradingSymbol(),
                    row.getLong(5),
                    row.getDouble(2), row.getDouble(1),
                    row.getDouble(2),row.getDouble(3),
                    row.getDouble(4),row.getString(0)));
        }

      return priceList;
    }


    public  static void analyzefromstockjson(Market m) throws Exception {
         JSONObject jsn = new JSONObject(getResource("jsonData.json"));
         JSONArray dataArray = jsn.getJSONArray("data");

         Stock s =new Stock("Silver ETF ","Silver ETF ");

        if(dataArray.length()>0) {
            List<Price> priceList = new ArrayList<>();
            priceList=getPriceListByJSON(dataArray,s);
            Stratergy mystrat= new Stratergy(s,m);
            Result r = mystrat.applyStratergy(s,priceList);
            System.out.println(r);
        }

    }
    public static void fetchandDisplayStock(Market m) {
        List<Stock> priceList = GetStockList();

        priceList.forEach((s) -> {
            Price res = null;
            try {
                res = m.getStockQuote(s.getSymbolToken());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(res);
         });
    }

    public static JSONArray GetStockHistoryList(String json, Price ss) {

        JSONObject jsonObject = new JSONObject(json);
        JSONArray dataArray = jsonObject.getJSONArray("data");

        return  dataArray;
    }

    private static List<Steps> GenerateSteps() {
        List<Steps> steps = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getFileFromResourceAsStream("step.csv")));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                Steps s = new Steps(values[0], values[1], values[2], values[3]);
                steps.add(s);
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return steps;
    }

    private static List<Stock> GetStockList() {
        List<Stock> stockList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getFileFromResourceAsStream(Constant.fileName)));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Stock s =new Stock(values[1],values[2]);
                stockList.add(s);
                //System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockList;
    }


    public static String getResource(String resource) {
        StringBuilder json = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(Objects.requireNonNull(Automate.class.getClassLoader().getResourceAsStream(resource)),
                            StandardCharsets.UTF_8));
            String str;
            while ((str = in.readLine()) != null)
                json.append(str);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException("Caught exception reading resource " + resource, e);
        }
        return json.toString();
    }

    public static InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = Automate.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

}
