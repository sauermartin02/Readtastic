package readtastic.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Das ist die Controller-Klasse der Hilfeseite.
 *
 * Sie verwaltet alle Interaktionen des Nutzers mit der grafischen Oberfläche von helpPage.fxml
 *
 * @author Martin Sauer
 */
public class HelpPageController {

    /**
     * Diese Methode wird ausgeführt, wenn Nutzer auf Zurück-Button klickt.
     *
     * Es findet ein Szenewechsel statt und das Hauptmenü wird wieder angezeigt.
     *
     * @param event
     */
    @FXML
    void backButtonOnMouseClicked(MouseEvent event) {
        try {
            // Laden von mainMenu.fxml
            Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
