package sample;

import dialogs.Dialogs;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class GameController {

    /**
     * Klasa zarzadzajaca interfejsem (ekranem) gry Mastermind.
     */

    private final Controller controller = new Controller();
    private String imie;
    private int liczbaPol = 4;
    private int maxTur = 10;
    private char[] litery = new char[]{'R', 'B', 'G', 'Y', 'P', 'O'};
    private javafx.scene.paint.Paint[] kolory = new javafx.scene.paint.Paint[]{javafx.scene.paint.Color.RED, javafx.scene.paint.Color.BLUE,
            javafx.scene.paint.Color.GREEN, javafx.scene.paint.Color.YELLOW, javafx.scene.paint.Color.PINK, Color.ORANGE};
    private final Game game = new Game(liczbaPol, maxTur, litery);
    private String odpowiedz = "";
    private char[] konfiguracja;


    @FXML
    private VBox historyPanel;

    @FXML
    private FlowPane pinPanel;

    @FXML
    private FlowPane currentPanel;

    @FXML
    private Button zastosujBtn;

    @FXML
    private Button wycofajBtn;

    @FXML
    private Circle circleRed;

    @FXML
    private Circle circleYellow;

    @FXML
    private Circle circleBlue;

    @FXML
    private Circle circlePink;

    @FXML
    private Circle circleGreen;

    @FXML
    private Circle circleOrange;

    @FXML
    private Button restartBtn;

    /**
     * Metoda ustawiajaca pole imie zawierajace informacje o imieniu uzytkownika.
     * @param imie Imie uzytkownika.
     */

    public void setUser(String imie) {
        this.imie = imie;
    }


    /**
     * Metoda inicjalizujaca zadania dla obiektow i metod.
     */
    public void initialize() {
        circleRed.setFill(javafx.scene.paint.Color.RED);
        circleBlue.setFill(javafx.scene.paint.Color.BLUE);
        circleGreen.setFill(javafx.scene.paint.Color.GREEN);
        circleYellow.setFill(javafx.scene.paint.Color.YELLOW);
        circlePink.setFill(javafx.scene.paint.Color.PINK);
        circleOrange.setFill(javafx.scene.paint.Color.ORANGE);

        circleRed.setOnMouseClicked(new ColorClicked("R"));
        circleBlue.setOnMouseClicked(new ColorClicked("B"));
        circleGreen.setOnMouseClicked(new ColorClicked("G"));
        circleYellow.setOnMouseClicked(new ColorClicked("Y"));
        circlePink.setOnMouseClicked(new ColorClicked("P"));
        circleOrange.setOnMouseClicked(new ColorClicked("O"));


        konfiguracja = game.wygenerujKonfiguracje(liczbaPol, litery);


    }


    /**
     * Metoda wycofujaca probe odgadniecia konfiguracji przez uzytkownika uzytkownika
     * @param event Obiekt typu ActionEvent.
     */
    @FXML
    void wycofaj(ActionEvent event) {
        odpowiedz = "";
        currentPanel.getChildren().clear();

    }

    /**
     * Metoda odpowiedzialna za pojedyncza probe odgadniecia konfiguracji przez uzytkownika i dodajaca ta probe do historyPanel.
     * @param event Obiekt typu ActionEvent.
     */
    @FXML
    void zastosuj(ActionEvent event) {

        char[] tablica = new char[odpowiedz.length()];
        for (int i = 0; i < odpowiedz.length(); i++) {
            tablica[i] = odpowiedz.charAt(i);
        }


        String[] znaki = new String[tablica.length];
        for (int i = 0; i < tablica.length; i++) {
            String s = Character.toString(tablica[i]);
            znaki[i] = s;

        }
        if (game.getTura() < maxTur && !game.czyWygrana() && odpowiedz.length() == liczbaPol) {

            game.poprawne(tablica);
            game.zgaduj(tablica);
            pokażProbe(odpowiedz, game.getPokolorowane(), game.getMiejsca());
            odpowiedz = "";
            currentPanel.getChildren().clear();
            wygrana();
        } else {

            wygrana();


        }


    }


    /**
     * Metoda dodajaca probe odgadniecia poprawnej konfiguracji okregow do panelu historii prob.
     * @param odpowiedz Konfiguracja kolorow wprowadzona niejawnie przez uzytkownika w formie obiektu typu String.
     * @param poprawnyKolor Ilosc poprawnie umiesczonych okregow.
     * @param poprawneMiejsce Ilosc kolorow zgadzajacych sie z konfiguracja, lecz znajdujacych sie na zlych miejscach.
     */
    public void pokażProbe(String odpowiedz, int poprawnyKolor, int poprawneMiejsce) {


        char[] literki = new char[odpowiedz.length()];

        String wyrazenie = "";

        for (int i = 0; i < litery.length; i++) {
            wyrazenie = wyrazenie + litery[i];
        }

        char[] literyCopy = wyrazenie.toCharArray();

        for (int i = 0; i < odpowiedz.length(); i++) {
            literki[i] = odpowiedz.charAt(i);
        }

        Circle[] circles = new Circle[odpowiedz.length()];

        for (int i = 0; i < literki.length; i++) {
            for (int j = 0; j < kolory.length; j++) {
                if (literki[i] == literyCopy[j]) {

                    circles[i] = new Circle();
                    circles[i].setRadius(12);
                    circles[i].setFill(kolory[j]);
                    break;

                }
            }
        }


        Circle[] znaneMiejsca = new Circle[0];
        if (poprawneMiejsce != 0) {
            znaneMiejsca = new Circle[poprawneMiejsce];
            for (int i = 0; i < poprawneMiejsce; i++) {
                znaneMiejsca[i] = new Circle();
                znaneMiejsca[i].setRadius(8);
                znaneMiejsca[i].setFill(Color.BLACK);
            }
        }
        Circle[] znaneKolory = new Circle[0];
        if (poprawnyKolor != 0) {
            znaneKolory = new Circle[poprawnyKolor];
            for (int i = 0; i < poprawnyKolor; i++) {
                znaneKolory[i] = new Circle();
                znaneKolory[i].setRadius(8);
                znaneKolory[i].setFill(Color.WHITE);
            }
        }


        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(5);
        flowPane.setVgap(5);
        flowPane.getChildren().addAll(circles);
        flowPane.getChildren().addAll(znaneMiejsca);
        flowPane.getChildren().addAll(znaneKolory);
        historyPanel.getChildren().add(flowPane);


    }


    /**
     * Metoda wyswielajaca poprawna konfiguracje okregow.
     * @param konfiguracja Tablica znakow kodujaca poprawne ulozenie okregow.
     * @return Okregi o odpowiednich kolorach.
     */
    public Circle[] pokazKonfiguracje(char[] konfiguracja) {
        Circle[] circles = new Circle[konfiguracja.length];

        for (int i = 0; i < konfiguracja.length; i++) {
            for (int j = 0; j < kolory.length; j++) {
                if (konfiguracja[i] == litery[j]) {

                    circles[i] = new Circle();
                    circles[i].setRadius(12);
                    circles[i].setFill(kolory[j]);
                    break;

                }
            }
        }

        return circles;

    }

    /**
     * Metoda wyswietlajaca odpowiednie teksty oraz poprawna konfiguracje jezeli uzytkownik wygra gre.
     */
    public void wygrana() {
        if (game.getTura() < maxTur) {
            if (game.czyWygrana()) {
                Text text = new Text("Gratulację " + imie + "!" + "\n" + "Osiągnięcie wygranej zajęło Ci " + game.getTura() + " tur.");
                historyPanel.getChildren().add(text);
            } else {
                Text text = new Text("Zostało Ci " + (maxTur - game.getTura()) + " szans.");
                historyPanel.getChildren().add(text);
            }
        } else {
            if (game.czyWygrana()) {
                Text text = new Text("Gratulację " + imie + "! Osiągnięcie wygranej zajęło Ci " + game.getTura() + " tur.");
                historyPanel.getChildren().add(text);
            } else {
                Text text = new Text("Przegrałeś " + imie + "! Spróbuj jeszcze raz." + "\n\n\n");
                historyPanel.getChildren().add(text);
                Text text2 = new Text("Poprawna konfiguracja:");
                historyPanel.getChildren().add(text2);
                text2.setLineSpacing(100);
                Circle[] szukanaKonfiguracja;
                szukanaKonfiguracja = pokazKonfiguracje(konfiguracja);
                FlowPane f = new FlowPane();
                f.getChildren().addAll(szukanaKonfiguracja);
                f.setHgap(5);
                historyPanel.getChildren().add(f);

            }
        }
    }

    /**
     * Metoda umozliwiajaca zamkniecie okna aplikacji.
     */
    public void closeApplication() {
        Optional<ButtonType> result = Dialogs.dialogExitApplication();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    /**
     * Metoda wyswietlajaca informacje o programie.
     */
    public void about() {
        Dialogs.dialogAboutApplication();
    }


    /**
     * Metoda na nowo inicjalizujaca okno aplikacji w przypadku, gdy uzytkownik chce zagrac ponownie.
     * @param event
     * @throws IOException
     */
    @FXML
    void restart(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.controller.fxml"));
        Parent game_stage = fxmlLoader.load();
        Stage app_stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setTitle("MASTERMIND");
        Scene scene = new Scene(game_stage, 400, 600);
        app_stage.setScene(scene);
        GameController controller = fxmlLoader.<GameController>getController();
        controller.setUser(imie);
        app_stage.setResizable(false);
        app_stage.show();

    }


    // Klasa wewnętrzna ColorClicked implementująca interfejs EventHandler, która ma
    // dostęp do wszystkich atrybutów czy metod klasy zewnętrznej, w której została zdefiniowana.

    /**
     * Klasa wewnetrzna implementujaca interfejs EventHandler ktorej glowna i jedyna funkcjonalnoscia jest zamiana kolorow z formy odpowiednich znakow na kolorowe okregi.
     * @author Maciej Holub, maciekholub@gmail.com
     */
    class ColorClicked implements EventHandler<MouseEvent> {


        private String litera;


        /**
         * Kotroler inicjalizujacy pole litera.
         * @param litera Obiekt typu char.
         */
        public ColorClicked(String litera) {
            this.litera = litera;

        }

        public void setLitera(String litera) {
            this.litera = litera;
        }

        public String getLitera() {
            return litera;
        }

        /**
         * Metoda obslugujaca wydarzenie, w tym przypadku klikniecie na odpowiedni okrag o okreslonym kolorze przez uzytkownika aplikacji.
         * @param mouseEvent Konkretne zdarzenie (w tym przypadku klikniecie myszy w wybranym miejscu) dla ktorego wykonywana jest metoda.
         */
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (odpowiedz.length() < liczbaPol) {
                odpowiedz = odpowiedz + litera;

                char[] literki = new char[odpowiedz.length()];

                String wyrazenie = "";

                for (int i = 0; i < litery.length; i++) {
                    wyrazenie = wyrazenie + litery[i];
                }

                char[] literyCopy = wyrazenie.toCharArray();

                for (int i = 0; i < odpowiedz.length(); i++) {
                    literki[i] = odpowiedz.charAt(i);
                }

                Circle[] circles = new Circle[odpowiedz.length()];

                for (int i = 0; i < literki.length; i++) {
                    for (int j = 0; j < kolory.length; j++) {
                        if (literki[i] == literyCopy[j]) {

                            circles[i] = new Circle();
                            circles[i].setRadius(10);
                            circles[i].setFill(kolory[j]);
                            break;

                        }
                    }
                }

                currentPanel.getChildren().clear();
                currentPanel.setHgap(5);
                currentPanel.getChildren().addAll(circles);


            }

        }
    }
}
