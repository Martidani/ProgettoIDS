package it.unibs.ids.progetto.main;

import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.DefaultInitializer;
import it.unibs.ids.progetto.FileManager;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.RootTreeException;
import it.unibs.ids.progetto.Utenza;


/**
 * Classe che contiene il metodo main per l'esecuzione dell'applicazione.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class Main {


    public static void main(String[] args) throws RootTreeException  {

        // Caricamento da file
        Utenza utenza = FileManager.caricaUtenza();
        Gerarchia gerarchia = FileManager.caricaGerarchia();
        Geografia geografia = FileManager.caricaGeografia();

        if (utenza == null || gerarchia == null || geografia == null) {
            // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
            DefaultInitializer defaultInitializer = DefaultInitializer.getDefaultInitializer();
            gerarchia = defaultInitializer.getGerarchia();
            utenza = defaultInitializer.getUtenza();
            geografia = defaultInitializer.getGeografia();
        } else {
            System.out.println("Lettura da file: " + FileManager.getUtenzaFile() 
            + ", " + FileManager.getGerarchiaFile() + ", " + FileManager.getGeografiaFile());
        }

        boolean tipoFunzionamento;
        int num = InputDati.leggiInteroRange(" \n Modalità di funzionamento:\n 1 - Configuratore\n 2- Fruitore\n --> ", 1, 2);
        if (num==1)
        	tipoFunzionamento = false;
        else
        	tipoFunzionamento = true;
       
        MenuController menuController;
        if (tipoFunzionamento)
        	menuController = new MenuFruitorController();
        else
        	menuController = new MenuConfController();
       
        int accesso, scelta;
        do {
            accesso = menuController.menuAccesso(utenza, geografia);
        } while (accesso == 1);

        
	    //modalità fruitore/configuratore
	    if (accesso != 0) {
	        do {
	            scelta = menuController.menu(gerarchia, geografia, utenza);
	        } while (scelta != 0);
	    }

	    //salva su file
        FileManager.salvaSuFile(gerarchia);
        FileManager.salvaSuFile(utenza);
        FileManager.salvaSuFile(geografia);
    }


}
