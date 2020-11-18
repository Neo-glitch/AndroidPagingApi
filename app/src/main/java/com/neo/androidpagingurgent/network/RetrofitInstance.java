package com.neo.androidpagingurgent.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";  // answers/

//    private static RetrofitInstance mInstance;
    private static Retrofit mRetrofit;


    public static Retrofit getInstance() {
        return mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory. create())
                .build();
    }


}
