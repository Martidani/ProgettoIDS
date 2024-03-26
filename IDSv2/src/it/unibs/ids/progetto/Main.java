package it.unibs.ids.progetto;
import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;
import it.unibs.ids.progetto.news.Fruitore;

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
	
	
	public static void main(String[] args) throws LeafHasChildrenException {
	    MyMenu menuAccesso = new MyMenu("Menu accesso", vociAccesso);
	    MyMenu menu = new MyMenu("Menu principale", voci);

	    // Caricamento da file
	    Utenza utenza = FileManager.caricaUtenza();
	    Gerarchia gerarchia = FileManager.caricaGerarchia();
	    Geografia geografia = FileManager.caricaGeografia();

	    if (utenza == null || gerarchia == null || geografia == null) {
	        // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
	        gerarchia = DefaultInitializer.defaultTree();
	        utenza = DefaultInitializer.defaultAccess();
	        geografia = DefaultInitializer.defaultWorld();
	    } else {
	        System.out.println("Lettura da file: " + FileManager.getUtenzaFile() 
	        + ", " + FileManager.getGerarchiaFile() + ", " + FileManager.getGeografiaFile());
	    }

	    int accesso;
	    do {
	        accesso = menuAccesso.scegli();
	        switch (accesso) {
	            case 1:
	            	if (InputDati.yesOrNo("Vuoi essere un fruitore? ")) {
	                    System.out.print(geografia.toString());
		            	registrazioneF(utenza, geografia);
	            	}

	            	else
	            		registrazioneC(utenza);
	                break;

	            case 2:
	            	if (InputDati.yesOrNo("Sei un fruitore?"))
	            		accesso = login(utenza, accesso, Fruitore.TIPOUTENTE);
	            	else
	            		accesso = login(utenza, accesso, Configuratore.TIPOUTENTE);
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
	                    creaComprensorio(geografia);
	                    break;

	                case 2:
	                    ArrayList<Nodo> foglieAttuali = new ArrayList<>();
	                    Nodo root = creaRadice(gerarchia);
	                    creaNodiFiglio(root, gerarchia, root, foglieAttuali);
	                    gerarchia.addAlbero(root);
	                    creaFattoriConversione(gerarchia, foglieAttuali);
	                    break;

	                case 3:
	                    System.out.println(geografia.toString());
	                    break;

	                case 4:
	                	String ger = GestioneGerarchia.toString(gerarchia.getAlberi());
	                    System.out.println(ger);
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
	    FileManager.salvaSuFile(utenza);
	    FileManager.salvaSuFile(geografia);
	}


	/**
	 * Metodo per registrare un nuovo utente.
	 * 
	 * @param utenza   L'oggetto Utenza utilizzato per registrare il nuovo utente.
	 */
	private static void registrazioneC(Utenza utenza) {
	    Configuratore configuratore = new Configuratore();
	    String id = configuratore.getID();
	    String psswd = configuratore.getPSSW();
	    System.out.println("ID di default: " + id);
	    System.out.println("Password di default: " + psswd);

	    Credenziali credenziali = new Credenziali(id, psswd);
	    configuratore.setCredenziali(credenziali);
	    configuratore.setIsDefinitivo(false);
	    utenza.addUtente(configuratore);
	}
	/**
	 * Metodo per registrare un nuovo utente.
	 * 
	 * @param utenza   L'oggetto Utenza utilizzato per registrare il nuovo utente.
	 */
	private static void registrazioneF(Utenza utenza, Geografia geografia) {
		
		String c;
		do {
			c = InputDati.leggiStringaNonVuota("  Comprensorio: ");
		} while (!geografia.verificaEsistenzaComprensorio(c));
		
	    Credenziali credenziali = primoAccesso(utenza);
	    String indirizzo = InputDati.leggiStringaNonVuota("  Indirizzo e-mail: ");
	    Comprensorio comprensorio = geografia.cercaComprensorio(c);

	    Fruitore fruitore = new Fruitore(comprensorio, credenziali, indirizzo);
	    utenza.addUtente(fruitore);
	}
	/**
	 * Metodo per inserire le credenziali di registrazione.
	 * 
	 * @param utenza          L'oggetto Utenza utilizzato per verificare l'esistenza dell'ID.
	 * @return                Le credenziali inserite dall'utente.
	 */
	private static Credenziali primoAccesso(Utenza utenza) {
	    String ID;
	    do {
	        ID = InputDati.leggiStringaNonVuota("  ID: ");
	        if (utenza.verificaEsistenzaID(ID)) System.out.println(" ! ID già utilizzato ! ");
	    } while (utenza.verificaEsistenzaID(ID));

	    String PSSW = InputDati.leggiStringaNonVuota("  Password: ");
	    return new Credenziali(ID, PSSW);
	}
	
	/**
	 * Metodo per il login.
	 * 
	 * @param utenza  		  L'oggetto Utenza utilizzato per gestire l'accesso.
	 * @param accesso         L'accesso corrente.
	 * @return                L'accesso aggiornato.
	 */
	private static int login(Utenza utenza, int accesso, char type) {
	    for (int i = 0; i < NUM_MAX_TENTATIVI; i++) {
	        System.out.println("Inserisci dati di login: ");
	        String ID = InputDati.leggiStringaNonVuota("  ID: ");
	        String PSSW = InputDati.leggiStringaNonVuota("  Password: ");
	        accesso = autenticazione(utenza, ID, PSSW, type);
	        if (accesso != 0) {
	            break;
	        }
	    }
	    return accesso;
	}
	/**
	 * Metodo per effettuare il login.
	 * 
	 * @param utenza 	      L'oggetto Utenza utilizzato per verificare le credenziali.
	 * @param ID              L'ID inserito.
	 * @param PSSW            La password inserita.
	 * @return                Il risultato del login.
	 */
	private static int autenticazione(Utenza utenza, String ID, String PSSW, char type) {
		Utente utente;
		if (type == Configuratore.TIPOUTENTE)
			utente = utenza.autenticazioneConfiguratore(ID, PSSW);
		else
			utente = utenza.autenticazioneFruitore(ID, PSSW);
		
	    if (utente == null) {
	    	if (type == Configuratore.TIPOUTENTE)
	    		System.out.println(" ! Non esiste alcun configuratore con queste credenziali !");
	    	else
	    		System.out.println(" ! Non esiste alcun fruitore con queste credenziali !");
	        return 1;
	    } else if (!utente.getCredenziali().isDefinitive() && type == Configuratore.TIPOUTENTE) {
	    	System.out.println("Scegli nuove credenziali: ");
	        Credenziali credenzialiRegistrazione = primoAccesso(utenza);
	        utente.setCredenziali(credenzialiRegistrazione);
	        utente.setIsDefinitivo(true);
	        return 2;
	    } else {
	        System.out.println("-> Utente riconosciuto");
	        return 2;
	    }
	}


	/**
	 * Metodo per aggiungere un comprensorio.
	 * 
	 * @param gestioneUtenza  L'oggetto GestioneUtenza utilizzato per aggiungere il comprensorio.
	 */
	private static void creaComprensorio(Geografia geografia) {
		
		String nome;
		do {
			nome = InputDati.leggiStringaNonVuota("Nome: ");
		} while (geografia.verificaEsistenzaComprensorio(nome));
		
	    Comprensorio comprensorio = new Comprensorio(nome);
	    System.out.println("Inserisci comprensorio (Exit per uscire) ");
	    String comune;

	    do {
	        comune = InputDati.leggiStringaNonVuota("  comune -> ");
	        comprensorio.addComune(comune);
	    } while (!comune.equalsIgnoreCase("Exit"));

	    int size = comprensorio.getComprensorio().size();
	    comprensorio.getComprensorio().remove(size - 1);

	    geografia.addComprensorio(comprensorio);
	}

	
	/**
	 * Metodo per creare la radice dell'albero.
	 * 
	 * @param gerarchia L'oggetto Gerarchia utilizzato per verificare l'esistenza del nome radice.
	 * @return Il nodo radice creato.
	 */
	private static Nodo creaRadice(Gerarchia gerarchia) {
	    String radice;
	    do {
	        radice = InputDati.leggiStringaNonVuota("Nome radice -> ");
	    } while (GestioneGerarchia.verificaEsistenzaNomeRadice(radice, gerarchia.getAlberi()));

	    String campo = InputDati.leggiStringaNonVuota("Campo -> ");
	    Nodo root = new Nodo(radice, true, campo);

	    creaValoriDominio(root);

	    return root;
	}
	/**
	 * Metodo per aggiungere i valori del dominio a un nodo.
	 * 
	 * @param nodo Il nodo a cui aggiungere i valori del dominio.
	 */
	private static void creaValoriDominio(Nodo nodo) {
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
	        System.out.println("\n" + numFigli + "' figlio (di " + nodoParent.getNome() 
	        + " [" +nodoParent.getDominio(numFigli)+ "]): ");
      
	        String nome;
	        do {
	            nome = InputDati.leggiStringaNonVuota("Nome -> ");
	        } while (GestioneGerarchia.verificaEsistenzaNomeNonRadice(nome, radice));

	        boolean isFoglia = InputDati.yesOrNo("È foglia? ");
	        Nodo nodoChild;
	        if (isFoglia) {
	            nodoChild = new Nodo(nome);
	            foglieAttuali.add(nodoChild);
	        } else {
	            nodoChild = creaNonFoglia(nome);
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
	private static Nodo creaNonFoglia(String nome) {
	    String campo = InputDati.leggiStringaNonVuota("Campo -> ");
	    Nodo nodoChild = new Nodo(nome, false, campo);

	    creaValoriDominio(nodoChild);

	    return nodoChild;
	}

	
	/**
	 * Metodo per inserire i fattori di conversione tra nodi.
	 * 
	 * @param gerarchia       L'oggetto Gerarchia utilizzato per gestire la struttura gerarchica.
	 * @param foglieAttuali  La lista delle foglie attuali.
	 * @throws Exception     Eccezione in caso di problemi durante l'inserimento.
	 */
	private static void creaFattoriConversione(Gerarchia gerarchia, ArrayList<Nodo> foglieAttuali) {
	    System.out.println("\nInserimento fattori di conversione:");
	    do {
	        Nodo nodo1 = chiediFoglia("Foglia 1:", gerarchia);
	        Nodo nodo2 = chiediFoglia("Foglia 2:", gerarchia);

	        double fattoreDiConversione = chiediFattoreConversione(gerarchia);

	        boolean condizione = !GestioneGerarchia.checkFoglia(nodo1, foglieAttuali) 
	        		&& !GestioneGerarchia.checkFoglia(nodo2, foglieAttuali);
	        if (!condizione) {
	            gerarchia.addFattoreConversione(nodo1, nodo2, fattoreDiConversione);
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
	private static Nodo chiediFoglia(String messaggio, Gerarchia gerarchia) {
	    Nodo nodo;
	    do {
	        System.out.println(messaggio);
	        String foglia = InputDati.leggiStringaNonVuota("  Nome -> ");
	        String radice = InputDati.leggiStringaNonVuota("  Radice -> ");
	        nodo = GestioneGerarchia.visualizzaNodo(foglia, radice, gerarchia.getAlberi());
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
	 * Metodo per stampare i fattori di conversione.
	 * 
	 * @param gerarchia  L'oggetto Gerarchia utilizzato per visualizzare i fattori di conversione.
	 */
	private static void stampaFattori(Gerarchia gerarchia) {
	    String foglia = InputDati.leggiStringaNonVuota("Inserisci nome foglia: ");
	    String radice = InputDati.leggiStringaNonVuota("Inserisci radice della gerarchia della foglia: ");
	    Nodo nodo = GestioneGerarchia.visualizzaNodo(foglia, radice, gerarchia.getAlberi());
	    if (nodo == null)
	        System.out.println("  Non è stata trovata nessuna corrispondenza");
	    else
	        System.out.println(nodo.toStringF());
	}

	
}