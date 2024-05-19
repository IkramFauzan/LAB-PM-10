package com.example.praktikum5;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Account> accounts =generateDummyAccount();

    private static ArrayList<Account> generateDummyAccount() {
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account("timnas.indonesia", "Timnas Indonesia", "Lorem Ipsum"
                ,R.drawable.indonesia, R.drawable.indonesia, null));
        accounts.add(new Account("realmadrid", "Real Madrid", "Lorem Ipsum"
                ,R.drawable.madrid, R.drawable.madrid, null));
        accounts.add(new Account("fcbarcelona", "FC Barcelona", "Lorem Ipsum"
                ,R.drawable.barca, R.drawable.barca, null));
        accounts.add(new Account("manutd", "Manchester United", "Lorem Ipsum"
                ,R.drawable.manutd, R.drawable.manutd, null));
        accounts.add(new Account("mancity", "Manchester City", "Lorem Ipsum"
                ,R.drawable.mancity, R.drawable.mancity, null));

        return accounts;

    }

}
