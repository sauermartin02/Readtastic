package readtastic.model;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 *  Instanzen dieser Klasse werden als Threads ausgeführt.
 *  Diese greifen auf den docs-Ordner zu und wandeln alle .epub-Dateien in .pdf-Dateien um, falls noch keine zugehörige
 *  .pdf-Datei vorhanden ist.
 *  Interagiert dabei mit der documents-ArrayList.
 *
 * @author Martin Sauer
 */
public class EpubToPdfConversion extends Thread {

    @Override
    public void run() {
        // Zugriff auf docs-Ordner
        File dir = new File("./docs");

        // Konvertiere alle .epub Dateien zu .pdf Dateien, falls noch nicht vorhanden.
        // Notwendig, da im Prinzip nur .pdf-Dateien verarbeitet werden, aber .epub mit unterstützt werden muss.
        // Konvertierung einfacher, als direkt .epub Dateien zu verarbeiten.
        // -----
        // Iteration über alle Dokumente im docs-Ordner.
        for (File file : dir.listFiles()) {
            // Berücksichtige nur .epub-Dateien.
            if (file.getName().endsWith(".epub")) {
                // Kontrolle, ob Datei schon als .pdf vorhanden ist.
                boolean existsAsPdf = false;
                for (File fileControl : dir.listFiles()) {
                    // Wenn Datei mit gleichem Titel als .pdf vorhanden.
                    // z.B: Dok1.epub -> Dok1.pdf
                    if (fileControl.getName().equals(FilenameUtils.removeExtension(file.getName()) + ".pdf")) {
                        existsAsPdf = true;
                    }
                }
                // Wenn nicht als .pdf vorhanden.
                if ( ! existsAsPdf) {
                    // Wandle .epub zu .pdf um.
                    // Dies geschieht über die Kommandozeile mittels des mitgelieferten Teilprogramms von Calibre "ebook-convert".
                    // -----
                    // Notwendig, da manuelle Umwandlung zu umständlich wäre und gefundene Bibliotheken nicht wie gewollt
                    // funktioniert haben.

                    String fileNameWithoutExtension = FilenameUtils.removeExtension(file.getName());
                    String docsDir = System.getProperty("user.dir") + "\\docs";
                    // Kommando, dass mittels Powershell ausgeführt werden soll
                    String command = "powershell.exe cd " +
                            docsDir + " ; " +
                            "..\\ebook-convert\\ebook-convert.exe '.\\" +
                            fileNameWithoutExtension + ".epub' " +
                            "'" + fileNameWithoutExtension + ".pdf'";

                    // Führe Kommando über Powershell aus
                    // Ruft "ebook-convert" auf, welches spezifizierte .epub-Datei ind .pdf-Datei umwandelt und diese
                    // im docs-Ordner speichert
                    try {
                        Runtime.getRuntime().exec(command);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

}
