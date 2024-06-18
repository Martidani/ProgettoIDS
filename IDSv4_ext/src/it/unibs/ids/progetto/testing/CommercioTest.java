package it.unibs.ids.progetto.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unibs.ids.progetto.Commercio;
import it.unibs.ids.progetto.DefaultInitializer;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.InsiemeAperto;
import it.unibs.ids.progetto.Offerta;
import it.unibs.ids.progetto.PropostaAperta;
import it.unibs.ids.progetto.Richiesta;
import it.unibs.ids.progetto.Utenza;

class CommercioTest {
	
	private Utenza utenza = DefaultInitializer.getDefaultInitializer().getUtenza();
	private Gerarchia gerarchia = DefaultInitializer.getDefaultInitializer().getGerarchia();
	
	
	
	
	@Test
	public void chiudiProposteTest1() {
		
	Commercio commercio = new Commercio();
    	
    	
    	Fruitore fruitore0 = (Fruitore)utenza.getUtenti().get(2);
    	commercio.setFruitoreDiSessione(fruitore0);
    	InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

		Richiesta r00 = new Richiesta(gerarchia.getFoglie().get(0), DefaultInitializer.COMMERCIO_VAL_00);
		Offerta o00 = new Offerta(gerarchia.getFoglie().get(1));
		PropostaAperta proposta00 = new PropostaAperta(r00, o00, commercio.numProposte(),fruitore0);
		
		Richiesta r01 = new Richiesta(gerarchia.getFoglie().get(1), DefaultInitializer.COMMERCIO_VAL_01);
		Offerta o01 = new Offerta(gerarchia.getFoglie().get(2));
		PropostaAperta proposta01 = new PropostaAperta(r01, o01, commercio.numProposte(),fruitore0);
	
		Richiesta r02 = new Richiesta(gerarchia.getFoglie().get(2), DefaultInitializer.COMMERCIO_VAL_02);
		Offerta o02 = new Offerta(gerarchia.getFoglie().get(0));
		PropostaAperta proposta02 = new PropostaAperta(r02, o02, commercio.numProposte(),fruitore0);
	
		insiemeAperto0.addPropostaAperta(proposta00);		
		insiemeAperto0.addPropostaAperta(proposta01);
		insiemeAperto0.addPropostaAperta(proposta02);
		
		
		commercio.addInsiemiAperti(insiemeAperto0);
		commercio.cercaProposteChiudibili(insiemeAperto0);  
		
		
		assertEquals(commercio.getInsiemiChiusi().get(0).getProposteChiuse().size(),3);


		
		
	}
	@Test
	public void chiudiProposteTest2() {
Commercio commercio = new Commercio();
    	
    	
    	Fruitore fruitore0 = (Fruitore)utenza.getUtenti().get(2);
    	commercio.setFruitoreDiSessione(fruitore0);
    	InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

		Richiesta r00 = new Richiesta(gerarchia.getFoglie().get(0), 2);
		Offerta o00 = new Offerta(gerarchia.getFoglie().get(1));
		PropostaAperta proposta00 = new PropostaAperta(r00, o00, commercio.numProposte(),fruitore0);
		
		Richiesta r01 = new Richiesta(gerarchia.getFoglie().get(1), 3);
		Offerta o01 = new Offerta(gerarchia.getFoglie().get(0));
		PropostaAperta proposta01 = new PropostaAperta(r01, o01, commercio.numProposte(),fruitore0);
	
	
	
		insiemeAperto0.addPropostaAperta(proposta00);		
		insiemeAperto0.addPropostaAperta(proposta01);
		
		
		
		commercio.addInsiemiAperti(insiemeAperto0);
		commercio.cercaProposteChiudibili(insiemeAperto0);  
		
		
		assertEquals(commercio.getInsiemiChiusi().get(0).getProposteChiuse().size(),2);
	}
	@Test
	public void chiudiProposteTest3() {
Commercio commercio = new Commercio();
    	
    	
    	Fruitore fruitore0 = (Fruitore)utenza.getUtenti().get(2);
    	commercio.setFruitoreDiSessione(fruitore0);
    	InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

		Richiesta r00 = new Richiesta(gerarchia.getFoglie().get(0), 4);
		Offerta o00 = new Offerta(gerarchia.getFoglie().get(1));
		PropostaAperta proposta00 = new PropostaAperta(r00, o00, commercio.numProposte(),fruitore0);
		
		Richiesta r01 = new Richiesta(gerarchia.getFoglie().get(1), 6);
		Offerta o01 = new Offerta(gerarchia.getFoglie().get(0));
		PropostaAperta proposta01 = new PropostaAperta(r01, o01, commercio.numProposte(),fruitore0);
	
	
	
		insiemeAperto0.addPropostaAperta(proposta00);		
		insiemeAperto0.addPropostaAperta(proposta01);
		
		
		
		commercio.addInsiemiAperti(insiemeAperto0);
		commercio.cercaProposteChiudibili(insiemeAperto0);  
		
		
		assertEquals(commercio.getInsiemiChiusi().get(0).getProposteChiuse().size(),2);
	}
	
