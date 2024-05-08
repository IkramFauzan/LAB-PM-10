package com.example.praktikum4;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Account implements Parcelable {

    private String name;
    private String username;
    private Integer profile;
    private Integer post;
    private String content;
    private Uri urigambar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getProfile() {
        return profile;
    }

    public void setProfile(Integer profile) {
        this.profile = profile;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Uri getUrigambar() {
        return urigambar;
    }

    public void setUrigambar(Uri urigambar) {
        this.urigambar = urigambar;
    }

    public Account(String name, String username, Integer profile, Integer post, String content, Uri urigambar) {
        this.name = name;
        this.username = username;
        this.profile = profile;
        this.post = post;
        this.content = content;
        this.urigambar = urigambar;
    }
    protected Account(Parcel in) {
        name = in.readString();
        username = in.readString();
        if (in.readByte() == 0) {
            profile = null;
        } else {
            profile = in.readInt();
        }
        if (in.readByte() == 0) {
            post = null;
        } else {
            post = in.readInt();
        }
        content = in.readString();
        urigambar = in.readParcelable(Uri.class.getClassLoader());
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
        dest.writeString(name);
        dest.writeString(username);
        if (profile == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(profile);
        }
        if (post == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(post);
        }
        dest.writeString(content);
        dest.writeParcelable(urigambar, flags);
    }
}
