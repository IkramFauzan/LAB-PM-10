package com.example.praktikum6;

public class UserDetailResponse {
    private User data;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}

//class ini gunanya yaitu untuk menyediakan sebuah pembungkus untuk objek User,
// sehingga ketika kita ingin mengirim atau menerima data pengguna (user) dari
// suatu sumber atau destinasi (misalnya, melalui jaringan),
// kita bisa gunakan kelas UserDetailResponse ini.