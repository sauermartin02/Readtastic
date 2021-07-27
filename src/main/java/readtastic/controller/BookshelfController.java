package readtastic.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import readtastic.model.StaticMethods;

/**
 * Das ist die Controller-Klasse von bookshelf.fxml.
 * Hier wird ein Bücherregal dargestellt, welches die importierten Dokumente des Nutzers enthält.
 * Der Nutzer kann diese Dokumente im Regal anklicken, um diese zu lesen.
 *
 * @author Martin Sauer
 */
public class BookshelfController {

    // Wurde in bookshelf.fxml definiert und dient zum Anzeigen von zusätzlichen grafischen Elementen, nachdem .fxml-Datei
    // geladen wurde.
    @FXML
    private Pane pane;

    /**
     * Das ist die initialize-Methode des Controllers, welche nach dem Laden der .fxml-Datei aufgerufen wird.
     * Sie wird u.a. genutzt, um die importierten Dokumente des Nutzers im Regal anzuzeigen und um .epub-Dateien
     * in .pdf-Dateien umzuwandeln.
     */
    @FXML
    public void initialize() {
        StaticMethods.handlePdfFiles();
        // Todo: ...
    }

    /**
     * Diese Methode wird ausgelöst, wenn Nutzer auf den Zurück-Button klickt.
     * Hier wird die Szene wieder geändert und mainMenu.fxml geladen, sodass wieder das Hauptmenü angezeigt wird.
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
