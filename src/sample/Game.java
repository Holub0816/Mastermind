package sample;

import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa zapewniająca funkcjonalność gryMastermind.
 * @author Maciej Holub, holubmaciek@gmail.com
 */

public class Game {

    final private char[] kolory;
    final private int liczbaPol;
    private char[] konfiguracja;
    private int tura;
    final private int maxTur;
    private boolean wygrana = false;
    private int miejsca;
    private int pokolorowane;

    /**
     * Kontroler umozliwiajacy przypisanie wartosci parametrow do odpowiednich pol klasy.
     * @param liczbaPol Liczba kolorowych okregow generowanych przez gre, ktorych polozenie ma zgadnac uzytkownik.
     * @param maxTur Maksymalna liczba ruchow, ktora uzytkownik moze wykonac.
     * @param kolory Tablica kolorow uzywanych w programie.
     */

    public Game(int liczbaPol, int maxTur, char[] kolory) {
        this.liczbaPol = liczbaPol;
        this.maxTur = maxTur;
        this.kolory = kolory;
    }

    /**
     * Metoda umozliwiajaca wygenerowanie przez program konfiguracji kolorow, ktora uzytkownik bedzie musial odgadnac.
     * @param liczbaPol Liczba kolorowych okregow generowanych przez gre, ktorych polozenie ma zgadnac uzytkownik.
     * @param kolory Tablica kolorow uzywanych w programie.
     * @return Losowa konfiguracja kolorow w formie tablicy znakow.
     */

    public char[] wygenerujKonfiguracje(int liczbaPol, char[] kolory) {
        Random r = new Random();
        konfiguracja = new char[liczbaPol];
        for (int i = 0; i < liczbaPol; i++) {

            konfiguracja[i] = kolory[(int) Math.floor(r.nextInt(kolory.length))];
        }
        return konfiguracja;
    }


    /**
     * Metoda zwracajaca liczby calkowite oznaczajace ilosc poprawnie umieszczonych okregow w probie oraz ilosc poprawnych kolorow w probie umieszczonych na zlym miejscu.
     * @param tab Tablica znakow generowana przez uzytkownika w celu odgadniecia poprawnej konfiguracji.
     * @return Tablica liczb calkowitych.
     */
    public int[] poprawne(char[] tab) {

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
        int[] odpowiedz = new int[]{counter1, counter2};   // indeks 0 - poprawne miejsca, indeks 1 - poprawne kolory
        return odpowiedz;
    }

    /**
     * Metoda przypisujaca do zmiennych instancji miejsca oraz pokolorowane wartosci z wczesniej wygenerowanej metody poprawne.
     * @param aktualnyRuch Tablica znakow generowana przez uzytkownika w celu odgadniecia poprawnej konfiguracji.
     */

    public void zgaduj(char[] aktualnyRuch) {
        miejsca = poprawne(aktualnyRuch)[0];
        pokolorowane = poprawne(aktualnyRuch)[1];
        tura = tura + 1;

        if (miejsca == liczbaPol) {
            wygrana = true;
        }

    }

    /**
     * Metoda zwracajaca aktualna ture.
     * @return Aktualna tura.
     */
    public int getTura() {
        return tura;
    }

    /**
     * Metoda zwracajaca liczbe pol.
     * @return Liczba pol.
     */
    public int getLiczbaPol() {
        return liczbaPol;
    }

    /**
     * Metoda zwracajaca tablice kolorow.
     * @return Tablica kolorow.
     */
    public char[] getKolory() {
        return kolory;
    }

    /**
     * Metoda zwracajaca poprawna, wygenerowana przez program konfiguracje okregow.
     * @return Poprawna konfiguracja.
     */
    public char[] getKonfiguracja() {
        return konfiguracja;
    }

    /**
     * Metoda informujaca o tym czy uzytkownik wygral gre czy nie.
     * @return Obiekt typu boolean przechowujacy informacje czy uzytkownik wygral gre czy nie.
     */
    public boolean czyWygrana() {
        return wygrana;
    }

    /**
     * Metoda zwracajaca ilosc poprawnie umiejscowionych okregow.
     * @return Poprawnie odgadniete okregi.
     */
    public int getMiejsca() {
        return miejsca;
    }


    /**
     * Metoda zwracajaca ilosc kolorow w probie zgadniecia zgodnych z konfiguracja.
     * @return Poprawne kolory w probie odgadniecia konfiguracji.
     */
    public int getPokolorowane() {
        return pokolorowane;
    }

    /**
     * Metoda zwracajaca ilosc tur, w ktorych uzytkownik musi wygrac gre.
     * @return Maksymalna liczba tur.
     */
    public int getMaxTur() {
        return maxTur;
    }
}