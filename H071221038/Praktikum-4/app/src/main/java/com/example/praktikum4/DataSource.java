package com.example.praktikum4;

import android.net.Uri;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<Account> accounts = generateDummyAccounts();

    private static ArrayList<Account> generateDummyAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account("USS Feeds",
                "ussfeeds",
                R.drawable.ussfeed,
                R.drawable.ussfeed_image,
                "After winning 1-0 in the first leg, Indonesia secured another win at My Dinh Stadium in Vietnam with a 3-0 score, thanks to a header from Jay Idzes, a brilliant performance by Ragnar Oratmangoen, who made his debut tonight, and last minute goal from Sananta.\n" +
                        "\n" +
                        "This result extends Indonesia’s winning streak over Vietnam to 3 consecutive victories. Furthermore, the Indonesian national team will continue their World Cup 2026 qualifying match in June. Let’s go boys! \uD83C\uDDEE\uD83C\uDDE9\uD83D\uDCA5❤\uFE0F\u200D\uD83D\uDD25\n" +
                        "\n" +
                        "[\uD83D\uDCF8via @timnas.indonesia]\n" +
                        "-\n" +
                        "#USSFeed",
                null));
        accounts.add(new Account("VALORANT",
                "valorant",
                R.drawable.valo_logo,
                R.drawable.valo_image,
                "a cluttered desk = a cluttered mind? yea, actually. that kinda tracks.",
                null));
        accounts.add(new Account("Real Madrid",
                "realmadrid",
                R.drawable.realmadrid,
                R.drawable.realmadrid_image,
                "Muchas felicidades por estos 122 años de historia, familia madridista! ¡Hala Madrid!",
                null));
        accounts.add(new Account("Manchester United",
                "manchesterunited",
                R.drawable.mu,
                R.drawable.mu_image,
                "Unrivalled commitment ❤\uFE0F\n" +
                        "\n" +
                        "#MUFC #ManUtd #UnitedForTheFans",
                null));
        accounts.add(new Account("Cristiano Ronaldo",
                "cristiano",
                R.drawable.ronaldo_image,
                R.drawable.ronaldo_story,
                "Vamos",
                null));
        accounts.add(new Account("Lionel Messi",
                "leomessi",
                R.drawable.messi,
                R.drawable.messi_image,
                "1 año de la locura más hermosa de mi carrera…\n" +
                        "\n" +
                        "Recuerdos inolvidables que quedarán para toda la vida.\n" +
                        "\n" +
                        "Feliz aniversario para todos!!! \uD83C\uDDE6\uD83C\uDDF7\uD83C\uDFC6\uD83D\uDE4C",
                null));

        return accounts;
    }
}
