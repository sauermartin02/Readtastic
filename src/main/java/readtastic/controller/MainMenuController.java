package readtastic.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Das ist die Controller-Klasse des Hauptmenüs, also von mainMenu.fxml
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
    private ImageView editDotHover;

    @FXML
    private ImageView helpDotHover;


    @FXML
    void editItemOnMouseClicked(MouseEvent event) {

    }

    /**
     * Macht einen kleinen Punkt unterhalb der Bearbeiten-Grafik sichtbar, wenn mit Maus überfahren.
     *
     * @param event
     */
    @FXML
    void editItemOnMouseEntered(MouseEvent event) {
        editDotHover.setVisible(true);
    }

    /**
     * Blendet Punkt wieder aus, wenn Maus nicht mehr die Bearbeiten-Grafik überfährt.
     *
     * @param event
     */
    @FXML
    void editItemOnMouseExited(MouseEvent event) {
        editDotHover.setVisible(false);
    }


    @FXML
    void helpItemOnMouseClicked(MouseEvent event) {

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


    @FXML
    void readItemOnMouseClicked(MouseEvent event) {

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

}
