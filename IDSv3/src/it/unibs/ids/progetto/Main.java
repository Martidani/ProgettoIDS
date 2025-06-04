package it.unibs.ids.progetto;
import java.util.ArrayList;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;
import it.unibs.ids.progetto.ecccezioni.NodeNotLeafException;
import it.unibs.ids.progetto.ecccezioni.RootTreeException;

/**
 * Classe Main per l'esecuzione del programma.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Main {

    
	public final static String[] vociC = 
		{"Introdurre comprensorio geografico", "Introdurre albero", "Visualizza comprensorio", 
			"Visualizza gerarchia", "Visualizza fattori di conversione"};
	public final static String[] vociF = 
		{"Naviga nella gerarchia", "Proponi uno scambio"};
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
	            		
	            		accesso = Application.login(utenza, Fruitore.TIPOUTENTE);
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

	                default:
	                    break;
	            }
	        } while (scelta != 0);
	    }
	    
	    //modalità fruitore
	    if (accesso != 0 && !tipoFunzionamento) {
	        do {
	            scelta = menuF.scegli();
	            switch (scelta) {

	                case 1:
	                	Application.navigaGerarchia(gerarchia);
							
	                    break;
	                    
	                case 2:
	                	Application.proponiScambio(utenza, gerarchia);
	      
	                	
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