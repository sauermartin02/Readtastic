package readtastic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import readtastic.model.EpubToPdfConversion;

/**
 * Dies ist die Entrypoint-Klasse der Anwendung.
 *
 * Sie baut das Fenster auf und führt in die Anwendung hinein, indem sie die fxml-Datei des Hauptmenüs lädt und
 * dieses dann anzeigt.
 *
 * @author Martin Sauer
 */
public class Entrypoint extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Erstellt einen neuen Thread, welcher alle .epub Dateien im docs-Ordner in .pdf Dateien umwandelt
        EpubToPdfConversion EpubToPdfConversionThread = new EpubToPdfConversion();
        // Startet diesen Thread
        EpubToPdfConversionThread.start();

        // Fenstertitel setzen
        primaryStage.setTitle("Readtastic");

        // Größe nicht veränderbar
        primaryStage.setResizable(false);

        // FXML laden und Icon für Anwendung setzen
        Parent root = null;
        try {
            // FXML des Hauptmenüs laden
            root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
            primaryStage.setScene(new Scene(root));

            // Icon setzen
            primaryStage.getIcons().add(new Image("/other/icon/readtasticIcon_v1_500x500.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Anzeigen der Benutzeroberfläche des Hauptmenüs
        primaryStage.show();
    }
}