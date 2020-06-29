package com.example.instershipandroidtask.Interface;

import com.example.instershipandroidtask.modelclass.Data;
import com.example.instershipandroidtask.modelclass.DropBoxdata;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("list1.json?dl=1")
    Call<DropBoxdata> getProductData();
}
