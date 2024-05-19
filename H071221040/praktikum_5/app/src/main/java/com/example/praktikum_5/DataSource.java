package com.example.praktikum_5;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<Instagram> instagrams =generateDummyInstagram();

    private static ArrayList<Instagram> generateDummyInstagram() {
        ArrayList<Instagram> instagrams1 = new ArrayList<>();
        instagrams1.add(new Instagram("fara", "Al Aini Syam", "black or white"
                ,R.drawable.profil1, R.drawable.post1));

        instagrams1.add(new Instagram("salsa", "Afifah Salsabila", "dreaming about ny"
                ,R.drawable.profil2, R.drawable.post2));

        instagrams1.add(new Instagram("aisyah", "Diva Arfillah", "Mon soleil"
                ,R.drawable.profil3, R.drawable.post3));

        instagrams1.add(new Instagram("rehan", "Arni Rahman", "Peacefully"
                ,R.drawable.profil4, R.drawable.post4));

        return instagrams1;

    }
}
