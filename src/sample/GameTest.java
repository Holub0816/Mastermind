package sample;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {


    Game game2;
    private char[] kolory = new char[]{'R', 'B', 'G', 'Y', 'P', 'O'};
    Game game = new Game(4,10,kolory);






    @BeforeAll
    static void initialize() {
        System.out.println("Test rozpoczęty");
    }

    @BeforeEach
    void before() {
        System.out.println("Następny test");
    }





    @Test
    @DisplayName("Dwa kolory dobre, dwa na innych miejscach.")
    public void dwaKoloryNaInnychMiejscach() {
        char[] konfiguracja = new char[]{'R','Y','G','O'};
        char[] tab = new char[]{'R','Y','O','G'};

        int counter2 = 0;
        int j = 0;
        ArrayList<Character> pozostaleKolory = new ArrayList<Character>();

        while (j < tab.length) {
            pozostaleKolory.add(konfiguracja[j]);
            j++;
        }

        for (int k = 0; k < tab.length; k++) {
            for (int g = 0; g < pozostaleKolory.size(); g++) {
                if (tab[k] == pozostaleKolory.get(g)) {
                    counter2++;
                    pozostaleKolory.remove(g);
                }
            }
        }

        int counter1 = 0;
        int i = 0;
        while (i < tab.length) {
            if (tab[i] == konfiguracja[i]) {
                counter1 = counter1 + 1;
                counter2 = counter2 - 1;
            }
            i++;
        }
        int[] odpowiedz = new int[]{counter1, counter2};
        int[] wynik = new int[]{2,2};
        assertArrayEquals(wynik,odpowiedz);
    }



    @Test
    @DisplayName("Wszystkie kolory na dobrych miejscach.")
    public void wszystkieKoloryNaDobrychMiejscach() {
        char[] konfiguracja = new char[]{'R','Y','G','O'};
        char[] tab = new char[]{'R','Y','G','O'};

        int counter2 = 0;
        int j = 0;
        ArrayList<Character> pozostaleKolory = new ArrayList<Character>();

        while (j < tab.length) {
            pozostaleKolory.add(konfiguracja[j]);
            j++;
        }

        for (int k = 0; k < tab.length; k++) {
            for (int g = 0; g < pozostaleKolory.size(); g++) {
                if (tab[k] == pozostaleKolory.get(g)) {
                    counter2++;
                    pozostaleKolory.remove(g);
                }
            }
        }

        int counter1 = 0;
        int i = 0;
        while (i < tab.length) {
            if (tab[i] == konfiguracja[i]) {
                counter1 = counter1 + 1;
                counter2 = counter2 - 1;
            }
            i++;
        }
        int[] odpowiedz = new int[]{counter1, counter2};
        int[] wynik = new int[]{4,0};
        assertArrayEquals(wynik,odpowiedz);
    }



    @Test
    @DisplayName("Wszysztkie kolory dobre, ale wszystkie na złych miejscach")
    public void wszystkieKoloryNaInnychMiejscach() {
        char[] konfiguracja = new char[]{'R','Y','G','O'};
        char[] tab = new char[]{'Y','G','O','R'};

        int counter2 = 0;
        int j = 0;
        ArrayList<Character> pozostaleKolory = new ArrayList<Character>();

        while (j < tab.length) {
            pozostaleKolory.add(konfiguracja[j]);
            j++;
        }

        for (int k = 0; k < tab.length; k++) {
            for (int g = 0; g < pozostaleKolory.size(); g++) {
                if (tab[k] == pozostaleKolory.get(g)) {
                    counter2++;
                    pozostaleKolory.remove(g);
                }
            }
        }

        int counter1 = 0;
        int i = 0;
        while (i < tab.length) {
            if (tab[i] == konfiguracja[i]) {
                counter1 = counter1 + 1;
                counter2 = counter2 - 1;
            }
            i++;
        }
        int[] odpowiedz = new int[]{counter1, counter2};
        int[] wynik = new int[]{0,4};
        assertArrayEquals(wynik,odpowiedz);
    }

    @Test
    @DisplayName("Jeden dobry kolor ale na złym miejscu")
    public void jedenDobryKolorNaZlymMiejscu() {
        char[] konfiguracja = new char[]{'R','Y','G','O'};
        char[] tab = new char[]{'Y','P','B','B'};

        int counter2 = 0;
        int j = 0;
        ArrayList<Character> pozostaleKolory = new ArrayList<Character>();

        while (j < tab.length) {
            pozostaleKolory.add(konfiguracja[j]);
            j++;
        }

        for (int k = 0; k < tab.length; k++) {
            for (int g = 0; g < pozostaleKolory.size(); g++) {
                if (tab[k] == pozostaleKolory.get(g)) {
                    counter2++;
                    pozostaleKolory.remove(g);
                }
            }
        }

        int counter1 = 0;
        int i = 0;
        while (i < tab.length) {
            if (tab[i] == konfiguracja[i]) {
                counter1 = counter1 + 1;
                counter2 = counter2 - 1;
            }
            i++;
        }
        int[] odpowiedz = new int[]{counter1, counter2};
        int[] wynik = new int[]{0,1};
        assertArrayEquals(wynik,odpowiedz);
    }


    @Test
    @DisplayName("Jeden dobry kolor na dobrym miejscu")
    public void jedenDobryKolorNaDobrymMiejscu() {
        char[] konfiguracja = new char[]{'R','Y','G','O'};
        char[] tab = new char[]{'B','P','G','P'};

        int counter2 = 0;
        int j = 0;
        ArrayList<Character> pozostaleKolory = new ArrayList<Character>();

        while (j < tab.length) {
            pozostaleKolory.add(konfiguracja[j]);
            j++;
        }

        for (int k = 0; k < tab.length; k++) {
            for (int g = 0; g < pozostaleKolory.size(); g++) {
                if (tab[k] == pozostaleKolory.get(g)) {
                    counter2++;
                    pozostaleKolory.remove(g);
                }
            }
        }

        int counter1 = 0;
        int i = 0;
        while (i < tab.length) {
            if (tab[i] == konfiguracja[i]) {
                counter1 = counter1 + 1;
                counter2 = counter2 - 1;
            }
            i++;
        }
        int[] odpowiedz = new int[]{counter1, counter2};
        int[] wynik = new int[]{1,0};
        assertArrayEquals(wynik,odpowiedz);
    }



    @Test
    @DisplayName("Żaden kolor nie jest dobry.")
    public void wszystkieZleKolory() {
        char[] konfiguracja = new char[]{'R','Y','G','O'};
        char[] tab = new char[]{'P','P','B','B'};

        int counter2 = 0;
        int j = 0;
        ArrayList<Character> pozostaleKolory = new ArrayList<Character>();

        while (j < tab.length) {
            pozostaleKolory.add(konfiguracja[j]);
            j++;
        }

        for (int k = 0; k < tab.length; k++) {
            for (int g = 0; g < pozostaleKolory.size(); g++) {
                if (tab[k] == pozostaleKolory.get(g)) {
                    counter2++;
                    pozostaleKolory.remove(g);
                }
            }
        }

        int counter1 = 0;
        int i = 0;
        while (i < tab.length) {
            if (tab[i] == konfiguracja[i]) {
                counter1 = counter1 + 1;
                counter2 = counter2 - 1;
            }
            i++;
        }
        int[] odpowiedz = new int[]{counter1, counter2};
        int[] wynik = new int[]{0,0};
        assertArrayEquals(wynik,odpowiedz);
    }


    @Test
    @DisplayName("Czy obiekt game jest instancją klasy Game.")
    void isTrue(){

        game2 = new Game(4,10,kolory);
        Assertions.assertTrue(game instanceof Game);
    }




    @Test
    @DisplayName("Czy zwracana liczba pól równa się tej prawdziwej.")
    public void getLiczbaPol() {
        assertEquals(4,game.getLiczbaPol());
    }

    @Test
    @DisplayName("Czy zwracana tablica kolorów równa się tej prawdziwej.")
    public void getKolory() {
        assertArrayEquals(kolory,game.getKolory());
    }

    @Test
    @DisplayName("Czy zwracana maksymalna liczba tur równa się tej prawdziwej.")
    public void getMaxTur() {
        assertEquals(10,game.getMaxTur());
    }




}