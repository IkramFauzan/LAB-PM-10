package com.example.praktikum6;

import java.util.List;

public class UserResponse {
    private List<User> data;

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}

//class ini digunakan untuk merepresentasikan respons yang berisi data pengguna dalam
// bentuk List dari objek User, dengan metode untuk mengakses dan mengatur data tersebut.