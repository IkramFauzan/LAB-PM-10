package com.example.praktikum5;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Account implements Parcelable {

    private String username;
    private String name;
    private String desc;
    private int profile;
    private int post;
    private Uri Urigambar;
    public Account(String username, String name, String desc, int profile, int post, Uri urigambar) {
        this.username = username;
        this.name = name;
        this.desc = desc;
        this.profile = profile;
        this.post = post;
        Urigambar = urigambar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public Uri getUrigambar() {
        return Urigambar;
    }

    public void setUrigambar(Uri urigambar) {
        Urigambar = urigambar;
    }



    protected Account(Parcel in) {
        username = in.readString();
        name = in.readString();
        desc = in.readString();
        profile = in.readInt();
        post = in.readInt();
        Urigambar = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeInt(profile);
        dest.writeInt(post);
        dest.writeParcelable(Urigambar, flags);
    }
}
