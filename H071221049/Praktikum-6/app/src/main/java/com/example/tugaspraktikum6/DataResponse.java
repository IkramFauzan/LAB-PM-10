package com.example.tugaspraktikum6;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
// memanggil array list dari user
public class DataResponse {
    @SerializedName("data")
    private ArrayList<User> data;

    public ArrayList<User> getData(){
        return data;
    }
}