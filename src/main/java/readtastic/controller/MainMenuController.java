package readtastic.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import readtastic.model.EpubToPdfConversion;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;

/**
 * Das ist die Controller-Klasse des Hauptmenüs, also von mainMenu.fxml.
 *
 * Sie verwaltet alle Interaktionen des Nutzers mit der grafischen Benutzeroberfläche des Hauptmenüs.
 *
 * @author Martin Sauer
 */
public class MainMenuController {

    // Grafiken der kleinen Punkte, welche beim Überfahren mit der Maus jeweils ein- oder ausgeblendet werden
    @FXML
    private ImageView readDotHover;

    @FXML
    private ImageView helpDotHover;

    @FXML
    private ImageView convertDotHover;


    /**
     * Diese Methode wird ausgelöst, wenn der Nutzer auf die Hilfe-Grafik klickt.
     *
     * Es wird dir .fxml-Datei der Hilfeseite geladen und diese angezeigt.
     *
     * @param event
     */
    @FXML
    void helpItemOnMouseClicked(MouseEvent event) {
        try {
            // Laden von helpPage.fxml
            Parent root = FXMLLoader.load(getClass().getResource("/view/helpPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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


    /**
     * Wenn der Nutzer auf "Konvertieren" klickt, dann wird ein neuer Thread gestartet, welcher alle .epub-Dateien
     * im docs-Ordner in .pdf-Dateien umwandelt.
     *
     * Zusätzlich wird ein Informationsfenster geöffnet, dass den Nutzer darauf hinweist, dass nun Dateien umgewandelt
     * werden.
     *
     * @param event
     */
    @FXML
    void convertItemOnMouseClicked(MouseEvent event) {
        // Thread für Konvertierung von .epub nach .pdf starten
        EpubToPdfConversion epubToPdfConversion = new EpubToPdfConversion();
        epubToPdfConversion.start();

        // Nachricht für Informationsfenster als String anlegen
        String infoMessage = "Ihre importierten EPUB-Dokumente werden nun zu PDF-Dokumenten umgewandelt.\n" +
                             "\n" +
                             "Bitte haben Sie einen Moment Geduld.\n" +
                             "Pruefen Sie ggf. im docs-Ordner, ob alle Dokumente konvertiert wurden!";

        // Informationsfenster anlegen
        Alert infoWindow = new Alert(Alert.AlertType.INFORMATION, infoMessage, ButtonType.OK);
        // Titel setzen
        infoWindow.setTitle("Information");
        // Headertext setzen
        infoWindow.setHeaderText("Information");
        // Größe nicht veränderbar
        infoWindow.setResizable(false);
        // Höhe und Breite für Fenster
        infoWindow.setWidth(650);
        infoWindow.setHeight(400);
        // Icon hinzufügen
        Stage stage = (Stage) infoWindow.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/other/icon/readtasticIcon_v1_500x500.png"));
        // Anzeige von Fenster
        infoWindow.show();
    }

    /**
     * Macht einen kleinen Punkt unterhalb der Konvertieren-Grafik sichtbar, wenn mit Maus überfahren.
     *
     * @param event
     */
    @FXML
    void convertItemOnMouseEntered(MouseEvent event) {
        convertDotHover.setVisible(true);
    }

    /**
     * Blendet Punkt wieder aus, wenn Maus nicht mehr die Konvertieren-Grafik überfährt.
     *
     * @param event
     */
    @FXML
    void convertItemOnMouseExited(MouseEvent event) {
        convertDotHover.setVisible(false);
    }


    /**
     * Wenn Nutzer auf die Hinzufügen-Grafik klickt, öffnet sich ein Fenster zur Auswahl einer Datei.
     * Hier soll der Nutzer .epub- oder .pdf-Dateien auswählen, welche dann in den docs-Ordner kopiert werden.
     * Diese können dann innerhalb der Anwendung gelesen werden.
     *
     * @param event
     */
    @FXML
    void addItemOnMouseClicked(MouseEvent event) {
        // Zugriff auf docs-Ordner
        File dir = new File("./docs");

        // FileChooser anlegen
        FileChooser fileChooser = new FileChooser();
        // Titel setzen
        fileChooser.setTitle("Fuege Dokument hinzu");
        // Derzeitige Stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Datei zwischenspeichern
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Kopiere in den docs-Ordner
        try {
            FileUtils.copyFileToDirectory(selectedFile, dir);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}