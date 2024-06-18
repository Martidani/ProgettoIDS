package it.unibs.ids.progetto.testing;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import it.unibs.ids.progetto.Albero;
import it.unibs.ids.progetto.DefaultInitializer;
import it.unibs.ids.progetto.FattoriDiConversione;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.NotLeaf;
import it.unibs.ids.progetto.Utenza;

class GerarchiaTest {

	private Utenza utenza = DefaultInitializer.getDefaultInitializer().getUtenza();
	private Gerarchia gerarchia = DefaultInitializer.getDefaultInitializer().getGerarchia();
	private Geografia geografia = DefaultInitializer.getDefaultInitializer().getGeografia();
	
	
	
	@Test
	void verificaFattoreCorrettoTest1() {
		
		 Gerarchia gerarchia = new Gerarchia();

		    
	        try {
	        // Creazione del nodo radice
	        NotLeaf nodo0 = new NotLeaf(DefaultInitializer.ROOT_NAME_0, null, DefaultInitializer.ROOT_FIELD_0);
	        for (String domainValue : DefaultInitializer.ROOT_DOMAIN_0) {
	            nodo0.addElementiDominio(domainValue);
	        }

	        // Creazione dei nodi figli
	        Leaf nodo00 = new Leaf(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
	        Leaf nodo01 = new Leaf(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);
	        Leaf nodo10 = new Leaf(DefaultInitializer.CHILD_NAME_10, DefaultInitializer.ROOT_NAME_0);

	            nodo0.addChild(nodo00);
	            nodo0.addChild(nodo01);
	            nodo0.addChild(nodo10);

	            // Aggiunta dei nodi all'albero e definizione dei fattori di conversione
	            nodo00.addFattoreConversione(nodo01, DefaultInitializer.FACTOR_VAL_00);
	            nodo01.addFattoreConversione(nodo00, 1/DefaultInitializer.FACTOR_VAL_00);
	            nodo00.addFattoreConversione(nodo10, DefaultInitializer.FACTOR_VAL_10);
	            nodo10.addFattoreConversione(nodo00, 1/DefaultInitializer.FACTOR_VAL_10);
	            
	            
	            Albero albero0 = new Albero(nodo0);
	          
	            albero0.setUtente(utenza.autenticazioneConfiguratore(DefaultInitializer.DEFAULT_C1_USERNAME,
	            		DefaultInitializer.DEFAULT_C1_PASSWORD));
	            gerarchia.addAlbero(albero0);
	            FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
	            
	            assertEquals(1/DefaultInitializer.FACTOR_VAL_00 * DefaultInitializer.FACTOR_VAL_10,nodo01.fattoreFoglia(nodo10));
	            
	     
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
		
	        
	}
	@Test
	void verificaFattoreCorrettoTest2() {
		
		 Gerarchia gerarchia = new Gerarchia();

		    
	        try {
	        // Creazione del nodo radice
	        NotLeaf nodo0 = new NotLeaf(DefaultInitializer.ROOT_NAME_0, null, DefaultInitializer.ROOT_FIELD_0);
	        for (String domainValue : DefaultInitializer.ROOT_DOMAIN_0) {
	            nodo0.addElementiDominio(domainValue);
	        }

	        // Creazione dei nodi figli
	        Leaf nodo00 = new Leaf(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
	        Leaf nodo01 = new Leaf(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);
	        

	            nodo0.addChild(nodo00);
	            nodo0.addChild(nodo01);
	           

	            // Aggiunta dei nodi all'albero e definizione dei fattori di conversione
	            nodo00.addFattoreConversione(nodo01, DefaultInitializer.FACTOR_VAL_00);
	            nodo01.addFattoreConversione(nodo00, 1/DefaultInitializer.FACTOR_VAL_00);
	            
	            
	            
	            Albero albero0 = new Albero(nodo0);
	          
	            albero0.setUtente(utenza.autenticazioneConfiguratore(DefaultInitializer.DEFAULT_C1_USERNAME,
	            		DefaultInitializer.DEFAULT_C1_PASSWORD));
	            gerarchia.addAlbero(albero0);
	            FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
	            
	            
	            // Creazione del nodo radice
	            NotLeaf nodo1 = new NotLeaf(DefaultInitializer.ROOT_NAME_1, null, DefaultInitializer.ROOT_FIELD_1);
	            for (String domainValue : DefaultInitializer.ROOT_DOMAIN_1) {
	                nodo1.addElementiDominio(domainValue);
	            }

	            // Creazione dei nodi figli
	            Leaf nodo10 = new Leaf(DefaultInitializer.CHILD_NAME_10,DefaultInitializer.ROOT_NAME_1);
	         
	            
	                nodo1.addChild(nodo10);
	               

	                // Aggiunta dei nodi all'albero e definizione dei fattori di conversione
	                nodo10.addFattoreConversione(nodo00, DefaultInitializer.FACTOR_VAL_10);
	                nodo00.addFattoreConversione(nodo10, 1/DefaultInitializer.FACTOR_VAL_10);
	                
	               
	                Albero albero1 = new Albero(nodo1);
	              
	                albero1.setUtente(utenza.autenticazioneConfiguratore(DefaultInitializer.DEFAULT_C1_USERNAME,
	                		DefaultInitializer.DEFAULT_C1_PASSWORD));
	                gerarchia.addAlbero(albero1);
	                FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
	                
	                assertEquals(DefaultInitializer.FACTOR_VAL_00 * DefaultInitializer.FACTOR_VAL_10,nodo10.fattoreFoglia(nodo01));
		            
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
		
		
	}
	@Test
	void verificaFattoreCorrettoTest3() {
		
		 Gerarchia gerarchia = new Gerarchia();

		    
	        try {
	        // Creazione del nodo radice
	        NotLeaf nodo0 = new NotLeaf(DefaultInitializer.ROOT_NAME_0, null, DefaultInitializer.ROOT_FIELD_0);
	        for (String domainValue : DefaultInitializer.ROOT_DOMAIN_0) {
	            nodo0.addElementiDominio(domainValue);
	        }

	        // Creazione dei nodi figli
	        Leaf nodo00 = new Leaf(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
	        Leaf nodo01 = new Leaf(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);
	        Leaf nodo10 = new Leaf(DefaultInitializer.CHILD_NAME_10, DefaultInitializer.ROOT_NAME_0);

	            nodo0.addChild(nodo00);
	            nodo0.addChild(nodo01);
	            nodo0.addChild(nodo10);

	            // Aggiunta dei nodi all'albero e definizione dei fattori di conversione
	            nodo00.addFattoreConversione(nodo01, DefaultInitializer.FACTOR_VAL_00);
	            nodo01.addFattoreConversione(nodo00, 1/DefaultInitializer.FACTOR_VAL_00);
	            nodo00.addFattoreConversione(nodo10, DefaultInitializer.FACTOR_VAL_10);
	            nodo10.addFattoreConversione(nodo00, 1/DefaultInitializer.FACTOR_VAL_10);
	            
	            
	            Albero albero0 = new Albero(nodo0);
	          
	            albero0.setUtente(utenza.autenticazioneConfiguratore(DefaultInitializer.DEFAULT_C1_USERNAME,
	            		DefaultInitializer.DEFAULT_C1_PASSWORD));
	            gerarchia.addAlbero(albero0);
	            FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
	            
	            assertEquals(1.0,nodo00.fattoreFoglia(nodo00));
	            
	     
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
		
	        
		
		
	}
	
	@Test
	void verificaFattoreNonCorrettoTest1() {
		
		 Gerarchia gerarchia = new Gerarchia();

		    
	        try {
	        // Creazione del nodo radice
	        NotLeaf nodo0 = new NotLeaf(DefaultInitializer.ROOT_NAME_0, null, DefaultInitializer.ROOT_FIELD_0);
	        for (String domainValue : DefaultInitializer.ROOT_DOMAIN_0) {
	            nodo0.addElementiDominio(domainValue);
	        }

	        // Creazione dei nodi figli
	        Leaf nodo00 = new Leaf(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
	        Leaf nodo01 = new Leaf(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);
	        Leaf nodo10 = new Leaf(DefaultInitializer.CHILD_NAME_10, DefaultInitializer.ROOT_NAME_0);

	            nodo0.addChild(nodo00);
	            nodo0.addChild(nodo01);
	            nodo0.addChild(nodo10);

	            // Aggiunta dei nodi all'albero e definizione dei fattori di conversione
	            nodo00.addFattoreConversione(nodo01, DefaultInitializer.FACTOR_VAL_00);
	            nodo01.addFattoreConversione(nodo00, 1/DefaultInitializer.FACTOR_VAL_00);
	            nodo00.addFattoreConversione(nodo10, DefaultInitializer.FACTOR_VAL_10);
	            nodo10.addFattoreConversione(nodo00, 1/DefaultInitializer.FACTOR_VAL_10);
	            
	            
	            Albero albero0 = new Albero(nodo0);
	          
	            albero0.setUtente(utenza.autenticazioneConfiguratore(DefaultInitializer.DEFAULT_C1_USERNAME,
	            		DefaultInitializer.DEFAULT_C1_PASSWORD));
	            gerarchia.addAlbero(albero0);
	            FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
	            
	            assertNotEquals(1/DefaultInitializer.FACTOR_VAL_10 * DefaultInitializer.FACTOR_VAL_20,nodo01.fattoreFoglia(nodo10));
	            
	     
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	}

	@Test
	void verificaFattoreNonCorrettoTest2() {
		 Gerarchia gerarchia = new Gerarchia();

		    
	        try {
	        // Creazione del nodo radice
	        NotLeaf nodo0 = new NotLeaf(DefaultInitializer.ROOT_NAME_0, null, DefaultInitializer.ROOT_FIELD_0);
	        for (String domainValue : DefaultInitializer.ROOT_DOMAIN_0) {
	            nodo0.addElementiDominio(domainValue);
	        }

	        // Creazione dei nodi figli
	        Leaf nodo00 = new Leaf(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
	        Leaf nodo01 = new Leaf(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);
	        

	            nodo0.addChild(nodo00);
	            nodo0.addChild(nodo01);
	           

	            // Aggiunta dei nodi all'albero e definizione dei fattori di conversione
	            nodo00.addFattoreConversione(nodo01, DefaultInitializer.FACTOR_VAL_00);
	            nodo01.addFattoreConversione(nodo00, 1/DefaultInitializer.FACTOR_VAL_00);
	            
	            
	            
	            Albero albero0 = new Albero(nodo0);
	          
	            albero0.setUtente(utenza.autenticazioneConfiguratore(DefaultInitializer.DEFAULT_C1_USERNAME,
	            		DefaultInitializer.DEFAULT_C1_PASSWORD));
	            gerarchia.addAlbero(albero0);
	            FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
	            
	            
	            // Creazione del nodo radice
	            NotLeaf nodo1 = new NotLeaf(DefaultInitializer.ROOT_NAME_1, null, DefaultInitializer.ROOT_FIELD_1);
	            for (String domainValue : DefaultInitializer.ROOT_DOMAIN_1) {
	                nodo1.addElementiDominio(domainValue);
	            }

	            // Creazione dei nodi figli
	            Leaf nodo10 = new Leaf(DefaultInitializer.CHILD_NAME_10,DefaultInitializer.ROOT_NAME_1);
	         
	            
	                nodo1.addChild(nodo10);
	               

	                // Aggiunta dei nodi all'albero e definizione dei fattori di conversione
	                nodo10.addFattoreConversione(nodo00, DefaultInitializer.FACTOR_VAL_10);
	                nodo00.addFattoreConversione(nodo10, 1/DefaultInitializer.FACTOR_VAL_10);
	                
	               
	                Albero albero1 = new Albero(nodo1);
	              
	                albero1.setUtente(utenza.autenticazioneConfiguratore(DefaultInitializer.DEFAULT_C1_USERNAME,
	                		DefaultInitializer.DEFAULT_C1_PASSWORD));
	                gerarchia.addAlbero(albero1);
	                FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
	                
	                assertNotEquals(1/DefaultInitializer.FACTOR_VAL_00 * DefaultInitializer.FACTOR_VAL_10,nodo10.fattoreFoglia(nodo01));
		            
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
		
		
	}
	@Test
	void verificaFattoreNonCorrettoTest3() {
		
		 Gerarchia gerarchia = new Gerarchia();

		    
	        try {
	        // Creazione del nodo radice
	        NotLeaf nodo0 = new NotLeaf(DefaultInitializer.ROOT_NAME_0, null, DefaultInitializer.ROOT_FIELD_0);
	        for (String domainValue : DefaultInitializer.ROOT_DOMAIN_0) {
	            nodo0.addElementiDominio(domainValue);
	        }

	        // Creazione dei nodi figli
	        Leaf nodo00 = new Leaf(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
	        Leaf nodo01 = new Leaf(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);
	        Leaf nodo10 = new Leaf(DefaultInitializer.CHILD_NAME_10, DefaultInitializer.ROOT_NAME_0);

	            nodo0.addChild(nodo00);
	            nodo0.addChild(nodo01);
	            nodo0.addChild(nodo10);

	            // Aggiunta dei nodi all'albero e definizione dei fattori di conversione
	            nodo00.addFattoreConversione(nodo01, DefaultInitializer.FACTOR_VAL_00);
	            nodo01.addFattoreConversione(nodo00, 1/DefaultInitializer.FACTOR_VAL_00);
	            nodo00.addFattoreConversione(nodo10, DefaultInitializer.FACTOR_VAL_10);
	            nodo10.addFattoreConversione(nodo00, 1/DefaultInitializer.FACTOR_VAL_10);
	            
	            
	            Albero albero0 = new Albero(nodo0);
	          
	            albero0.setUtente(utenza.autenticazioneConfiguratore(DefaultInitializer.DEFAULT_C1_USERNAME,
	            		DefaultInitializer.DEFAULT_C1_PASSWORD));
	            gerarchia.addAlbero(albero0);
	            FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
	            
	            assertNotEquals(1.1,nodo00.fattoreFoglia(nodo00));
	            
	     
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
		
		
	}
}
