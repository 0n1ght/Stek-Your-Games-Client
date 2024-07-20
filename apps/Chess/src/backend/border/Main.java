package backend.border;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int[][] tab = stworzSzachownice();

        int[][] tab1 = postawSkoczkaNaSzachownicy(tab);
        int i = 0;
        while (i >= 0) {
            wyswietlStanSzachownicy(tab1);
            obsluzRuchSkoczka(tab1);
            i++;
        }
    }

    private static int[][] stworzSzachownice() {
        int[][] arr = new int[8][8];
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr.length; col++) {
                arr[row][col] = 0;
            }
        }
        return arr;
    }

    private static int[][] postawSkoczkaNaSzachownicy(int[][] tab) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ustaw skoczka na szachownicy podając dwie współrzędne");
        System.out.println("Współrzędna pozioma:");
        int x = scanner.nextInt();
        System.out.println("Współrzędna pionowa:");
        int y = scanner.nextInt();
        tab[x - 1][y - 1] = 1;
        return tab;
    }

    private static void obsluzRuchSkoczka(int[][] tab) {
        int[] arr = sprawdzPierwotnePolozenieSkoczka(tab);
        int[][] tab1 = przemiescWieze(arr, tab);
        wyzerujStarePolozenie(arr, tab1);
//        return tab;
    }

    private static void wyzerujStarePolozenie(int[] arr, int[][] tab) {
        tab[arr[0]][arr[1]] = 0;
//        return tab;
    }

    private static boolean skontrolujCzyRuchPoprawny(int[] arr, int x, int y) {
        boolean czyPoprawny = true;

        int[] arr1 = skorygujRozniceGdyUjemna(arr, x, y);
//        if ((arr1[0] != 1 || arr1[1] != 2) && (arr1[0] != 2 || arr1[1] != 1)) { /** Pierwszy pomysł, działa raczej na pewno*/
        if ((arr1[0] + arr1[1] != 3) || (arr1[0] == 0)) { //Za poziom/
            System.out.println("Niepoprawny");            /**Pomysł drugi, nie jestem go pewien ale raczej okej :D */
            czyPoprawny = false;
        } else if (arr1[1] == 0) { //Za pion
            System.out.println("Niepoprawny");
            czyPoprawny = false;
        }
        return czyPoprawny;
    }

    private static int[] skorygujRozniceGdyUjemna(int[] arr, int x, int y) {
        int a = (arr[0] + 1);
        int b = (arr[1] + 1);
        System.out.println(a - x);
        System.out.println(b - y);
        int c = a - x;
        int d = b - y;


        if (c < 0) {
            c *= -1;
        }
        if (d < 0) {
            d *= -1;
        }
        System.out.println(c);
        System.out.println(d);
        return new int[]{c, d};
    }

    private static int[][] przemiescWieze(int[] arr, int[][] tab) {
        System.out.println("\nWykonaj ruch podając dwie współrzędne:");
        Scanner scanner = new Scanner(System.in);
        int x;
        int y;
        do {
            System.out.println("współrzędna pozioma:");
            x = scanner.nextInt();
            System.out.println("współrzędna pionowa:");
            y = scanner.nextInt();
        } while (!skontrolujCzyRuchPoprawny(arr, x, y));

        tab[x - 1][y - 1] = tab[arr[0]][arr[1]];
        return tab;
    }

    private static int[] sprawdzPierwotnePolozenieSkoczka(int[][] tab) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j] != 0) {
                    a = i;
                    b = j;
                }
            }
        }
        return new int[]{a, b};
    }

    private static void wyswietlStanSzachownicy(int[][] wieza) {
        int a = 0;
        int b = 0;
        System.out.print("  ");
        for (int i = 0; i < wieza.length; i++) {
            a++;
            System.out.printf("%4d", a);
        }
        for (int[] wewnetrznaTab : wieza) {
            b++;
            System.out.printf("\n%2d", b);
            for (int s : wewnetrznaTab) {
                System.out.printf("%4s", s);
            }
        }
    }

}
