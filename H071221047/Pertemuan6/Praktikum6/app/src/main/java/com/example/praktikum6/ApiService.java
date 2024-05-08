package com.example.praktikum6;

// mengimpor kelas Call dari library Retrofit dan digunakan untuk
// mengirim permintaan HTTP dan menerima responsnya.
import retrofit2.Call;

//digunakan untuk menandai metode yang akan melakukan permintaan GET HTTP.
import retrofit2.http.GET;

//digunakan untuk menggantikan variabel bagian dari URL permintaan dengan
//nilai yang spesifik (misalnya ID pengguna).
import retrofit2.http.Path;

//digunakan untuk menambahkan parameter query ke URL (misalnya ?page=2).
import retrofit2.http.Query;

//mendefinisikan berbagai endpoint API yang akan dipanggil.
public interface ApiService {
    @GET("api/users")
    Call<UserResponse> getUsers(@Query("page") int page);
    @GET("api/users/{id}")
    Call<UserDetailResponse> getUserDetails(@Path("id") int userId);
}
