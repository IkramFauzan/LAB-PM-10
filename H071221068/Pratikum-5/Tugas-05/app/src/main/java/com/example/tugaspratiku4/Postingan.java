package com.example.tugaspratiku4;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class Postingan implements Parcelable {
    private String name, bio;
    private String desc;
    private int profil;
    private int post;
    private Uri selectedImageUri;

    public Postingan(String name, String bio, String desc, int profil, int post) {
        this.name = name;
        this.bio = bio;
        this.desc = desc;
        this.profil = profil;
        this.post = post;
    }

    public Postingan(String defaultNama, String defaultBio, String konten, int defaultProfil, Uri selectedImageUri) {
        this.name = defaultNama;
        this.bio = defaultBio;
        this.desc = konten;
        this.profil = defaultProfil;
        this.selectedImageUri = selectedImageUri;
    }

    protected Postingan(Parcel in) {
        name = in.readString();
        bio = in.readString();
        desc = in.readString();
        profil = in.readInt();
        post = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(bio);
        dest.writeString(desc);
        dest.writeInt(profil);
        dest.writeInt(post);
//        dest.writeParcelable(selectedImageUri, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Postingan> CREATOR = new Creator<Postingan>() {
        @Override
        public Postingan createFromParcel(Parcel in) {
            return new Postingan(in);
        }

        @Override
        public Postingan[] newArray(int size) {
            return new Postingan[size];
        }
    };

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

    public int getProfil() {
        return profil;
    }

    public void setProfil(int profil) {
        this.profil = profil;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Uri getSelectedImageUri() {
        return selectedImageUri;
    }

    public void setSelectedImageUri(Uri selectedImageUri) {
        this.selectedImageUri = selectedImageUri;
    }
}