	@Test
	public void nonChiudiProposteTest1() {
		Commercio commercio = new Commercio();
    	
    	
    	Fruitore fruitore0 = (Fruitore)utenza.getUtenti().get(2);
    	commercio.setFruitoreDiSessione(fruitore0);
    	InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

		Richiesta r00 = new Richiesta(gerarchia.getFoglie().get(0), DefaultInitializer.COMMERCIO_VAL_00);
		Offerta o00 = new Offerta(gerarchia.getFoglie().get(1));
		PropostaAperta proposta00 = new PropostaAperta(r00, o00, commercio.numProposte(),fruitore0);
		
		Richiesta r01 = new Richiesta(gerarchia.getFoglie().get(1), DefaultInitializer.COMMERCIO_VAL_05);
		Offerta o01 = new Offerta(gerarchia.getFoglie().get(2));
		PropostaAperta proposta01 = new PropostaAperta(r01, o01, commercio.numProposte(),fruitore0);
	
		Richiesta r02 = new Richiesta(gerarchia.getFoglie().get(2), DefaultInitializer.COMMERCIO_VAL_02);
		Offerta o02 = new Offerta(gerarchia.getFoglie().get(0));
		PropostaAperta proposta02 = new PropostaAperta(r02, o02, commercio.numProposte(),fruitore0);
	
		insiemeAperto0.addPropostaAperta(proposta00);		
		insiemeAperto0.addPropostaAperta(proposta01);
		insiemeAperto0.addPropostaAperta(proposta02);
		
		
		commercio.addInsiemiAperti(insiemeAperto0);
		commercio.cercaProposteChiudibili(insiemeAperto0);  
		
		
		assertEquals(commercio.getInsiemiChiusi().size(),0);
	}
	@Test
	public void nonChiudiProposteTest2() {
		Commercio commercio = new Commercio();
    	
    	
    	Fruitore fruitore0 = (Fruitore)utenza.getUtenti().get(2);
    	commercio.setFruitoreDiSessione(fruitore0);
    	InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

		Richiesta r00 = new Richiesta(gerarchia.getFoglie().get(0), 2);
		Offerta o00 = new Offerta(gerarchia.getFoglie().get(1));
		PropostaAperta proposta00 = new PropostaAperta(r00, o00, commercio.numProposte(),fruitore0);
		
		Richiesta r01 = new Richiesta(gerarchia.getFoglie().get(1), 4);
		Offerta o01 = new Offerta(gerarchia.getFoglie().get(0));
		PropostaAperta proposta01 = new PropostaAperta(r01, o01, commercio.numProposte(),fruitore0);
	
	
	
		insiemeAperto0.addPropostaAperta(proposta00);		
		insiemeAperto0.addPropostaAperta(proposta01);
		
		
		
		commercio.addInsiemiAperti(insiemeAperto0);
		commercio.cercaProposteChiudibili(insiemeAperto0);  
		
		
		assertNotEquals(commercio.getInsiemiChiusi().size(),1);
	}
	@Test
	public void nonChiudiProposteTest3() {
		Commercio commercio = new Commercio();
    	
    	
    	Fruitore fruitore0 = (Fruitore)utenza.getUtenti().get(2);
    	commercio.setFruitoreDiSessione(fruitore0);
    	InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

		Richiesta r00 = new Richiesta(gerarchia.getFoglie().get(0), 2);
		Offerta o00 = new Offerta(gerarchia.getFoglie().get(1));
		PropostaAperta proposta00 = new PropostaAperta(r00, o00, commercio.numProposte(),fruitore0);
		
		Richiesta r01 = new Richiesta(gerarchia.getFoglie().get(1), 3);
		Offerta o01 = new Offerta(gerarchia.getFoglie().get(2));
		PropostaAperta proposta01 = new PropostaAperta(r01, o01, commercio.numProposte(),fruitore0);
	
	
	
		insiemeAperto0.addPropostaAperta(proposta00);		
		insiemeAperto0.addPropostaAperta(proposta01);
		
		
		
		commercio.addInsiemiAperti(insiemeAperto0);
		commercio.cercaProposteChiudibili(insiemeAperto0);  
		
		
		assertNotEquals(commercio.getInsiemiChiusi().size(),1);
		
	}


}
