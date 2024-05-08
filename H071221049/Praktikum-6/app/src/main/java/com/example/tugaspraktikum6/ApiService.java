package com.example.tugaspraktikum6;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    // ambil data users berdasarkan page
    @GET("api/users")
    Call<DataResponse> getData(@Query("page") String page);

    // ambil data user berdasarkan ID
    @GET("api/users/{id}")
    Call<ProfileResponse> getUser(@Path("id") String id);
}