package it.unibs.ids.progetto.news;
import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.Configuratore;
import it.unibs.ids.progetto.Credenziali;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.GestioneUtenza;
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
    private GestioneUtenza gestioneUtenza;
    
    /**
     * Costruttore che inizializza gli oggetti di default.
     */
    public DefaultInitializer() {
        this.gerarchia = defaultTree();
        this.gestioneUtenza = defaultAccess();
    }

    /**
     * Restituisce l'albero gerarchico predefinito.
     * 
     * @return L'albero gerarchico predefinito
     */
    public Gerarchia getGerarchia() {
        return gerarchia;
    }

    /**
     * Restituisce la gestione utenza predefinita.
     * 
     * @return La gestione utenza predefinita
     */
    public GestioneUtenza getGestioneUtenza() {
        return gestioneUtenza;
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
            gerarchia.aggiungiFattoreConversione(nodo11, nodo12, 2);
            gerarchia.addAlberi(nodo1);
            gerarchia.addTransitivoFattoreConversione();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return gerarchia;
    }

    /**
     * Crea e restituisce una gestione utenza di default.
     * 
     * @return La gestione utenza di default
     */
    public static GestioneUtenza defaultAccess() {
        GestioneUtenza gestioneUtenza = new GestioneUtenza();

        // Creazione delle credenziali di default per l'utente admin
        Credenziali cred = new Credenziali(DEFAULT_USERNAME, DEFAULT_PASSWORD);
        cred.setDefinitive(true);
        Configuratore utente = new Configuratore(cred);
        gestioneUtenza.addUtente(utente);

        gestioneUtenza.addComprensorio(defaultComprensorio());
        
        return gestioneUtenza;
    }

    /**
     * Crea e restituisce un comprensorio di default.
     * 
     * @return Il comprensorio di default
     */
    public static Comprensorio defaultComprensorio() {
        Comprensorio comprensorio = new Comprensorio();
        comprensorio.addComune(DEFAULT_COMMUNITY_1);
        comprensorio.addComune(DEFAULT_COMMUNITY_2);
        comprensorio.addComune(DEFAULT_COMMUNITY_3);
        return comprensorio;
    }


}