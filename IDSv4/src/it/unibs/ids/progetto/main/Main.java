package it.unibs.ids.progetto.main;
import java.util.ArrayList;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;
import it.unibs.ids.progetto.Albero;
import it.unibs.ids.progetto.Commercio;
import it.unibs.ids.progetto.Configuratore;
import it.unibs.ids.progetto.DefaultInitializer;
import it.unibs.ids.progetto.FileManager;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.NodeNotLeafException;
import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.RootTreeException;
import it.unibs.ids.progetto.Utenza;

/**
 * Classe Main per l'esecuzione del programma.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Main {

    
	public final static String[] vociC = 
		{"Introdurre comprensorio geografico", "Introdurre albero", "Visualizza comprensorio", 
			"Visualizza gerarchia", "Visualizza fattori di conversione", "Visualizza proposte", "Visualizza insiemi chiusi"};
	public final static String[] vociF = 
		{"Naviga nella gerarchia", "Proponi uno scambio", "Visualizza proposte", "Ritira Proposte"};
	public final static String[] vociAccesso = 
		{"Registrazione","Login"};
    
    
    public static void main(String[] args) throws RootTreeException, NodeNotLeafException {
	    MyMenu menuAccesso = new MyMenu("Menu accesso", vociAccesso);
	    MyMenu menuC = new MyMenu("Menu principale", vociC);
	    MyMenu menuF = new MyMenu("Menu principale", vociF);

        // Caricamento da file
        Utenza utenza = FileManager.caricaUtenza();
        Gerarchia gerarchia = FileManager.caricaGerarchia();
        Geografia geografia = FileManager.caricaGeografia();
        Commercio commercio = FileManager.caricaCommercio();

        if (utenza == null || gerarchia == null || geografia == null || commercio == null) {
            // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
            DefaultInitializer defaultInitializer = DefaultInitializer.getDefaultInitializer();
            if (gerarchia == null)
            	gerarchia = defaultInitializer.getGerarchia();
            if (utenza == null)
            	utenza = defaultInitializer.getUtenza();
            if (geografia == null)
            	geografia = defaultInitializer.getGeografia();
            if (commercio == null)
            	commercio = defaultInitializer.getCommercio();

        } else {
            System.out.println("Lettura da file: " 
                   + FileManager.getUtenzaFile() 
            + ", " + FileManager.getGerarchiaFile() 
            + ", " + FileManager.getGeografiaFile()
            + ", " + FileManager.getCommercioFile());
        }

	    boolean tipoFunzionamento = false;
	    int accesso;
	    do {
	        accesso = menuAccesso.scegli();
	        switch (accesso) {
	            case 1:
	            	if (InputDati.yesOrNo("Vuoi essere un fruitore? ")) {
	                    System.out.print(geografia.toString());
		            	Application.registrazioneF(utenza, geografia);
	            	}

	            	else
	            		Application.registrazioneC(utenza);
	                break;

	            case 2:
	            	if (InputDati.yesOrNo("Sei un fruitore?")) {
	            		
	            		accesso = Application.login(utenza, Fruitore.TIPO_UTENTE);
	            		tipoFunzionamento=false;
	            	} else {
	            		accesso = Application.login(utenza, Configuratore.TIPOUTENTE);
	            		tipoFunzionamento=true;
	            	}
	                break;

	            default:
	                break;
	        }
	    } while (accesso == 1);

	    //modalità configuratre
        int scelta;
	    if (accesso != 0 && tipoFunzionamento) {
	        do {
	            scelta = menuC.scegli();
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
	                    System.out.print(geografia.toString());
	                    break;

	                case 4:
	                	String ger = gerarchia.toString();
	                    System.out.println(ger);
	                    break;

	                case 5:
	                	Application.stampaFattori(gerarchia);
	                    break;
	                    
	                case 6:
	                	Application.visualizzaProposteFoglia(commercio, gerarchia);
	                    break;
	                    
	                case 7:
	                	Application.visualizzaInsiemiChiusi(commercio, gerarchia);
	                    break;

	                default:
	                    break;
	            }
	        } while (scelta != 0);
	    }
	    
	    
	    //modalità fruitore
	    if (accesso != 0 && !tipoFunzionamento) {
	    	commercio.setUtenteDiSessione((Fruitore)utenza.getUtenteDiSessione());
	    	
		    do {
	            scelta = menuF.scegli();
	            switch (scelta) {

	                case 1:
	                	Application.navigaGerarchia(gerarchia);
							
	                    break;
	                    
	                case 2:
	                	Application.proponiScambio(utenza, gerarchia, commercio);
		                break;
		                
	                case 3:
	                	Application.visualizzaProposte(commercio);
	                break;
	                
	                case 4:
	                	Application.ritiraProposte(commercio);
	                break;

	                default:
	                    break;
	            }
	        } while (scelta != 0);
	    }

	    FileManager.salvaSuFile(gerarchia);
	    FileManager.salvaSuFile(utenza);
	    FileManager.salvaSuFile(geografia);
	    FileManager.salvaSuFile(commercio);
    }

	
}