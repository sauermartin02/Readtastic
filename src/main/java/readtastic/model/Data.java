package readtastic.model;

import java.util.ArrayList;

/**
 * Diese Klasse beinhaltet genutzte Daten / Datenstrukturen, welche innerhalb der Anwendung öffentlich zugänglich sein sollen.
 *
 * @author Martin Sauer
 */
public class Data {

    // Datenstruktur zum Verwalten erstellter Ebook-Objekte
    public static ArrayList<Ebook> documents = new ArrayList<Ebook>();

    // Öffentlich zugängliche Variable, in welcher der Index des gerade geöffneten Ebooks gespeichert wird
    public static int currentEbookIndex;

}
