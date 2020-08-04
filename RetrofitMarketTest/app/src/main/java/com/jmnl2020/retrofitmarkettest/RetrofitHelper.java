package com.jmnl2020.retrofitmarkettest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {

    public static Retrofit getInstance(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://jmnl.dothome.co.kr");
        builder.addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }

    public static Retrofit getInstance2(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://jmnl.dothome.co.kr");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        //스트링으로 받도록

        return builder.build();
    }

}
