package it.unibs.ids.progetto.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unibs.ids.progetto.Commercio;
import it.unibs.ids.progetto.DefaultInitializer;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.InsiemeAperto;
import it.unibs.ids.progetto.Offerta;
import it.unibs.ids.progetto.PropostaAperta;
import it.unibs.ids.progetto.Richiesta;
import it.unibs.ids.progetto.Utenza;

class CommercioTest {
	
	private Commercio commercio = DefaultInitializer.getDefaultInitializer().getCommercio();
	private Utenza utenza = DefaultInitializer.getDefaultInitializer().getUtenza();
	private Gerarchia gerarchia = DefaultInitializer.getDefaultInitializer().getGerarchia();

	@Test
	public void esistePropostaAperta() {
		
		Fruitore fruitore0 = (Fruitore)utenza.getUtenti().get(2);
    	commercio.setFruitoreDiSessione(fruitore0);
		InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

		Richiesta r00 = new Richiesta(gerarchia.getFoglie().get(0), DefaultInitializer.COMMERCIO_VAL_00);
		Offerta o00 = new Offerta(gerarchia.getFoglie().get(1));
		PropostaAperta proposta00 = new PropostaAperta(r00, o00, commercio.numProposte(),fruitore0);
		
		insiemeAperto0.addPropostaAperta(proposta00);	
		commercio.addInsiemiAperti(insiemeAperto0);
		commercio.cercaProposteChiudibili(insiemeAperto0);  
		
		
		
		//assertEquals(DefaultInitializer, null);
		
	}
	

}
