package it.unibs.ids.progetto;

import it.unibs.ids.progetto.news.Commercio;
import it.unibs.ids.progetto.news.InsiemeAperto;
import it.unibs.ids.progetto.news.MailAddress;
import it.unibs.ids.progetto.news.Offerta;
import it.unibs.ids.progetto.news.PropostaAperta;
import it.unibs.ids.progetto.news.Richiesta;

/**
 * Classe per l'inizializzazione predefinita del sistema.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class DefaultInitializer {
    
    private static final String ROOT_NAME = "system";
    private static final String ROOT_FIELD = "field";
    private static final String[] ROOT_DOMAIN = {"rootchildM", "rootchildF",};
    private static final String CHILD2_NAME = "rootchild2";
    private static final String CHILD1_NAME = "rootchild1";
    
	private static final String DEFAULT_NAME_COMMUNITY_0 = "C0";
    private static final String DEFAULT_COMMUNITY_1 = "Comune1";
    private static final String DEFAULT_COMMUNITY_2 = "Comune2";
    private static final String DEFAULT_COMMUNITY_3 = "Comune3";
    
    private static final String DEFAULT_CUSERNAME = "admin";
    private static final String DEFAULT_CPASSWORD = "admin";
    
    private static final String DEFAULT_FUSERNAME = "user";
    private static final String DEFAULT_FPASSWORD = "user";
    private static final String DEFAULT_FEMAIL = "user@unibs.it";
    
    public static final double FACTOR_VAL = 2;
    
    private Gerarchia gerarchia;
    private Utenza utenza;
    private Geografia geografia;
    private Commercio commercio;
    
    /**
     * Costruttore che inizializza gli oggetti di default.
     */
    private DefaultInitializer() {
        this.utenza = defaultAccess();
        this.gerarchia = defaultTree();
        this.geografia = defaultWorld();
        this.commercio = defaultCommercio();
    }

    private static DefaultInitializer defaultInitializer;
    //singleton
    public static DefaultInitializer getDefaultInitializer() {
    	if (defaultInitializer == null)
    		defaultInitializer= new DefaultInitializer(); 
    	return defaultInitializer;
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
     * Restituisce il commercio predefinito.
     * 
     * @return Il commercio predefinito
     */
    public Commercio getCommercio() {
        return this.commercio;
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
        NotLeaf nodo1 = new NotLeaf(ROOT_NAME, null, ROOT_FIELD);
        for (String domainValue : ROOT_DOMAIN) {
            nodo1.addElementiDominio(domainValue);
        }

        // Creazione dei nodi figli
        Leaf nodo11 = new Leaf(CHILD1_NAME, ROOT_NAME);
        Leaf nodo12 = new Leaf(CHILD2_NAME, ROOT_NAME);
        try {
            nodo1.addChild(nodo11);
            nodo1.addChild(nodo12);

            // Aggiunta dei nodi all'albero e definizione dei fattori di conversione
            nodo11.addFattoreConversione(nodo12, FACTOR_VAL);
            nodo12.addFattoreConversione(nodo11, 1/FACTOR_VAL);
            Albero albero = new Albero(nodo1);
            
            albero.setUtente(utenza.autenticazioneConfiguratore(DEFAULT_CUSERNAME, DEFAULT_CPASSWORD));
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

        // Creazione delle credenziali di default per l'utente configuratore admin
        Credenziali credC= new Credenziali(DEFAULT_CUSERNAME, DEFAULT_CPASSWORD);
        credC.setDefinitive(true);
        Configuratore configuratore = new Configuratore(credC);
        utenza.addUtente(configuratore);
        
        // Creazione delle credenziali di default per l'utente fruitore user
        Credenziali credF = new Credenziali(DEFAULT_FUSERNAME, DEFAULT_FPASSWORD);
        Fruitore fruitore = new Fruitore(this.defaultComprensorio(), credF, new MailAddress(DEFAULT_FEMAIL));
        utenza.addUtente(fruitore);
        
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
    
    private Commercio defaultCommercio()  {
        

    	Commercio commercio = new Commercio();
    	
    	InsiemeAperto insiemeAperto = new InsiemeAperto(this.defaultComprensorio());
    	
			
    	Richiesta r1 = new Richiesta(gerarchia.getFoglie().get(0), (int) FACTOR_VAL);
    	Offerta o1 = new Offerta(gerarchia.getFoglie().get(1));
		PropostaAperta proposta1 = new PropostaAperta(r1, o1, commercio.numProposte(),(Fruitore)utenza.getUtenti().get(1));
	
		Richiesta r2 = new Richiesta(gerarchia.getFoglie().get(1), (int) FACTOR_VAL*2);
		Offerta o2 = new Offerta(gerarchia.getFoglie().get(0));
		PropostaAperta proposta2 = new PropostaAperta(r2, o2, commercio.numProposte(),(Fruitore)utenza.getUtenti().get(1));
		
		insiemeAperto.addPropostaAperta(proposta1);
		insiemeAperto.addPropostaAperta(proposta2);
		
		commercio.addInsiemiAperti(insiemeAperto);
		commercio.metodo(insiemeAperto);
    	
    	
    	
    	return commercio;
    }
}
