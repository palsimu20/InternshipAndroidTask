package com.example.instershipandroidtask.activitites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.instershipandroidtask.Interface.ApiInterface;
import com.example.instershipandroidtask.R;
import com.example.instershipandroidtask.api.RetrofitClient;
import com.example.instershipandroidtask.modelclass.Data;
import com.example.instershipandroidtask.modelclass.DropBoxdata;
import com.example.instershipandroidtask.utility.DataAdapterClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_1 extends AppCompatActivity {
    private RecyclerView rv;
    private List<Data> imageLists;
    private ApiInterface apiInterface;
    private DataAdapterClass adapter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        rv=(RecyclerView)findViewById(R.id.recy);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
         mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<DropBoxdata> call = apiInterface.getProductData();
            call.enqueue(new Callback<DropBoxdata>() {
                @Override
                public void onResponse(Call<DropBoxdata> call, Response<DropBoxdata> response) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    List<Data> ProductData = response.body().getData();
                    adapter = new DataAdapterClass(ProductData, getApplicationContext());

                    rv.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<DropBoxdata> call, Throwable t) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                }
            });

    }
}
