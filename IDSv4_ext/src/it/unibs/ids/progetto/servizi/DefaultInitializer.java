package it.unibs.ids.progetto.servizi;

import it.unibs.ids.progetto.Albero;
import it.unibs.ids.progetto.Commercio;
import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.Configuratore;
import it.unibs.ids.progetto.Credenziali;
import it.unibs.ids.progetto.FattoriDiConversione;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.InsiemeAperto;
import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.NotLeaf;
import it.unibs.ids.progetto.Offerta;
import it.unibs.ids.progetto.PropostaAperta;
import it.unibs.ids.progetto.Richiesta;
import it.unibs.ids.progetto.Utenza;

/**
 * Classe per l'inizializzazione predefinita del sistema.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class DefaultInitializer {
    
    public static final String ROOT_NAME_0 = "Lezioni di musica";
    public static final String ROOT_FIELD_0 = "Tipo";
    public static final String[] ROOT_DOMAIN_0 = {"Teoria", "Pratica"};
    public static final String CHILD_NAME_00 = "Lezioni di storia della musica";
    public static final String CHILD_NAME_01 = "Lezioni di chitarra";
    public static final double FACTOR_VAL_00 = 1.5;
    
    public static final String ROOT_NAME_1 = "Allenamento sportivo";
    public static final String ROOT_FIELD_1 = "Tipologia";
    public static final String[] ROOT_DOMAIN_1 = {"Individuale", "Collettivo"};
    public static final String CHILD_NAME_10 = "Allenamento di tennis";
    public static final String CHILD_NAME_11 = "Allenamento di calcio";
    public static final double FACTOR_VAL_10 = 1.5;
    public static final double FACTOR_VAL_11 = 1.5;
    
    public static final String ROOT_NAME_2 = "Ripetizioni scolastiche";
    public static final String ROOT_FIELD_2 = "Livello";
    public static final String[] ROOT_DOMAIN_2 = {"Scuola media", "Scuola superiore",};
    public static final String CHILD_NAME_20 = "Ripetizioni di matematica base";
    public static final String CHILD_NAME_21 = "Ripetizioni di matematica avanzata";
    public static final double FACTOR_VAL_20 = 1.2;
    public static final double FACTOR_VAL_21 = 2;
    
    public static final String DEFAULT_NAME_COMMUNITY_0 = "Roma";
    public static final String DEFAULT_COMMUNITY_01 = "Fiumicino";
    public static final String DEFAULT_COMMUNITY_02 = "Tivoli";
    public static final String DEFAULT_COMMUNITY_03 = "Frascati";
    public static final String DEFAULT_NAME_COMMUNITY_1 = "Milano";
    public static final String DEFAULT_COMMUNITY_11 = "Rho";
    public static final String DEFAULT_COMMUNITY_12 = "Cinisello Balsamo";
    public static final String DEFAULT_COMMUNITY_13 = "Sesto San Giovanni";
    public static final String DEFAULT_NAME_COMMUNITY_2 = "Napoli";
    public static final String DEFAULT_COMMUNITY_21 = "Pozzuoli";
    public static final String DEFAULT_COMMUNITY_22 = "Casoria";
    public static final String DEFAULT_COMMUNITY_23 = "Portici";
    
    public static final String DEFAULT_C1_USERNAME = "federico";
    public static final String DEFAULT_C1_PASSWORD = "sabbadini";
    public static final String DEFAULT_C2_USERNAME = "daniele";
    public static final String DEFAULT_C2_PASSWORD = "martinelli";
    
    public static final String DEFAULT_F1_USERNAME = "user1";
    public static final String DEFAULT_F1_PASSWORD = "user1";
    public static final String DEFAULT_F1_EMAIL = "user1@unibs.it";
    public static final String DEFAULT_F2_USERNAME = "user2";
    public static final String DEFAULT_F2_PASSWORD = "user2";
    public static final String DEFAULT_F2_EMAIL = "user2@unibs.it";
    public static final String DEFAULT_F3_USERNAME = "user3";
    public static final String DEFAULT_F3_PASSWORD = "user3";
    public static final String DEFAULT_F3_EMAIL = "user3@unibs.it";
    
    public static final int COMMERCIO_VAL_00 = 4;
    public static final int COMMERCIO_VAL_01 = 6;
    public static final int COMMERCIO_VAL_02 = 9;
    public static final int COMMERCIO_VAL_03 = 4;
    public static final int COMMERCIO_VAL_04 = 16;
    public static final int COMMERCIO_VAL_05 = 12;
    public static final int COMMERCIO_VAL_06 = 2;
    public static final int COMMERCIO_VAL_07 = 10;
    public static final int COMMERCIO_VAL_10 = 4;
    public static final int COMMERCIO_VAL_11 = 7;
    public static final int COMMERCIO_VAL_12 = 9;
    public static final int COMMERCIO_VAL_13 = 4;
    public static final int COMMERCIO_VAL_14 = 4;
    public static final int COMMERCIO_VAL_15 = 12;
    public static final int COMMERCIO_VAL_16 = 18;
    public static final int COMMERCIO_VAL_17 = 4;
    
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

    private static volatile DefaultInitializer defaultInitializer;
    //singleton
    public static DefaultInitializer getDefaultInitializer() {
    	if (defaultInitializer == null) { 
    		synchronized (DefaultInitializer.class) {
    		if (defaultInitializer == null) {
    			defaultInitializer = new DefaultInitializer(); 
				}
    		}
    	}
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
    
    /**
     * Metodo per la creazione di default di un insieme di proposte (Aperte, chiuse,ritirate)
     * @return commercio di default
     */
    
    private Commercio defaultCommercio()  {
        

    	Commercio commercio = new Commercio();
    	
    	
    	Fruitore fruitore0 = (Fruitore)utenza.getUtenti().get(2);
    	commercio.setFruitoreDiSessione(fruitore0);
    	InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

		Richiesta r00 = new Richiesta(gerarchia.getFoglie().get(0), COMMERCIO_VAL_00);
		Offerta o00 = new Offerta(gerarchia.getFoglie().get(1));
		PropostaAperta proposta00 = new PropostaAperta(r00, o00, commercio.numProposte(),fruitore0);
		
		Richiesta r01 = new Richiesta(gerarchia.getFoglie().get(1), COMMERCIO_VAL_01);
		Offerta o01 = new Offerta(gerarchia.getFoglie().get(2));
		PropostaAperta proposta01 = new PropostaAperta(r01, o01, commercio.numProposte(),fruitore0);
	
		Richiesta r02 = new Richiesta(gerarchia.getFoglie().get(2), COMMERCIO_VAL_02);
		Offerta o02 = new Offerta(gerarchia.getFoglie().get(0));
		PropostaAperta proposta02 = new PropostaAperta(r02, o02, commercio.numProposte(),fruitore0);
		
		Richiesta r03 = new Richiesta(gerarchia.getFoglie().get(2), COMMERCIO_VAL_03);
		Offerta o03 = new Offerta(gerarchia.getFoglie().get(4));
		PropostaAperta proposta03 = new PropostaAperta(r03, o03, commercio.numProposte(),fruitore0);
		
		Richiesta r04 = new Richiesta(gerarchia.getFoglie().get(3), COMMERCIO_VAL_04);
		Offerta o04 = new Offerta(gerarchia.getFoglie().get(5));
		PropostaAperta proposta04 = new PropostaAperta(r04, o04, commercio.numProposte(),fruitore0);
	
		Richiesta r05 = new Richiesta(gerarchia.getFoglie().get(5), COMMERCIO_VAL_05);
		Offerta o05 = new Offerta(gerarchia.getFoglie().get(2));
		PropostaAperta proposta05 = new PropostaAperta(r05, o05, commercio.numProposte(),fruitore0);
		
		Richiesta r06 = new Richiesta(gerarchia.getFoglie().get(0), COMMERCIO_VAL_06);
		Offerta o06 = new Offerta(gerarchia.getFoglie().get(4));
		PropostaAperta proposta06 = new PropostaAperta(r06, o06, commercio.numProposte(),fruitore0);
		
		Richiesta r07 = new Richiesta(gerarchia.getFoglie().get(3), COMMERCIO_VAL_07);
		Offerta o07 = new Offerta(gerarchia.getFoglie().get(0));
		PropostaAperta proposta07 = new PropostaAperta(r07, o07, commercio.numProposte(),fruitore0);
		
		insiemeAperto0.addPropostaAperta(proposta00);		
		insiemeAperto0.addPropostaAperta(proposta01);
		insiemeAperto0.addPropostaAperta(proposta02);
		insiemeAperto0.addPropostaAperta(proposta03);		
		insiemeAperto0.addPropostaAperta(proposta04);
		insiemeAperto0.addPropostaAperta(proposta05);
		insiemeAperto0.addPropostaAperta(proposta06);
		insiemeAperto0.addPropostaAperta(proposta07);
		
		commercio.addInsiemiAperti(insiemeAperto0);
		commercio.cercaProposteChiudibili(insiemeAperto0);  
		
		
    	Fruitore fruitore1 = (Fruitore)utenza.getUtenti().get(3);
    	commercio.setFruitoreDiSessione(fruitore1);
    	InsiemeAperto insiemeAperto1 = new InsiemeAperto(fruitore1.getComprensorioAppartenenza());

    	Richiesta r10 = new Richiesta(gerarchia.getFoglie().get(3), COMMERCIO_VAL_10);
		Offerta o10 = new Offerta(gerarchia.getFoglie().get(1));
		PropostaAperta proposta10 = new PropostaAperta(r10, o10, commercio.numProposte(),fruitore1);
		
		Richiesta r11 = new Richiesta(gerarchia.getFoglie().get(4), COMMERCIO_VAL_11);
    	Offerta o11 = new Offerta(gerarchia.getFoglie().get(2));
		PropostaAperta proposta11 = new PropostaAperta(r11, o11, commercio.numProposte(),fruitore1);
	
		Richiesta r12 = new Richiesta(gerarchia.getFoglie().get(3), COMMERCIO_VAL_12);
		Offerta o12 = new Offerta(gerarchia.getFoglie().get(0));
		PropostaAperta proposta12 = new PropostaAperta(r12, o12, commercio.numProposte(),fruitore1);
		
		Richiesta r13 = new Richiesta(gerarchia.getFoglie().get(1), COMMERCIO_VAL_13);
		Offerta o13 = new Offerta(gerarchia.getFoglie().get(4));
		PropostaAperta proposta13 = new PropostaAperta(r13, o13, commercio.numProposte(),fruitore1);
		
		Richiesta r14 = new Richiesta(gerarchia.getFoglie().get(2), COMMERCIO_VAL_14);
    	Offerta o14 = new Offerta(gerarchia.getFoglie().get(5));
		PropostaAperta proposta14 = new PropostaAperta(r14, o14, commercio.numProposte(),fruitore1);
	
		Richiesta r15 = new Richiesta(gerarchia.getFoglie().get(5), COMMERCIO_VAL_15);
		Offerta o15 = new Offerta(gerarchia.getFoglie().get(2));
		PropostaAperta proposta15 = new PropostaAperta(r15, o15, commercio.numProposte(),fruitore1);
		
		Richiesta r16 = new Richiesta(gerarchia.getFoglie().get(3), COMMERCIO_VAL_16);
		Offerta o16 = new Offerta(gerarchia.getFoglie().get(4));
		PropostaAperta proposta16 = new PropostaAperta(r16, o16, commercio.numProposte(),fruitore1);
		
		Richiesta r17 = new Richiesta(gerarchia.getFoglie().get(1), COMMERCIO_VAL_17);
    	Offerta o17 = new Offerta(gerarchia.getFoglie().get(2));
		PropostaAperta proposta17 = new PropostaAperta(r17, o17, commercio.numProposte(),fruitore1);

		
		insiemeAperto1.addPropostaAperta(proposta10);		
		insiemeAperto1.addPropostaAperta(proposta11);
		insiemeAperto1.addPropostaAperta(proposta12);
		insiemeAperto1.addPropostaAperta(proposta13);		
		insiemeAperto1.addPropostaAperta(proposta14);
		insiemeAperto1.addPropostaAperta(proposta15);
		
		insiemeAperto1.addPropostaAperta(proposta16);		
		insiemeAperto1.addPropostaAperta(proposta17);

		
		commercio.addInsiemiAperti(insiemeAperto1);
		commercio.cercaProposteChiudibili(insiemeAperto1); 
		
		commercio.ritira(proposta16);
		commercio.ritira(proposta17);

    	return commercio;
    }
    
}
