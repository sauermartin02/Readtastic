package readtastic.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.net.URI;

/**
 * Das ist die Controller-Klasse des Hauptmenüs, also von mainMenu.fxml.
 *
 * Sie verwaltet alle Interaktionen des Nutzers mit dem Hauptmenü.
 *
 * @author Martin Sauer
 */
public class MainMenuController {

    // Grafiken der kleinen Punkte, welche beim Überfahren mit der Maus jeweils ein- oder ausgeblendet werden.
    @FXML
    private ImageView readDotHover;

    @FXML
    private ImageView helpDotHover;


    @FXML
    void helpItemOnMouseClicked(MouseEvent event) {
        // Todo:
        // Zum Schluss, Anzeigen einer Anleitung zur Applikation nach Klicken auf "Hilfe"-Menüpunkt.
    }

    /**
     * Macht einen kleinen Punkt unterhalb der Hilfe-Grafik sichtbar, wenn mit Maus überfahren.
     *
     * @param event
     */
    @FXML
    void helpItemOnMouseEntered(MouseEvent event) {
        helpDotHover.setVisible(true);
    }

    /**
     * Blendet Punkt wieder aus, wenn Maus nicht mehr die Hilfe-Grafik überfährt.
     *
     * @param event
     */
    @FXML
    void helpItemOnMouseExited(MouseEvent event) {
        helpDotHover.setVisible(false);
    }


    /**
     * Öffnet im Internetbrowser die zugehörige GitHub-Seite zu dieser Anwendung, auf der man auch den Quellcode etc.
     * einsehen kann, wenn Nutzer auf das Logo klickt.
     *
     * @param event
     */
    @FXML
    void logoItemOnMouseClicked(MouseEvent event) {
        // Öffnen der GitHub Website im Standardbrowser.
        Desktop dt = Desktop.getDesktop();
        try {
            dt.browse(new URI("https://github.com/sauermartin02/Readtastic"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Wenn Nutzer auf den Menüpunkt "Lesen" klickt, dann wird bookshelf.fxml als Szene geladen und angezeigt.
     * Diese Szene stellt das Bücherregal dar, in welchem importierte Dokumente angezeigt werden und der Nutzer dann
     * mit diesen weiter interagieren kann.
     *
     * @param event
     */
    @FXML
    void readItemOnMouseClicked(MouseEvent event) {
        try {
            // Laden von bookshelf.fxml
            Parent root = FXMLLoader.load(getClass().getResource("/view/bookshelf.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Macht einen kleinen Punkt unterhalb der Lesen-Grafik sichtbar, wenn mit Maus überfahren.
     *
     * @param event
     */
    @FXML
    void readItemOnMouseEntered(MouseEvent event) {
        readDotHover.setVisible(true);
    }

    /**
     * Blendet Punkt wieder aus, wenn Maus nicht mehr die Lesen-Grafik überfährt.
     *
     * @param event
     */
    @FXML
    void readItemOnMouseExited(MouseEvent event) {
        readDotHover.setVisible(false);
    }

}