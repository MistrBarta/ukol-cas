
package com.company;


import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Vyberte aplikaci");
        System.out.println("-----------------------");
        System.out.println("1. Zjištění přestuopnéo roku podle zadaného roku od uživatele");
        System.out.println("2. Zjištění přestuopnéo roku podle aktuálního data");
        System.out.println("3. Vlastní formát data a času");
        System.out.println("4. Zbývající čas do určitého data");

        int num = sc.nextInt();

        switch (num) {
            case 1 -> {
                System.out.println("zadejte rok");
                int rok = sc.nextInt();

                Year firstYear = Year.of(rok);

                if (firstYear.isLeap() == true) {
                    System.out.println("rok je přestupný");
                } else {
                    System.out.println("rok není přestupný");
                }
            }

            case 2 -> {

                int year = Year.now().getValue();

                boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

                if (isLeap) {
                    System.out.println(year + " je prestupny");
                } else {
                    int nextYear = year;
                    while (true) {
                        nextYear++;
                        if ((nextYear % 4 == 0 && nextYear % 100 != 0) || (nextYear % 400 == 0)) {
                            System.out.println("Rok " + year + " není přestupný. Další přestupný rok bude v roce " + nextYear);
                            break;
                        }
                    }
                }

            }
            case 3 -> {
                long casMillis = System.currentTimeMillis();

                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

                // Převedení systémového času na formátovaný řetězec
                String formatovanyCas = sdf.format(new Date(casMillis));
                System.out.println("Aktuální čas je " + formatovanyCas);
            }
            case 4 -> {
                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                String formattedDate = myDateObj.format(myFormatObj);
                System.out.println("Aktuální čas je: " + formattedDate);

                Calendar aktualniCas = Calendar.getInstance();

                System.out.print("Zadejte den (1-31): ");
                int den = sc.nextInt();
                System.out.print("Zadejte měsíc (1-12): ");
                int mesic = sc.nextInt() - 1; // Měsíce jsou indexované od 0
                System.out.print("Zadejte rok: ");
                int rok = sc.nextInt();

                Calendar zadaneDatum = Calendar.getInstance();
                zadaneDatum.set(rok, mesic, den);

                long casRozdil = zadaneDatum.getTimeInMillis() - aktualniCas.getTimeInMillis();

                if (casRozdil <= 0) {
                    System.out.println("Zadané datum je v minulosti.");
                } else {

                    long dny = casRozdil / (1000 * 60 * 60 * 24);
                    long hodiny = (casRozdil % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                    long minuty = (casRozdil % (1000 * 60 * 60)) / (1000 * 60);
                    long sekundy = (casRozdil % (1000 * 60)) / 1000;
                    long roky = dny / 365; // Předpoklad, že každý rok má 365 dní

                    System.out.println("Zbývá " + roky + " let, " + dny % 366 + " dní, " + hodiny + " hodin, " + minuty + " minut a " + sekundy + " sekund do zadaného data.");
                }
            }


        }


    }
}
