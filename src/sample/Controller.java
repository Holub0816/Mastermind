package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Klasa zarzadzajaca interfejsem rozpoczecia gry.
 * @author Maciej Holub, holubmaciek@gmail.com
 */

public class Controller {
    private String name;

    @FXML
    private TextField nameTxtfield;

    @FXML
    private Button startBtn;

    @FXML
    private Button rulesBtn;


    public Controller() {
        this.name = name;
    }

    /**
     * Metoda wyswietlajaca instrukcje do gry Mastermind.
     * @param event Obiekt typu ActionEvent.
     */

    @FXML
    void showRules(ActionEvent event) {
        Stage stg = new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setTitle("Zasady");
        stg.setMinWidth(600);
        Label label = new Label();
        label.setText("Welcome to Mastermind\n" +
                "\n" +
                "The objective of the game is to correctly guess a secret code consisting\n" +
                "of a series of 4 colored pegs.\n" +
                "Each peg will be of one of 6 colors – Blue, Green, Orange, Purple, Red, and Yellow.\n" +
                "More than one peg in the secret code could be of the same color. You must guess the correct color\n" +
                "and order of the code.\n" +
                "You will have 10 chances to correctly guess the code. After every guess, the computer\n" +
                "will provide you feedback in the form of 0 to 4 colored pegs. A black peg indicates \n" +
                "that a peg in your guess is the correct color and is in the correct position.\n" +
                "A white peg indicates that a peg in your guess is of the correct color\n" +
                "but is not in the correct position.\n" +
                "NOTE: The order of the feedback pegs does not correspond to either the pegs in the code\n" +
                "or the pegs in your guess.\n" +
                "In other words, the color of the pegs is important, not the order they are in.");
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(label);
        BorderPane.setAlignment(label, Pos.CENTER);
        Scene scene2 = new Scene(borderPane, 500, 500);
        stg.setScene(scene2);
        stg.show();
    }

    /**
     * Metoda inicjalizujaca nowa scene, ktora jest docelowa scena gry.
     * @param event Obiekt typu ActionEvent.
     * @throws IOException
     */
    @FXML
    void startGame(ActionEvent event) throws IOException {
        name = String.valueOf(nameTxtfield.getText());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.controller.fxml"));
        Parent game_stage = fxmlLoader.load();
        Stage app_stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setTitle("MASTERMIND");
        Scene scene = new Scene(game_stage, 400, 600);
        app_stage.setScene(scene);
        GameController controller = fxmlLoader.<GameController>getController();
        controller.setUser(name);
        app_stage.setResizable(false);
        app_stage.show();

    }


}


