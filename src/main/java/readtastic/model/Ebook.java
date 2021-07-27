package readtastic.model;

import javafx.scene.image.Image;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * Dies ist die Ebook-Klasse.
 * Ein Ebook-Objekt wird aus einer .pdf-Datei erstellt und wird innerhalb der Anwendung zum Anzeigen von Seiten/Covern
 * und zur Verwaltung genutzt.
 *
 * @author Martin Sauer
 */
public class Ebook {

    // Titel des Ebooks
    private String title;

    // Seitenanzahl
    private int numberOfPages;

    // Enthält alle Seiten des Ebooks
    // Wird zur Anzeige im Reader genutzt
    private PDDocument pages;

    // Cover des Ebooks
    // Wird zur Anzeige im Bücherregal genutzt
    private Image cover;


    /**
     * Konstruktor für Ebook-Objekte.
     *
     * @param title             Titel
     * @param numberOfPages     Seitenanzahl
     * @param pages             Inhalt als Seiten
     * @param cover             Cover als Image
     */
    public Ebook(String title, int numberOfPages, PDDocument pages, Image cover) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.pages = pages;
        this.cover = cover;
    }

    /**
     * Gibt Titel eines Ebooks zurück.
     *
     * @return  Titel
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gibt Seitenanzahl eines Ebooks zurück.
     *
     * @return  Seitenanzahl
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * Gibt Seiten (Inhalt) des Ebooks zurück.
     *
     * @return  Seiten
     */
    public PDDocument getPages() {
        return pages;
    }

    /**
     * Gibt Cover als Image (JavaFX) zurück.
     *
     * @return  Cover
     */
    public Image getCover() {
        return cover;
    }
}
