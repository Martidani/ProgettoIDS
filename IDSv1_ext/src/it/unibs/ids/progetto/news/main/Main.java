package it.unibs.ids.progetto.news.main;

import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Utenza;
import it.unibs.ids.progetto.news.DefaultInitializer;
import it.unibs.ids.progetto.news.FileManager;
import it.unibs.ids.progetto.news.Geografia;

/**
 * Classe che contiene il metodo main per l'esecuzione dell'applicazione.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class Main {


    public static void main(String[] args) throws Exception {

        // Caricamento da file
        Utenza utenza = FileManager.caricaUtenza();
        Gerarchia gerarchia = FileManager.caricaGerarchia();
        Geografia geografia = FileManager.caricaGeografia();

        if (utenza == null || gerarchia == null || geografia == null) {
            // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
            DefaultInitializer defaultInitializer = new DefaultInitializer();
            gerarchia = defaultInitializer.getGerarchia();
            utenza = defaultInitializer.getUtenza();
            geografia = defaultInitializer.getGeografia();
        } else {
            System.out.println("Lettura da file: " + FileManager.getUtenzaFile() 
            + ", " + FileManager.getGerarchiaFile() + ", " + FileManager.getGeografiaFile());
        }

        int accesso;
        do {
            accesso = MenuConfController.menuAccesso(utenza);
        } while (accesso == 1);

        if (accesso != 0) {
            int scelta;
            do {
                scelta = MenuConfController.menuConfiguratore(gerarchia, geografia);
            } while (scelta != 0);
        }

        FileManager.salvaSuFile(gerarchia);
        FileManager.salvaSuFile(utenza);
        FileManager.salvaSuFile(geografia);
    }
}
