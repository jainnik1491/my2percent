package com.zs.af;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.zs.af.model.Price;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;


public class Market {

    String marketType;
    String apiToken;
    String apiKey;
    final String BASEURL="https://apiconnect.angelone.in/rest/secure/angelbroking/market/v1";

    public Market(String marketType, String apiKey, String token) {

        this.marketType = marketType;
        this.apiKey=apiKey;
        this.apiToken=token;

    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBASEURL() {
        return BASEURL;
    }

    public Price getStockQuote(String symbolToken) throws UnirestException, InterruptedException {

       Unirest.config().verifySsl(false);

       System.out.println("API token is "+this.apiToken);

       HttpResponse<String> response = Unirest.post(BASEURL+"/quote/")
               .header("X-PrivateKey", this.apiKey)
               .header("X-UserType", "USER")
               .header("X-SourceID", "WEB")
               .header("Content-Type", "application/json")
               .header("X-ClientLocalIP", "")
               .header("X-ClientPublicIP", "")
               .header("X-MACAddress", "")
               .header("Authorization",this.apiToken)
               .header("Accept", "application/json")
               .body("{\r\n   \"mode\": \"FULL\",\r\n   \"exchangeTokens\": { \"NSE\": [\""+symbolToken+"\"] }\r\n}")
               .asString();


       JsonObject jsonObject=JsonParser.parseString(response.getBody()).getAsJsonObject();
       System.out.println("response recieved"+jsonObject.toString());

       if(!jsonObject.get("message").getAsString().equals("Invalid Token")){
            JsonArray jsonArray = jsonObject.getAsJsonObject("data").getAsJsonArray("fetched");
            List<Price> s = getStockByJSonArray(jsonArray);
            System.out.println("Stocks retrieved is " + s);
           return s.get(0);
        }
       else{
           this.apiToken=getAuthenticationToken(getTotp());
           return getStockQuote(symbolToken);
       }
    }

    public String getAuthenticationToken(String totp) throws InterruptedException {
         Thread.sleep(1000);
        Unirest.config().verifySsl(false);
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("clientcode","N51864425");
        jsonObject.addProperty("password","9090");
        jsonObject.addProperty("totp",totp);
        jsonObject.addProperty("state","state_or_environment_variable");

        String body=jsonObject.toString();
        System.out.println("body"+body);

        HttpResponse<String> response = Unirest.post("https://apiconnect.angelone.in/rest/auth/angelbroking/user/v1/loginByPassword")
                .header("X-PrivateKey", this.apiKey)
                .header("X-UserType", "USER")
                .header("X-SourceID", "WEB")
                .header("Content-Type", "application/json")
                .header("X-ClientLocalIP", "")
                .header("X-ClientPublicIP", "")
                .header("X-MACAddress", "")
                .header("Accept", "application/json")
                .body(body)
                .asString();


        JsonObject resjsonObject=JsonParser.parseString(response.getBody()).getAsJsonObject();
        String jwtToken=resjsonObject.getAsJsonObject("data").getAsJsonPrimitive("jwtToken").getAsString();

        String bearerToken="Bearer "+jwtToken;
       System.out.println(bearerToken);
        this.setApiToken(bearerToken);

        return bearerToken;

    }

    private List<Price> getStockByJSonArray(JsonArray jsonArray) {
        Type listType = new TypeToken<List<Price>>(){}.getType();
        List<Price> prices = new Gson().fromJson(jsonArray.toString(), listType);

        return prices;
    }

    public String getTotp(){

        Scanner scn = new Scanner(System.in);
        System.out.println("Please enter totp :");
        String totp = scn.nextLine();
        return totp;
    }

    public void  placeBracketOrder(){

    }

    public JSONArray fetchStockHistory(String symbolToken,String fromDate,String todate) {
        HttpResponse<String> response=null;
        try {
            Unirest.config().verifySsl(false);
            //System.out.println("API token is "+this.apiToken);
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode rootNode = mapper.createObjectNode();

            rootNode.put("exchange", "NSE");
            rootNode.put("symboltoken", symbolToken);
            rootNode.put("interval", Constant.INTERVAL);
            rootNode.put("fromdate", fromDate);//"2024-12-01 09:30"
            rootNode.put("todate", todate);//"2024-12-28 03:30"

            Thread.sleep(1000);
            String body = mapper.writeValueAsString(rootNode);
            //System.out.println("body= "+body);
             response = Unirest.post("https://apiconnect.angelone.in/rest/secure/angelbroking/historical/v1/getCandleData")
                    .header("X-PrivateKey", this.apiKey)
                    .header("X-UserType", "USER")
                    .header("X-SourceID", "WEB")
                    .header("Content-Type", "application/json")
                    .header("X-ClientLocalIP", "")
                    .header("X-ClientPublicIP", "")
                    .header("X-MACAddress", "")
                    .header("Authorization", this.apiToken)
                    .header("Accept", "application/json")
                    .body(body)
                    .asString();


            JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();

            if (!jsonObject.get("message").getAsString().equals("Invalid Token")) {

                JSONObject jsn = new JSONObject(jsonObject.toString());
                JSONArray dataArray = jsn.getJSONArray("data");

                return dataArray;
            } else {
                this.apiToken = getAuthenticationToken(getTotp());
                return fetchStockHistory(symbolToken, fromDate, todate);
            }

        }
        catch (Exception e ){
            System.out.println(response.getBody());
            System.out.println("malformed json ");
        }

    return new JSONArray();
    }


}
