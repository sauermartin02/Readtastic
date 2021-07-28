package readtastic.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import readtastic.model.Data;
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
     * Das ist die initialize-Methode des Controllers, welche direkt nach dem Laden der .fxml-Datei aufgerufen wird.
     * Sie wird u.a. genutzt, um die importierten Dokumente des Nutzers im Regal anzuzeigen und um die .pdf-Dateien
     * im "docs"-Ordner zu verarbeiten.
     */
    @FXML
    public void initialize() {
        // Erstelle Ebook-Objekte aus .pdf-Dateien im docs-Ordner
        StaticMethods.handlePdfFiles();
        // Zeige Cover an und definiere Aktionen bei Klick auf Cover
        showCover();
    }

    /**
     * Diese Methode wird genutzt, um die Cover der Ebooks im Bücherregal anzuzeigen.
     * Gleichzeitig werden die Aktionen definiert, die ausgeführt werden sollen, wenn der Nutzer auf eines der Ebooks klickt.
     * Dazu gehören z.B. der Szenewechsel zum Reader und das Anzeigen des besagten Ebooks.
     */
    private void showCover() {
        // Anfangskoordinaten des ersten Ebooks im Regal
        int currentX = 150;
        int currentY = 44;
        // Iteration über alle Ebooks
        for (int i = 0; i <= Data.documents.size()-1; i++) {
            // Zurzeit wird nu Anzeige von maximal 96 Dokumenten unterstützt
            // Daher werden nur die ersten 96 Dokumente im "docs"-Ordner angezeigt
            if (i <= 95) {
                // Erstelle ImageView aus Ebook-Cover
                ImageView cover = new ImageView(Data.documents.get(i).getCover());
                // Lege Breite und Höhe fest
                // Behält A4-Format bei
                cover.setFitWidth(100);
                cover.setFitHeight(141);
                // Verschiebe ImageView an richtige Stelle im Regal
                cover.setX(currentX);
                cover.setY(currentY);

                // Definiere Aktionen bei Klick auf Ebook-Cover
                int finalI = i;
                cover.setOnMouseClicked(mouseEvent -> {
                    // Todo: ...
                });

                // Lege Aussehen des Cursors fest, wenn Cover überfahren wird
                cover.setCursor(Cursor.HAND);

                // Füge Cover zu grafischer Oberfläche hinzu
                pane.getChildren().add(cover);

                // Lege Koordinaten für nächstes Ebook fest
                // Rutsche nach rechts in derzeitiger Regalebene
                currentX += 148;

                // Wenn Ende einer Regalebene erreicht, rutsche in nächste, untere Regalebene
                // Das ist nach 8 Ebooks der Fall
                if (i%8 == 7) {
                    currentY += 150;
                    currentX = 150;
                }
            }
        }
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
