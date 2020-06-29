package com.example.instershipandroidtask.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

  public static String BASE_URL = "https://www.dropbox.com/s/1hh8vh7whv6cjme/";


//    public static Retrofit getClient() {api
//
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;

   // }

    public static OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder()
            .addNetworkInterceptor(new HttpLoggingInterceptor())
            .readTimeout(1000, TimeUnit.SECONDS);


    public static Retrofit getClient(){
        if(retrofit == null){


            /*
                Logging Interceptor for Retrofit Client
            */


            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okhttpBuilder.addInterceptor(loggingInterceptor);

            okhttpBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder newRequest = request.newBuilder().header("Authorization","Token "+"");
                    return chain.proceed(newRequest.build());

                }
            });

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okhttpBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();


        }
        return retrofit;
    }
}
