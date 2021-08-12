package readtastic.controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.pdfbox.rendering.PDFRenderer;
import readtastic.model.Data;

/**
 * Dies ist die Controller-Klasse von reader.fxml.
 *
 * Hier werden alle Interaktionen des Nutzers mit der grafischen Oberfläche des Readers verwaltet.
 *
 * @author Martin Sauer
 */
public class ReaderController {

    // Textfield, welches aktuelle Seitennummer anzeigt
    // Durch Eingabe kann auch zu einer anderen Seite gesprungen werden
    @FXML
    private TextField currentPageNumberTextField;

    // Anzeige der Seitenanzahl des Ebooks
    @FXML
    private Label totalPageNumberLabel;

    // Anzeige der aktuellen Seite des Ebooks
    @FXML
    private ImageView currentPageImageView;

    // PDFRenderer, welcher Seiten des derzeitigen Ebooks rendern kann
    private PDFRenderer renderer = new PDFRenderer(Data.documents.get(Data.currentEbookIndex).getPages());


    /**
     * Das ist die initialize-Methode des Readers, welche als erstes ausgeführt wird.
     *
     * Hier werden die korrekten Seitenzahlen für TextField und Label oben rechts angezeigt.
     * Gleichzeitig wird die aktuelle Seite angezeigt.
     */
    public void initialize() {
        // Setzen von Seitenanzahl in totalPageNumberLabel
        totalPageNumberLabel.setText(Data.documents.get(Data.currentEbookIndex).getNumberOfPages() + "");

        // Setzen der aktuellen Seitenzahl des TextFields
        // Standardmäßig 1 (Index 0) in jeder neuen Session
        currentPageNumberTextField.setText(Data.documents.get(Data.currentEbookIndex).getLastOpenedPage()+1 + "");

        // Aktuelle bzw. erste Seite anzeigen über getLastOpenedPage()
        try {
            currentPageImageView.setImage(SwingFXUtils.toFXImage(renderer.renderImage(Data.documents.get(Data.currentEbookIndex).getLastOpenedPage()), null));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Diese Methode wird ausgelöst, wenn Nutzer auf den Zurück-Button klickt.
     * Hier wird die Szene wieder geändert und bookshelf.fxml geladen, sodass wieder das Bücherregal angezeigt wird.
     *
     * @param event
     */
    @FXML
    void backButtonOnMouseClicked(MouseEvent event) {
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
     * Diese Methode wird ausgelöst, wenn Nutzer auf den Pfeil zum Zurückblättern klickt.
     *
     * Es wird die vorhergehende Seite im Reader angezeigt.
     *
     * @param event
     */
    @FXML
    void turnPageBackwardsOnMouseClicked(MouseEvent event) {
        // Prüfe ob vorherige Seite des Ebooks vorhanden
        if (Data.documents.get(Data.currentEbookIndex).getLastOpenedPage()+1 > 1) {
            // Wenn ja, reduziere lastOpenedPage des Ebooks um 1
            Data.documents.get(Data.currentEbookIndex).setLastOpenedPage(Data.documents.get(Data.currentEbookIndex).getLastOpenedPage()-1);

            // Zeige Seite über lastOpenedPage (vorherige) an
            try {
                currentPageImageView.setImage(SwingFXUtils.toFXImage(renderer.renderImage(Data.documents.get(Data.currentEbookIndex).getLastOpenedPage()), null));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // TextField aktualisieren
            currentPageNumberTextField.setText(Data.documents.get(Data.currentEbookIndex).getLastOpenedPage()+1 + "");
        }
    }

    /**
     * Diese Methode wird ausgelöst, wenn Nutzer auf den Pfeil zum Vorblättern klickt.
     *
     * Es wird die nachfolgende Seite im Reader angezeigt.
     *
     * @param event
     */
    @FXML
    void turnPageForwardOnMouseClicked(MouseEvent event) {
        // Prüfe ob nachfolgende Seite innerhalb der möglichen Seitenzahlen liegt
        if (Data.documents.get(Data.currentEbookIndex).getLastOpenedPage()+1 < Data.documents.get(Data.currentEbookIndex).getNumberOfPages()) {
            // Wenn ja, inkrementiere lastOpenedPage um 1
            Data.documents.get(Data.currentEbookIndex).setLastOpenedPage(Data.documents.get(Data.currentEbookIndex).getLastOpenedPage()+1);

            // Zeige Seite über lastOpenedPage (nachfolgende) an
            try {
                currentPageImageView.setImage(SwingFXUtils.toFXImage(renderer.renderImage(Data.documents.get(Data.currentEbookIndex).getLastOpenedPage()), null));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // TextField aktualisieren
            currentPageNumberTextField.setText(Data.documents.get(Data.currentEbookIndex).getLastOpenedPage()+1 + "");
        }
    }

    /**
     * Diese Methode wird ausgelöst, wenn der Nutzer das TextField ausgewählt hat und die Enter-Taste drückt.
     *
     * Es wird geprüft, ob die eingegebene Zahl eine Nummer ist und ob es eine valide Seitennummer zum Anzeigen ist.
     * Wenn ja, dann wird die Seite mit der zugehörigen Seitennummer im Reader angezeigt.
     *
     * @param event
     */
    @FXML
    void currentPageNumberTextFieldOnKeyPressed(KeyEvent event) {
        // Drücken der Enter-Taste bei ausgewähltem TextField
        if (event.getCode().equals(KeyCode.ENTER)) {
            // Gehe davon aus, Eingabe ist eine valide Nummer
            boolean isNumber = true;
            // Setze neue Nummer standardmäßig auf -1
            int enteredNewPageNumber = -1;
            try {
                // Speichere Nummer aus String im TextField
                enteredNewPageNumber = Integer.parseInt(currentPageNumberTextField.getText());
            } catch (Exception ex) {
                // Falls dies nicht klappt, weil es keine valide Nummer ist, dann setze isNumber auf false
                isNumber = false;
            }

            // Wenn Eingabe valide Nummer ist
            if (isNumber) {
                // Wenn Nummer mögliche, gültige Seitenzahl ist
                if ((enteredNewPageNumber >= 1) && (enteredNewPageNumber <= Data.documents.get(Data.currentEbookIndex).getNumberOfPages())) {
                    // Aktualisiere lastOpenedPage
                    Data.documents.get(Data.currentEbookIndex).setLastOpenedPage(enteredNewPageNumber-1);

                    // Neue Seite mittels lastOpenedPage anzeigen
                    try {
                        currentPageImageView.setImage(SwingFXUtils.toFXImage(renderer.renderImage(Data.documents.get(Data.currentEbookIndex).getLastOpenedPage()), null));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }
    }

}
