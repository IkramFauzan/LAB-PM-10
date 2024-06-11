package com.example.praktikum6;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/users")
    // api/users?page=2
    Call<UserResponse> getUsers(@Query("page") int page);
    @GET("api/users/{id}")
    // api/users/1
    Call<SingleUserResponse> getUsersById(@Path("id") int userId);
}
