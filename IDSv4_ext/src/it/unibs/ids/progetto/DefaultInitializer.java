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
    
    private static final String ROOT_NAME_0 = "Lezioni di musica";
    private static final String ROOT_FIELD_0 = "Tipo";
    private static final String[] ROOT_DOMAIN_0 = {"Teoria", "Pratica"};
    private static final String CHILD_NAME_00 = "Lezioni di storia della musica";
    private static final String CHILD_NAME_01 = "Lezioni di chitarra";
    public static final double FACTOR_VAL_00 = 1.5;
    
    private static final String ROOT_NAME_1 = "Allenamento sportivo";
    private static final String ROOT_FIELD_1 = "Tipologia";
    private static final String[] ROOT_DOMAIN_1 = {"Individuale", "Collettivo"};
    private static final String CHILD_NAME_10 = "Allenamento di tennis";
    private static final String CHILD_NAME_11 = "Allenamento di calcio";
    public static final double FACTOR_VAL_10 = 1.5;
    public static final double FACTOR_VAL_11 = 1.5;
    
    private static final String ROOT_NAME_2 = "Ripetizioni scolastiche";
    private static final String ROOT_FIELD_2 = "Livello";
    private static final String[] ROOT_DOMAIN_2 = {"Scuola media", "Scuola superiore",};
    private static final String CHILD_NAME_20 = "Ripetizioni di matematica base";
    private static final String CHILD_NAME_21 = "Ripetizioni di matematica avanzata";
    public static final double FACTOR_VAL_20 = 1.2;
    public static final double FACTOR_VAL_21 = 2;
    
	private static final String DEFAULT_NAME_COMMUNITY_0 = "Roma";
    private static final String DEFAULT_COMMUNITY_01 = "Fiumicino";
    private static final String DEFAULT_COMMUNITY_02 = "Tivoli";
    private static final String DEFAULT_COMMUNITY_03 = "Frascati";
	private static final String DEFAULT_NAME_COMMUNITY_1 = "Milano";
    private static final String DEFAULT_COMMUNITY_11 = "Rho";
    private static final String DEFAULT_COMMUNITY_12 = "Cinisello Balsamo";
    private static final String DEFAULT_COMMUNITY_13 = "Sesto San Giovanni";
	private static final String DEFAULT_NAME_COMMUNITY_2 = "Napoli";
    private static final String DEFAULT_COMMUNITY_21 = "Pozzuoli";
    private static final String DEFAULT_COMMUNITY_22 = "Casoria";
    private static final String DEFAULT_COMMUNITY_23 = "Portici";
    
    private static final String DEFAULT_C1_USERNAME = "federico";
    private static final String DEFAULT_C1_PASSWORD = "sabbadini";
    private static final String DEFAULT_C2_USERNAME = "daniele";
    private static final String DEFAULT_C2_PASSWORD = "martinelli";
    
    private static final String DEFAULT_F1_USERNAME = "user1";
    private static final String DEFAULT_F1_PASSWORD = "user1";
    private static final String DEFAULT_F1_EMAIL = "user1@unibs.it";
    private static final String DEFAULT_F2_USERNAME = "user2";
    private static final String DEFAULT_F2_PASSWORD = "user2";
    private static final String DEFAULT_F2_EMAIL = "user2@unibs.it";
    private static final String DEFAULT_F3_USERNAME = "user3";
    private static final String DEFAULT_F3_PASSWORD = "user3";
    private static final String DEFAULT_F3_EMAIL = "user3@unibs.it";
    
    
    private Gerarchia gerarchia;
    private Utenza utenza;
    private Geografia geografia;
    private Commercio commercio;
    
    /**
     * Costruttore che inizializza gli oggetti di default.
     */
    private DefaultInitializer() {
        this.geografia = defaultWorld();
        this.utenza = defaultAccess();
        this.gerarchia = defaultTree();
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

    
        try {
        // Creazione del nodo radice
        NotLeaf nodo0 = new NotLeaf(ROOT_NAME_0, null, ROOT_FIELD_0);
        for (String domainValue : ROOT_DOMAIN_0) {
            nodo0.addElementiDominio(domainValue);
        }

        // Creazione dei nodi figli
        Leaf nodo00 = new Leaf(CHILD_NAME_00, ROOT_NAME_0);
        Leaf nodo01 = new Leaf(CHILD_NAME_01, ROOT_NAME_0);

            nodo0.addChild(nodo00);
            nodo0.addChild(nodo01);

            // Aggiunta dei nodi all'albero e definizione dei fattori di conversione
            nodo00.addFattoreConversione(nodo01, FACTOR_VAL_00);
            nodo01.addFattoreConversione(nodo00, 1/FACTOR_VAL_00);
            
            Albero albero0 = new Albero(nodo0);
          
            albero0.setUtente(utenza.autenticazioneConfiguratore(DEFAULT_C1_USERNAME, DEFAULT_C1_PASSWORD));
            gerarchia.addAlbero(albero0);
            FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
            
            
        // Creazione del nodo radice
        NotLeaf nodo1 = new NotLeaf(ROOT_NAME_1, null, ROOT_FIELD_1);
        for (String domainValue : ROOT_DOMAIN_1) {
            nodo1.addElementiDominio(domainValue);
        }

        // Creazione dei nodi figli
        Leaf nodo10 = new Leaf(CHILD_NAME_10, ROOT_NAME_1);
        Leaf nodo11 = new Leaf(CHILD_NAME_11, ROOT_NAME_1);
        
            nodo1.addChild(nodo10);
            nodo1.addChild(nodo11);

            // Aggiunta dei nodi all'albero e definizione dei fattori di conversione
            nodo10.addFattoreConversione(nodo11, FACTOR_VAL_10);
            nodo11.addFattoreConversione(nodo10, 1/FACTOR_VAL_10);
            
            nodo01.addFattoreConversione(nodo10, FACTOR_VAL_11);
            nodo10.addFattoreConversione(nodo01, 1/FACTOR_VAL_11);
            Albero albero1 = new Albero(nodo1);
          
            albero1.setUtente(utenza.autenticazioneConfiguratore(DEFAULT_C1_USERNAME, DEFAULT_C1_PASSWORD));
            gerarchia.addAlbero(albero1);
            FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
            
            
        // Creazione del nodo radice
        NotLeaf nodo2 = new NotLeaf(ROOT_NAME_2, null, ROOT_FIELD_2);
        for (String domainValue : ROOT_DOMAIN_2) {
            nodo2.addElementiDominio(domainValue);
        }


        // Creazione dei nodi figli
        Leaf nodo20 = new Leaf(CHILD_NAME_20, ROOT_NAME_2);
        Leaf nodo21 = new Leaf(CHILD_NAME_21, ROOT_NAME_2);
        
            nodo2.addChild(nodo20);
            nodo2.addChild(nodo21);

            // Aggiunta dei nodi all'albero e definizione dei fattori di conversione
            nodo20.addFattoreConversione(nodo21, FACTOR_VAL_20);
            nodo21.addFattoreConversione(nodo20, 1/FACTOR_VAL_20);
            
            nodo20.addFattoreConversione(nodo00, FACTOR_VAL_21);
            nodo00.addFattoreConversione(nodo20, 1/FACTOR_VAL_21);
            Albero albero2 = new Albero(nodo2);
          
            albero2.setUtente(utenza.autenticazioneConfiguratore(DEFAULT_C2_USERNAME, DEFAULT_C2_PASSWORD));
            gerarchia.addAlbero(albero2); 
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
        Credenziali credC1= new Credenziali(DEFAULT_C1_USERNAME, DEFAULT_C1_PASSWORD);
        credC1.setDefinitive(true);
        Configuratore configuratore1 = new Configuratore(credC1);
        Credenziali credC2= new Credenziali(DEFAULT_C2_USERNAME, DEFAULT_C2_PASSWORD);
        credC2.setDefinitive(true);
        Configuratore configuratore2 = new Configuratore(credC2);
        utenza.addUtente(configuratore1);
        utenza.addUtente(configuratore2);

        
        // Creazione delle credenziali di default per l'utente fruitore user
        Credenziali credF1 = new Credenziali(DEFAULT_F1_USERNAME, DEFAULT_F1_PASSWORD);
        Fruitore fruitore1 = 
        		new Fruitore(geografia.getComprensori().get(0), credF1, 
        				new MailAddress(DEFAULT_F1_EMAIL));
        Credenziali credF2 = new Credenziali(DEFAULT_F2_USERNAME, DEFAULT_F2_PASSWORD);
        Fruitore fruitore2 = 
        		new Fruitore(geografia.getComprensori().get(1), credF2, 
        				new MailAddress(DEFAULT_F2_EMAIL));
        Credenziali credF3 = new Credenziali(DEFAULT_F3_USERNAME, DEFAULT_F3_PASSWORD);
        Fruitore fruitore3 = 
        		new Fruitore(geografia.getComprensori().get(2), credF3, 
        				new MailAddress(DEFAULT_F3_EMAIL));
        utenza.addUtente(fruitore1);
        utenza.addUtente(fruitore2);
        utenza.addUtente(fruitore3);
        
        return utenza;
    }

    
    /**
     * Crea e restituisce una geografia di default.
     * 
     * @return La geografia di default
     */
    public Geografia defaultWorld() {
        Geografia geografia = new Geografia();
        
        Comprensorio comprensorio1 = new Comprensorio(DEFAULT_NAME_COMMUNITY_0);
        comprensorio1.addComune(DEFAULT_COMMUNITY_01);
        comprensorio1.addComune(DEFAULT_COMMUNITY_02);
        comprensorio1.addComune(DEFAULT_COMMUNITY_03);
        
        Comprensorio comprensorio2 = new Comprensorio(DEFAULT_NAME_COMMUNITY_1);
        comprensorio2.addComune(DEFAULT_COMMUNITY_11);
        comprensorio2.addComune(DEFAULT_COMMUNITY_12);
        comprensorio2.addComune(DEFAULT_COMMUNITY_13);
        
        Comprensorio comprensorio3 = new Comprensorio(DEFAULT_NAME_COMMUNITY_2);
        comprensorio3.addComune(DEFAULT_COMMUNITY_21);
        comprensorio3.addComune(DEFAULT_COMMUNITY_22);
        comprensorio3.addComune(DEFAULT_COMMUNITY_23);
        
        geografia.addComprensorio(comprensorio1);
        geografia.addComprensorio(comprensorio2);
        geografia.addComprensorio(comprensorio3);

        return geografia;
    }
    
    private Commercio defaultCommercio()  {
        

    	Commercio commercio = new Commercio();
    	/*
    	InsiemeAperto insiemeAperto1 = new InsiemeAperto(this.geografia.getComprensori().get(0));
    	InsiemeAperto insiemeAperto2 = new InsiemeAperto(this.geografia.getComprensori().get(1));
    	InsiemeAperto insiemeAperto3 = new InsiemeAperto(this.geografia.getComprensori().get(2));

    	Richiesta r1 = new Richiesta(gerarchia.getFoglie().get(0), (int) FACTOR_VAL);
    	Offerta o1 = new Offerta(gerarchia.getFoglie().get(1));
		PropostaAperta proposta1 = new PropostaAperta(r1, o1, commercio.numProposte(),(Fruitore)utenza.getUtenti().get(1));
	
		Richiesta r2 = new Richiesta(gerarchia.getFoglie().get(1), (int) FACTOR_VAL*2);
		Offerta o2 = new Offerta(gerarchia.getFoglie().get(0));
		PropostaAperta proposta2 = new PropostaAperta(r2, o2, commercio.numProposte(),(Fruitore)utenza.getUtenti().get(1));
		
		insiemeAperto1.addPropostaAperta(proposta1);
		insiemeAperto1.addPropostaAperta(proposta2);
		
		commercio.addInsiemiAperti(insiemeAperto1);
		commercio.metodo(insiemeAperto1);
    	
    	
    	*/
    	return commercio;
    }
}
