package com.example.instershipandroidtask.Interface;

import com.example.instershipandroidtask.modelclass.GraphData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfaceGraph {
    String BASE_URL = "https://www.dropbox.com/s/n4i57r22rdx89cw/";

    @GET("list2.json?dl=1")
    Call<List<GraphData>> getGraphData();
}
