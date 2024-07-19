package com.example.shop.network;

import com.example.shop.config.Config;
import com.example.shop.interceptors.JWTInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance;
    private Retrofit retrofit;

    public RetrofitClient() {
        HttpLoggingInterceptor interceptor =  new  HttpLoggingInterceptor ();
        interceptor.setLevel( HttpLoggingInterceptor.Level.BODY );

        OkHttpClient.Builder client =  new  OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new JWTInterceptor())
                .addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance=new RetrofitClient();
        }
        return instance;
    }
    public CategoriesApi getCategoriesApi() {
        return retrofit.create(CategoriesApi.class);
    }
    public PizzaApi getPizzaApi() {
        return retrofit.create(PizzaApi.class);
    }
    public AccountApi getAccountApi() { return retrofit.create(AccountApi.class); }



}