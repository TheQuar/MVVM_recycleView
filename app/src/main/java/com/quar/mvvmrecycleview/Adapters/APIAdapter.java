package com.quar.mvvmrecycleview.Adapters;

import com.quar.mvvmrecycleview.API.API;

import java.security.PublicKey;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIAdapter {
    private static APIAdapter retrofitClient;
    private Retrofit retrofit;

    public APIAdapter(String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static synchronized APIAdapter getInstance(String url) {
        if (retrofitClient == null) {
            retrofitClient = new APIAdapter(url);
        }
        return retrofitClient;
    }


    public API getApi() {
        return retrofit.create(API.class);
    }
}
