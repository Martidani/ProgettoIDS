package it.unibs.ids.progetto;
import java.util.ArrayList;
import it.unibs.fp.mylib.MyMenu;
import it.unibs.ids.progetto.news.Albero;
import it.unibs.ids.progetto.news.DefaultInitializer;
import it.unibs.ids.progetto.news.FileManager;
import it.unibs.ids.progetto.news.Geografia;
import it.unibs.ids.progetto.news.ecccezioni.RootTreeException;

/**
 * Classe Main per l'esecuzione del programma.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Main {


    public final static String[] voci = 
        {"Introdurre comprensorio geografico", "Introdurre albero", "Visualizza comprensorio", 
            "Visualizza gerarchia", "Visualizza fattori di conversione"};
    public final static String[] vociAccesso = 
        {"Registrazione","Login"};
    
    
    public static void main(String[] args) throws RootTreeException {
        MyMenu menuAccesso = new MyMenu("Menu accesso", vociAccesso);
        MyMenu menu = new MyMenu("Menu principale", voci);

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
            accesso = menuAccesso.scegli();
            switch (accesso) {
                case 1:
                	Application.registrazione(utenza);
                    break;

                case 2:
                    accesso = Application.login(utenza);
                    break;

                default:
                    break;
            }
        } while (accesso == 1);

        if (accesso != 0) {
            int scelta;
            do {
                scelta = menu.scegli();
                switch (scelta) {

                    case 1:
                    	Application.creaComprensorio(geografia);
                        break;

                    case 2:
                        ArrayList<Nodo> foglieAttuali = new ArrayList<>();
                        Nodo root = Application.creaRadice(gerarchia);
                        Application.creaNodiFiglio(root, gerarchia, root, foglieAttuali);
                        gerarchia.addAlbero(new Albero(root));
                        Application.creaFattoriConversione(gerarchia, foglieAttuali);
                        break;

                    case 3:
                        System.out.println(geografia.toString());
                        break;

                    case 4:
                        String ger = gerarchia.toString();
                        System.out.println(ger);
                        break;

                    case 5:
                        Application.stampaFattori(gerarchia);
                        break;

                    default:
                        break;
                }
            } while (scelta != 0);
        }

        FileManager.salvaSuFile(gerarchia);
        FileManager.salvaSuFile(utenza);
        FileManager.salvaSuFile(geografia);
    }
}
