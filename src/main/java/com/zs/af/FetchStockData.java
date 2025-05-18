/*
package com.zs.af;
import com.angelbroking.smartapi.SmartConnect;
import com.angelbroking.smartapi.http.SessionExpiryHook;
import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import com.angelbroking.smartapi.models.Order;
import com.angelbroking.smartapi.models.OrderParams;
import com.angelbroking.smartapi.models.TokenSet;
import com.angelbroking.smartapi.models.User;
import com.angelbroking.smartapi.utils.Constants;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;


public class FetchStockData {

    public static void main(String[] args) throws SmartAPIException, IOException {

        disableSslVerification();
        System.setProperty("com.sun.net.ssl.checkRevocation", "false");
        smartLtp();
    }


    public  static  void smartLtp() throws SmartAPIException, IOException {
        SmartConnect smartConnect = new SmartConnect();
        disableSslVerification();
        // Provide your API key here
        smartConnect.setApiKey("KrcoL3QO ");

        // Set session expiry callback.
        smartConnect.setSessionExpiryHook(new SessionExpiryHook() {
            @Override
            public void sessionExpired() {
                System.out.println("session expired");
            }
        });

        System.out.println("please enter totp:");
        String totp= new Scanner(System.in).nextLine();
        User user = smartConnect.generateSession("N51864425", "9090", totp);
        smartConnect.setAccessToken(user.getAccessToken());
        smartConnect.setUserId(user.getUserId());

        // token re-generate

        TokenSet tokenSet = smartConnect.renewAccessToken(user.getAccessToken(),
                user.getRefreshToken());
        smartConnect.setAccessToken(tokenSet.getAccessToken());

        getLTP(smartConnect);
    }

    private static void disableSslVerification() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static  void getLTP(SmartConnect smartConnect) throws SmartAPIException, IOException {
        String exchange = "NSE";
        String tradingSymbol = "SBIN-EQ";
        String symboltoken = "3045";
        JSONObject ltpData = smartConnect.getLTP(exchange, tradingSymbol, symboltoken);
    }


}*/
