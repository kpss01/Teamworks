package com.example.teamwork;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class AsyncClient {
   private static final String BASE_URL="";

    private static AsyncHttpClient client = new AsyncHttpClient();


    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setConnectTimeout(20000);
        client.setResponseTimeout(20000);
        client.setTimeout(20000);
        //client.setAuthenticationPreemptive(true);
        //client.addHeader("Authkey","#SeRVICE_AuTH_KeY_TeKr@eerfm#");
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setConnectTimeout(20000);
        client.setResponseTimeout(20000);
        client.setTimeout(20000);
       // client.setAuthenticationPreemptive(true);
       // client.addHeader("Authkey","#SeRVICE_AuTH_KeY_TeKr@eerfm#");
        client.post(getAbsoluteUrl(url),params , responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
