package com.example.instershipandroidtask.Interface;

import com.example.instershipandroidtask.modelclass.GraphData;
import com.example.instershipandroidtask.modelclass.WebViewData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfaceWebview {
    String BASE_URLW = "https://www.dropbox.com/s/ep7v5yex3fjs3s1/";

    @GET("webview.json?dl=1")
    Call<List<WebViewData>> getWebViewData();
}
