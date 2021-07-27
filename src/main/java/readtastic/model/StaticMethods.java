package readtastic.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.io.File;

/**
 * Diese Klasse wird genutzt, um allgemein benutzte statische Methoden innerhalb der Anwendung zu definieren.
 *
 * @author Martin Sauer
 */
public class StaticMethods {

    /**
     * Diese Methode greift auf den "docs"-Ordner zu, welcher sich im gleichen Verzeichnis, wie die ausgeführte .jar-Datei,
     * befindet.
     * In diesem Ordner sollen nur vom Nutzer importierte Dokumente (.pdf oder .epub) sein.
     * Jegliche andere Dokumente, z.B. .txt o.ä. werden nicht berücksichtigt.
     * Es wird für jede .pdf-Datei ein "Ebook"-Objekt erstellt, welche in einer zugehörigen öffentlichen Datenstruktur "documents"
     * gespeichert werden.
     */
    public static void handlePdfFiles() {
        // Zugriff auf docs-Ordner
        File dir = new File("./docs");

        // Iteration über alle Dokumente im docs-Ordner
        for (File file : dir.listFiles()) {
            // Berücksichtige nur .pdf-Dateien
            if (file.getName().endsWith(".pdf")) {
                // Lade .pdf-Datei als PDDocument
                PDDocument pages = null;
                try {
                    pages = PDDocument.load(file);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Titel für Dokument aus Dateinamen ableiten
                String title = FilenameUtils.removeExtension(file.getName());

                // Seitenanzahl aus PDDocument-Objekt festlegen
                int numberOfPages = pages.getNumberOfPages();

                // Cover (1. Seite) des Dokuments als JavaFX Image festlegen
                Image cover = null;
                try {
                    cover = SwingFXUtils.toFXImage(new PDFRenderer(pages).renderImage(0), null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Anlegen von Ebook-Objekt
                Ebook ebook = new Ebook(title, numberOfPages, pages, cover);

                // Hinzufügen dieses Ebook-Objekts zu interner Datenstruktur "documents", wenn noch nicht vorhanden
                // Sinnvoll, da es sonst zu Duplikaten bei Szenenwechsel kommen kann
                boolean isAdded = false;
                // Kontrolle, ob schon hinzugefügt über documents-Datenstruktur
                for (int i = 0; i <= Data.documents.size()-1; i++) {
                    // Kontrolle mittels Titel
                    if (Data.documents.get(i).getTitle().equals(ebook.getTitle())) {
                        isAdded = true;
                    }
                }

                // Füge Ebook hinzu, wenn noch kein Ebook mit gleichem Titel existiert
                if ( ! isAdded) {
                    Data.documents.add(ebook);
                }

                // Schließen von PDDocument-Objekt, nachdem es nicht mehr gebraucht wird
                try {
                    pages.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
