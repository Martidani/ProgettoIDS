package it.unibs.ids.progetto;

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
    
	private static final String DEFAULT_NAME_COMMUNITY_0 = "C0";
    private static final String DEFAULT_COMMUNITY_1 = "Comune1";
    private static final String DEFAULT_COMMUNITY_2 = "Comune2";
    private static final String DEFAULT_COMMUNITY_3 = "Comune3";
    
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin";
    
    private Gerarchia gerarchia;
    private Utenza utenza;
    private Geografia geografia;
    
    /**
     * Costruttore che inizializza gli oggetti di default.
     */
    public DefaultInitializer() {
        this.utenza = defaultAccess();
        this.gerarchia = defaultTree();
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
    public Gerarchia defaultTree() {
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
            nodo11.addFattoreConversione(nodo12, 2);
            nodo12.addFattoreConversione(nodo11, 0.5);
            Albero albero = new Albero(nodo1);
            
            albero.setUtente(utenza.autenticazioneConfiguratore(DEFAULT_USERNAME, DEFAULT_PASSWORD));
            gerarchia.addAlbero(albero);
            FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return gerarchia;
    }

    /**
     * Crea e restituisce un'utenza di default.
     * 
     * @return L'utenza di default
     */
    public Utenza defaultAccess() {
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
    private Comprensorio defaultComprensorio() {
        Comprensorio comprensorio = new Comprensorio(DEFAULT_NAME_COMMUNITY_0);
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
    public Geografia defaultWorld() {
        Geografia geografia = new Geografia();
        geografia.addComprensorio(defaultComprensorio());
        return geografia;
    }
}
