package com.example.tugaspraktikum6;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// ambil data dari url

public class ApiConfig {
    public static ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }
}