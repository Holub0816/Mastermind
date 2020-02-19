package dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Klasa przechowujaca metody informujace oraz umozliwiajace interakcje uzytkownika z interfejsem.
 * @author Maciej Holub, holubmaciek@gmail.com
 */

public class Dialogs {


    /**
     * Metoda inizjalizujaca obiekt typu Alert wyswietlajacy informacje o programie.
     */
    public static void dialogAboutApplication() {
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle("About application");
        informationAlert.setHeaderText("Mastermind, version 1.0");
        informationAlert.setContentText("Autor: Maciej Hołub, Wrocław University of Technology");
        informationAlert.showAndWait();
    }

    /**
     * Metoda wyswietlajaca okno, ktore pyta uzytkownika czy na pewno chce wyjsc z programu.
     * @return Obiekt typu Optional.
     */
    public static Optional<ButtonType> dialogExitApplication() {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit");
        exitAlert.setHeaderText("Are you sure you want to close the program?");
        Optional<ButtonType> result = exitAlert.showAndWait();
        return result;
    }
}
