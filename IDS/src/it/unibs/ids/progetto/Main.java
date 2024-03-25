package it.unibs.ids.progetto;
import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;
import it.unibs.ids.progetto.news.DefaultInitializer;
import it.unibs.ids.progetto.news.FileManager;

/**
 * Classe Main per l'esecuzione del programma.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Main {


	private static final int NUM_MAX_TENTATIVI = 3;
	public final static String[] voci = 
		{"Introdurre comprensorio geografico", "Introdurre albero", "Visualizza comprensorio", 
			"Visualizza gerarchia", "Visualizza fattori di conversione"};
	public final static String[] vociAccesso = 
		{"Registrazione","Login"};
	
	
	public static void main(String[] args) throws Exception {
	    MyMenu menuAccesso = new MyMenu("Menu accesso", vociAccesso);
	    MyMenu menu = new MyMenu("Menu principale", voci);

	    // Caricamento da file
	    GestioneUtenza gestioneUtenza = FileManager.caricaGestioneUtenza();
	    Gerarchia gerarchia = FileManager.caricaGerarchia();

	    if (gestioneUtenza == null || gerarchia == null) {
	        // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
	        gerarchia = DefaultInitializer.defaultTree();
	        gestioneUtenza = DefaultInitializer.defaultAccess();
	    } else {
	        System.out.println("Lettura da file: " + FileManager.getGestioneUtenzaFile() + ", " + FileManager.getGerarchiaFile());
	    }

	    int accesso;
	    do {
	        accesso = menuAccesso.scegli();
	        switch (accesso) {
	            case 1:
	                registrazione(gestioneUtenza);
	                break;

	            case 2:
	                accesso = login(gestioneUtenza, accesso);
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
	                    aggiungiComprensorio(gestioneUtenza);
	                    break;

	                case 2:
	                    ArrayList<Nodo> foglieAttuali = new ArrayList<>();
	                    Nodo root = creaNodoRadice(gerarchia);
	                    creaNodiFiglio(root, gerarchia, root, foglieAttuali);
	                    gerarchia.addAlberi(root);
	                    inserisciFattoriConversione(gerarchia, foglieAttuali);
	                    break;

	                case 3:
	                    System.out.println(gestioneUtenza.toString());
	                    break;

	                case 4:
	                    System.out.println(gerarchia.toString());
	                    break;

	                case 5:
	                    stampaFattori(gerarchia);
	                    break;

	                default:
	                    break;
	            }
	        } while (scelta != 0);
	    }

	    FileManager.salvaSuFile(gerarchia);
	    FileManager.salvaSuFile(gestioneUtenza);
	}


	/**
	 * Metodo per creare la radice dell'albero.
	 * 
	 * @param gerarchia L'oggetto Gerarchia utilizzato per verificare l'esistenza del nome radice.
	 * @return Il nodo radice creato.
	 */
	private static Nodo creaNodoRadice(Gerarchia gerarchia) {
	    String radice;
	    do {
	        radice = InputDati.leggiStringaNonVuota("Nome radice -> ");
	    } while (gerarchia.verificaEsistenzaNomeRadice(radice));

	    String campo = InputDati.leggiStringaNonVuota("Campo -> ");
	    Nodo root = new Nodo(radice, true, campo);

	    aggiungiValoriDominio(root);

	    return root;
	}

	/**
	 * Metodo per aggiungere i valori del dominio a un nodo.
	 * 
	 * @param nodo Il nodo a cui aggiungere i valori del dominio.
	 */
	private static void aggiungiValoriDominio(Nodo nodo) {
	    int num = 0;
	    do {
	        num++;
	        String valoreDominio = InputDati.leggiStringaNonVuota(num + "' valore del dominio -> ");
	        if (InputDati.yesOrNo("  Vuoi inserire una descrizione di " + valoreDominio + "? ")) {
	            String descrizioneDominio = InputDati.leggiStringaNonVuota("Descrizione -> ");
	            nodo.addElementiDominio(valoreDominio, descrizioneDominio);
	        } else {
	            nodo.addElementiDominio(valoreDominio);
	        }
	    } while (InputDati.yesOrNo("Vuoi aggiugere un altro elemento al dominio? "));
	}

	/**
	 * Metodo per inserire i fattori di conversione tra nodi.
	 * 
	 * @param gerarchia       L'oggetto Gerarchia utilizzato per gestire la struttura gerarchica.
	 * @param foglieAttuali  La lista delle foglie attuali.
	 * @throws Exception     Eccezione in caso di problemi durante l'inserimento.
	 */
	private static void inserisciFattoriConversione(Gerarchia gerarchia, ArrayList<Nodo> foglieAttuali) throws Exception {
	    System.out.println("\nInserimento fattori di conversione:");
	    do {
	        Nodo nodo1 = chiediFogliaRadice("Foglia 1:", gerarchia);
	        Nodo nodo2 = chiediFogliaRadice("Foglia 2:", gerarchia);

	        double fattoreDiConversione = chiediFattoreConversione(gerarchia);

	        boolean condizione = !gerarchia.checkFoglia(nodo1, foglieAttuali) && !gerarchia.checkFoglia(nodo2, foglieAttuali);
	        if (!condizione) {
	            gerarchia.aggiungiFattoreConversione(nodo1, nodo2, fattoreDiConversione);
	        }
	    } while (InputDati.yesOrNo("Vuoi continuare l'inserimento? "));

	    gerarchia.addTransitivoFattoreConversione();
	}

	/**
	 * Metodo per chiedere la foglia e la radice e ottenere il nodo corrispondente.
	 * 
	 * @param messaggio   Il messaggio da visualizzare.
	 * @param gerarchia   L'oggetto Gerarchia utilizzato per cercare il nodo.
	 * @return            Il nodo corrispondente alla foglia e alla radice specificate.
	 */
	private static Nodo chiediFogliaRadice(String messaggio, Gerarchia gerarchia) {
	    Nodo nodo;
	    do {
	        System.out.println(messaggio);
	        String foglia = InputDati.leggiStringaNonVuota("  Nome -> ");
	        String radice = InputDati.leggiStringaNonVuota("  Radice -> ");
	        nodo = gerarchia.visualizzaNodo(foglia, radice, gerarchia.getAlberi());
	    } while (nodo == null);
	    return nodo;
	}

	/**
	 * Metodo per chiedere il fattore di conversione.
	 * @param gerarchia   L'oggetto Gerarchia utilizzato per verificare il fattore di conversione.
	 * @return            Il fattore di conversione inserito.
	 */
	private static double chiediFattoreConversione(Gerarchia gerarchia) {
	    double fattoreDiConversione;
	    do {
	        fattoreDiConversione = InputDati.leggiDouble("Fattore di conversione -> ");
	    } while (!gerarchia.verificaFattoreConversione(fattoreDiConversione));
	    return fattoreDiConversione;
	}

	/**
	 * Metodo per il login.
	 * 
	 * @param gestioneUtenza  L'oggetto GestioneUtenza utilizzato per gestire l'accesso.
	 * @param accesso         L'accesso corrente.
	 * @return                L'accesso aggiornato.
	 */
	private static int login(GestioneUtenza gestioneUtenza, int accesso) {
	    for (int i = 0; i < NUM_MAX_TENTATIVI; i++) {
	        System.out.println("Inserisci dati di login: ");
	        String ID = InputDati.leggiStringaNonVuota("  ID: ");
	        String PSSW = InputDati.leggiStringaNonVuota("  Password: ");
	        accesso = effettuaLogin(gestioneUtenza, ID, PSSW);
	        if (accesso != 0) {
	            break;
	        }
	    }
	    return accesso;
	}

	/**
	 * Metodo per effettuare il login.
	 * 
	 * @param gestioneUtenza  L'oggetto GestioneUtenza utilizzato per verificare le credenziali.
	 * @param ID              L'ID inserito.
	 * @param PSSW            La password inserita.
	 * @return                Il risultato del login.
	 */
	private static int effettuaLogin(GestioneUtenza gestioneUtenza, String ID, String PSSW) {
	    Configuratore conf = gestioneUtenza.verificaEsistenzaConfiguratore(ID, PSSW);
	    if (conf == null) {
	        System.out.println(" ! Non esiste configuratore con queste credenziali !");
	        return 1;
	    } else if (!conf.getCredenziali().isDefinitive()) {
	        System.out.println("Scegli nuove credenziali: ");
	        Credenziali credenzialiRegistrazione = inserisciCredenzialiRegistrazione(gestioneUtenza);
	        conf.setCredenziali(credenzialiRegistrazione);
	        conf.setIsDefinitivo(true);
	        return 2;
	    } else {
	        System.out.println("-> Utente riconosciuto");
	        return 2;
	    }
	}

	/**
	 * Metodo per creare i figli di un nodo.
	 * 
	 * @param nodoParent     Il nodo genitore.
	 * @param gerarchia      L'oggetto Gerarchia utilizzato per gestire la struttura gerarchica.
	 * @param radice         La radice dell'albero gerarchico.
	 * @param foglieAttuali  La lista delle foglie attuali.
	 */
	private static void creaNodiFiglio(Nodo nodoParent, Gerarchia gerarchia, Nodo radice, ArrayList<Nodo> foglieAttuali) {
	    int numFigli = 0;
	    do {
	        numFigli++;
	        System.out.println("\n" + numFigli + "' figlio (di " + nodoParent.getNome() + "): ");
      
	        String nome;
	        do {
	            nome = InputDati.leggiStringaNonVuota("Nome -> ");
	        } while (gerarchia.verificaEsistenzaNomeNonRadice(nome, radice));

	        boolean isFoglia = InputDati.yesOrNo("È foglia? ");
	        Nodo nodoChild;
	        if (isFoglia) {
	            nodoChild = new Nodo(nome);
	            foglieAttuali.add(nodoChild);
	        } else {
	            nodoChild = creaNodoNonFoglia(nome);
	        }

	        try {
	            nodoParent.addChild(nodoChild);
	        } catch (Exception e) {
	            e.getMessage();
	        }
	    } while (numFigli < nodoParent.getDominio().size());

	    for (Nodo nodo : nodoParent.getChildren()) {
	        if (!nodo.isLeaf()) {
	            creaNodiFiglio(nodo, gerarchia, radice, foglieAttuali);
	        }
	    }
	}

	/**
	 * Metodo per creare un nodo non foglia.
	 * 
	 * @param nome  Il nome del nodo.
	 * @return      Il nodo non foglia creato.
	 */
	private static Nodo creaNodoNonFoglia(String nome) {
	    String campo = InputDati.leggiStringaNonVuota("Campo -> ");
	    Nodo nodoChild = new Nodo(nome, false, campo);

	    aggiungiValoriDominio(nodoChild);

	    return nodoChild;
	}

	/**
	 * Metodo per stampare i fattori di conversione.
	 * 
	 * @param gerarchia  L'oggetto Gerarchia utilizzato per visualizzare i fattori di conversione.
	 */
	private static void stampaFattori(Gerarchia gerarchia) {
	    String foglia = InputDati.leggiStringaNonVuota("Inserisci nome foglia: ");
	    String radice = InputDati.leggiStringaNonVuota("Inserisci radice della gerarchia della foglia: ");
	    Nodo nodo = gerarchia.visualizzaNodo(foglia, radice, gerarchia.getAlberi());
	    if (nodo == null)
	        System.out.println("  Non è stata trovata nessuna corrispondenza");
	    else
	        System.out.println(nodo.toStringF());
	}

	/**
	 * Metodo per aggiungere un comprensorio.
	 * 
	 * @param gestioneUtenza  L'oggetto GestioneUtenza utilizzato per aggiungere il comprensorio.
	 */
	private static void aggiungiComprensorio(GestioneUtenza gestioneUtenza) {
	    Comprensorio comprensorio = new Comprensorio();
	    System.out.println("Inserisci comprensorio (Exit per uscire) ");
	    String comune;

	    do {
	        comune = InputDati.leggiStringaNonVuota("  comune -> ");
	        comprensorio.addComune(comune);
	    } while (!comune.equalsIgnoreCase("Exit"));

	    int size = comprensorio.getComprensorio().size();
	    comprensorio.getComprensorio().remove(size - 1);

	    gestioneUtenza.addComprensorio(comprensorio);
	}

	/**
	 * Metodo per registrare un nuovo utente.
	 * 
	 * @param gestioneUtenza  L'oggetto GestioneUtenza utilizzato per registrare il nuovo utente.
	 */
	private static void registrazione(GestioneUtenza gestioneUtenza) {
	    Configuratore configuratore = new Configuratore();
	    String id = configuratore.getID();
	    String psswd = configuratore.getPSSW();
	    System.out.println("ID di default: " + id);
	    System.out.println("Password di default " + psswd);

	    Credenziali credenziali = new Credenziali(id, psswd);
	    configuratore.setCredenziali(credenziali);
	    configuratore.setIsDefinitivo(false);
	    gestioneUtenza.addUtente(configuratore);
	}

	/**
	 * Metodo per inserire le credenziali di registrazione.
	 * 
	 * @param gestioneUtenza  L'oggetto GestioneUtenza utilizzato per verificare l'esistenza dell'ID.
	 * @return                Le credenziali inserite dall'utente.
	 */
	private static Credenziali inserisciCredenzialiRegistrazione(GestioneUtenza gestioneUtenza) {
	    String ID;
	    do {
	        ID = InputDati.leggiStringaNonVuota("  ID: ");
	        if (gestioneUtenza.verificaEsistenzaID(ID)) System.out.println(" ! ID già utilizzato ! ");
	    } while (gestioneUtenza.verificaEsistenzaID(ID));

	    String PSSW = InputDati.leggiStringaNonVuota("  Password: ");
	    return new Credenziali(ID, PSSW);
	}
	
	
}