package it.unibs.ids.progetto.testing;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
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

public class CommercioTest {
	
    private Utenza utenza;
    private Gerarchia gerarchia;
    private Commercio commercio;
    private Fruitore fruitore0;

    @BeforeEach
    void setUp() {
        utenza = DefaultInitializer.getDefaultInitializer().getUtenza();
        gerarchia = DefaultInitializer.getDefaultInitializer().getGerarchia();
        commercio = new Commercio();
        fruitore0 = (Fruitore) utenza.getUtenti().get(2);
        commercio.setFruitoreDiSessione(fruitore0);
    }

    @Test
    void test_Chiudi_NessunaProposta() {
        InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());
        commercio.addInsiemiAperti(insiemeAperto0);
        commercio.cercaProposteChiudibili(insiemeAperto0);

        assertEquals(0, commercio.getInsiemiChiusi().size());
    }

    @Test
    void test_Chiudi_ProposteConValoriEstremi() {
        InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

        int val = Integer.MAX_VALUE-1;
        PropostaAperta proposta00 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(1), val),
                new Offerta(gerarchia.getFoglie().get(0))
        );

        PropostaAperta proposta01 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(0), proposta00.getOfferta().getDurata()),
                new Offerta(gerarchia.getFoglie().get(1))
        );

        aggiungiProposteAperte(insiemeAperto0, proposta00, proposta01);

        // Verificare che le proposte siano chiuse correttamente anche con valori estremi
        assertEquals(2, commercio.getInsiemiChiusi().get(0).getProposteChiuse().size());
    }

    

    @Test
    void test_Chiudi_ProposteConRichiestaZero() {
        InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

        PropostaAperta proposta00 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(0), 0),
                new Offerta(gerarchia.getFoglie().get(1))
        );

        PropostaAperta proposta01 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(1), 0),
                new Offerta(gerarchia.getFoglie().get(0))
        );

        aggiungiProposteAperte(insiemeAperto0, proposta00, proposta01);

        // Verificare che le proposte siano chiuse correttamente anche con richieste di valore zero
        assertEquals(2, commercio.getInsiemiChiusi().get(0).getProposteChiuse().size());
    }

    @Test
    void test_Chiudi_ProposteConRichiestaNegativa() {
        InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

        PropostaAperta proposta00 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(0), -1),
                new Offerta(gerarchia.getFoglie().get(1))
        );

        PropostaAperta proposta01 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(1), -1),
                new Offerta(gerarchia.getFoglie().get(0))
        );

        aggiungiProposteAperte(insiemeAperto0, proposta00, proposta01);

        // Verificare che le proposte non vengano chiuse con richieste negative
        assertEquals(0, commercio.getInsiemiChiusi().size());
    }

    @Test
    void test_Chiudi_3Proposte() {
        InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

        PropostaAperta proposta00 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(0), DefaultInitializer.COMMERCIO_VAL_00),
                new Offerta(gerarchia.getFoglie().get(1))
        );

        PropostaAperta proposta01 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(1), DefaultInitializer.COMMERCIO_VAL_01),
                new Offerta(gerarchia.getFoglie().get(2))
        );

        PropostaAperta proposta02 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(2), DefaultInitializer.COMMERCIO_VAL_02),
                new Offerta(gerarchia.getFoglie().get(0))
        );

        aggiungiProposteAperte(insiemeAperto0, proposta00, proposta01, proposta02);

        assertEquals(3, commercio.getInsiemiChiusi().get(0).getProposteChiuse().size());
    }

    @Test
    void test_Chiudi_2Proposte_soddisfacimento1() {
        InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

        PropostaAperta proposta00 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(0), 2),
                new Offerta(gerarchia.getFoglie().get(1))
        );

        PropostaAperta proposta01 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(1), 3),
                new Offerta(gerarchia.getFoglie().get(0))
        );

        aggiungiProposteAperte(insiemeAperto0, proposta00, proposta01);

        assertEquals(2, commercio.getInsiemiChiusi().get(0).getProposteChiuse().size());
    }

    @Test
    void test_Chiudi_2Proposte_soddisfacimento2() {
        InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

        PropostaAperta proposta00 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(0), 4),
                new Offerta(gerarchia.getFoglie().get(1))
        );

        PropostaAperta proposta01 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(1), 6),
                new Offerta(gerarchia.getFoglie().get(0))
        );

        aggiungiProposteAperte(insiemeAperto0, proposta00, proposta01);

        assertEquals(2, commercio.getInsiemiChiusi().get(0).getProposteChiuse().size());
    }

    @Test
    void test_NonChiudi_3Proposte() {
        InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

        PropostaAperta proposta00 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(0), DefaultInitializer.COMMERCIO_VAL_00),
                new Offerta(gerarchia.getFoglie().get(1))
        );

        PropostaAperta proposta01 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(1), DefaultInitializer.COMMERCIO_VAL_05),
                new Offerta(gerarchia.getFoglie().get(2))
        );

        PropostaAperta proposta02 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(2), DefaultInitializer.COMMERCIO_VAL_02),
                new Offerta(gerarchia.getFoglie().get(0))
        );

        aggiungiProposteAperte(insiemeAperto0, proposta00, proposta01, proposta02);

        assertEquals(0, commercio.getInsiemiChiusi().size());
    }

    @Test
    void test_NonChiudi_2Proposte_soddisfacimento1() {
        InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

        PropostaAperta proposta00 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(0), 2),
                new Offerta(gerarchia.getFoglie().get(1))
        );

        PropostaAperta proposta01 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(1), 4),
                new Offerta(gerarchia.getFoglie().get(0))
        );

        aggiungiProposteAperte(insiemeAperto0, proposta00, proposta01);

        assertNotEquals(1, commercio.getInsiemiChiusi().size());
    }

    @Test
    void test_NonChiudi_2Proposte_soddisfacimento2() {
        InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

        PropostaAperta proposta00 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(0), 2),
                new Offerta(gerarchia.getFoglie().get(1))
        );

        PropostaAperta proposta01 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(1), 3),
                new Offerta(gerarchia.getFoglie().get(2))
        );

        aggiungiProposteAperte(insiemeAperto0, proposta00, proposta01);

        assertNotEquals(1, commercio.getInsiemiChiusi().size());
    }
    
    
    // metodi di utilità
    private PropostaAperta createProposta(Richiesta richiesta, Offerta offerta) {
        return new PropostaAperta(richiesta, offerta, commercio.numProposte(), fruitore0);
    }

    private void aggiungiProposteAperte(InsiemeAperto insiemeAperto, PropostaAperta... proposte) {
        for (PropostaAperta proposta : proposte) {
            insiemeAperto.addPropostaAperta(proposta);
        }
        commercio.addInsiemiAperti(insiemeAperto);
        commercio.cercaProposteChiudibili(insiemeAperto);
    }
}
