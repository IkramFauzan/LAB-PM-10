package com.example.tugaspraktikum5;

import java.util.ArrayList;

// Sumber data awal yang menyediakan daftar objek Instagram dengan informasi
// yang telah ditentukan secara statis

public class DataSource {

    public static ArrayList<Instagram> instagrams =generateDummyInstagram();

    private static ArrayList<Instagram> generateDummyInstagram() {
        ArrayList<Instagram> instagrams1 = new ArrayList<>();
        instagrams1.add(new Instagram("Ciyo Cimol", "Ciyo", "Life is too short to wear boring clothes"
                ,R.drawable.ciyo, R.drawable.ciyo));

        instagrams1.add(new Instagram("Jje eaJ", "Jje", "Don’t wait for the perfect moment, take the moment and make it perfect"
                ,R.drawable.jje, R.drawable.jje));

        instagrams1.add(new Instagram("Dion Denimalz", "Dion", "Lost in wanderlust"
                ,R.drawable.don, R.drawable.don));

        instagrams1.add(new Instagram("Kke Brian", "Kke", "I’m too glam to give a damn"
                ,R.drawable.kke, R.drawable.kke));

        return instagrams1;

    }

}
