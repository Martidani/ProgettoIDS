package it.unibs.ids.progetto.news;
import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.Configuratore;
import it.unibs.ids.progetto.Credenziali;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Utenza;
import it.unibs.ids.progetto.Nodo;

/**
 * Classe per l'inizializzazione predefinita del sistema.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class DefaultInitializer {
    

	private static final String ROOT_NAME = "system";
    private static final String ROOT_FIELD = "field";
    private static final String[] ROOT_DOMAIN = {"rootchildM", "rootchildF"};
    private static final String CHILD2_NAME = "rootchild2";
	private static final String CHILD1_NAME = "rootchild1";
	
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin";
    
    private static final String DEFAULT_COMMUNITY_1 = "Comune1";
    private static final String DEFAULT_COMMUNITY_2 = "Comune2";
    private static final String DEFAULT_COMMUNITY_3 = "Comune3";


    private Gerarchia gerarchia;
    private Utenza utenza;
    private Geografia geografia;
    
    /**
     * Costruttore che inizializza gli oggetti di default.
     */
    public DefaultInitializer() {
        this.gerarchia = defaultTree();
        this.utenza = defaultAccess();
        this.geografia = defaultWorld();
    }



	/**
     * Restituisce l'albero gerarchico predefinito.
     * 
     * @return L'albero gerarchico predefinito
     */
    public Gerarchia getGerarchia() {
        return this.gerarchia;
    }

    /**
     * Restituisce la gestione utenza predefinita.
     * 
     * @return La gestione utenza predefinita
     */
    public Utenza getUtenza() {
        return this.utenza;
    }


    /**
     * Restituisce la geografia predefinita.
     * 
     * @return La geografia predefinita
     */
    public Geografia getGeografia() {
		return this.geografia;
	}

	/**
     * Crea e restituisce un albero gerarchico di default.
     * 
     * @return L'albero gerarchico di default
     */
    public static Gerarchia defaultTree() {
        Gerarchia gerarchia = new Gerarchia();

        // Creazione del nodo radice
        Nodo nodo1 = new Nodo(ROOT_NAME, true, ROOT_FIELD);
        for (String domainValue : ROOT_DOMAIN) {
            nodo1.addElementiDominio(domainValue);
        }

        // Creazione dei nodi figli
        Nodo nodo11 = new Nodo(CHILD1_NAME);
        Nodo nodo12 = new Nodo(CHILD2_NAME);
        try {
            nodo1.addChild(nodo11);
            nodo1.addChild(nodo12);

            // Aggiunta dei nodi all'albero e definizione dei fattori di conversione
            gerarchia.addFattoreConversione(nodo11, nodo12, 2);
            gerarchia.addAlbero(nodo1);
            gerarchia.addTransitivoFattoreConversione();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return gerarchia;
    }

    /**
     * Crea e restituisce un' utenza di default.
     * 
     * @return L' utenza di default
     */
    public static Utenza defaultAccess() {
        Utenza utenza = new Utenza();

        // Creazione delle credenziali di default per l'utente admin
        Credenziali cred = new Credenziali(DEFAULT_USERNAME, DEFAULT_PASSWORD);
        cred.setDefinitive(true);
        Configuratore utente = new Configuratore(cred);
        utenza.addUtente(utente);
        
        return utenza;
    }

    /**
     * Crea e restituisce un comprensorio di default.
     * 
     * @return Il comprensorio di default
     */
    private static Comprensorio defaultComprensorio() {
        Comprensorio comprensorio = new Comprensorio();
        comprensorio.addComune(DEFAULT_COMMUNITY_1);
        comprensorio.addComune(DEFAULT_COMMUNITY_2);
        comprensorio.addComune(DEFAULT_COMMUNITY_3);
        return comprensorio;
    }
    
    /**
     * Crea e restituisce una geografia di default.
     * 
     * @return La geografia di default
     */
    public static Geografia defaultWorld() {

    	Geografia geografia = new Geografia();
    	
    	geografia.addComprensorio(defaultComprensorio());
		return geografia;
	}


}