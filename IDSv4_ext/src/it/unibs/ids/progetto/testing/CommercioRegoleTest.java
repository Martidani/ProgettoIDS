package it.unibs.ids.progetto.testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibs.ids.progetto.*;
import it.unibs.ids.progetto.servizi.DefaultInitializer;

public class CommercioRegoleTest {
    
    private Utenza utenza;
    private Gerarchia gerarchia;
    private Commercio commercio;
    private Fruitore fruitore0;

    @BeforeEach
    void setUp() {
        // Inizializzazione dei dati necessari per ogni test
        utenza = DefaultInitializer.getDefaultInitializer().getUtenza();
        gerarchia = DefaultInitializer.getDefaultInitializer().getGerarchia();
        commercio = new Commercio();
        fruitore0 = (Fruitore) utenza.getUtenti().get(2); // Assume che il terzo utente sia un Fruitore
        commercio.setFruitoreDiSessione(fruitore0); // Imposta il Fruitore di sessione per il commercio
    }

    // Metodo di utilità per creare una PropostaAperta
    private PropostaAperta createProposta(Richiesta richiesta, Offerta offerta) {
        return new PropostaAperta(richiesta, offerta, commercio.numProposte(), fruitore0);
    }

    // Metodo di utilità per aggiungere ProposteAperte a un InsiemeAperto e gestire il commercio
    private void aggiungiProposteAperte(InsiemeAperto insiemeAperto, PropostaAperta... proposte) {
        for (PropostaAperta proposta : proposte) {
            insiemeAperto.addPropostaAperta(proposta);
        }
        commercio.addInsiemiAperti(insiemeAperto); // Aggiunge l'insieme aperto al commercio
        commercio.cercaProposteChiudibili(insiemeAperto); // Cerca le proposte chiudibili nell'insieme aperto
    }

    //------------------------------------------------------------------------------------------ 
    //---------------------------Equivalence Partitioning Test---------------------------------- 
    //------------------------------------------------------------------------------------------

    @Test
    void test_Chiudi_3Proposte() {
        // Test per verificare che sia possibile chiudere 3 proposte
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

        // Verifica che ci siano 3 proposte chiuse nell'insieme chiuso
        assertEquals(3, commercio.getInsiemiChiusi().get(0).getProposteChiuse().size());
    }

    @Test
    void test_Chiudi_2Proposte_soddisfacimento1() {
        // Test per verificare che sia possibile chiudere 2 proposte con soddisfacimento 1
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

        // Verifica che ci siano 2 proposte chiuse nell'insieme chiuso
        assertEquals(2, commercio.getInsiemiChiusi().get(0).getProposteChiuse().size());
    }

    @Test
    void test_Chiudi_2Proposte_soddisfacimento2() {
        // Test per verificare che sia possibile chiudere 2 proposte con soddisfacimento 2
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

        // Verifica che ci siano 2 proposte chiuse nell'insieme chiuso
        assertEquals(2, commercio.getInsiemiChiusi().get(0).getProposteChiuse().size());
    }

    @Test
    void test_NonChiudi_3Proposte() {
        // Test per verificare che non si possano chiudere 3 proposte
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

        // Verifica che non ci siano insiemi chiusi
        assertEquals(0, commercio.getInsiemiChiusi().size());
    }

    @Test
    void test_NonChiudi_2Proposte_soddisfacimento1() {
        // Test per verificare che non si possano chiudere 2 proposte con soddisfacimento 1
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

        // Verifica che ci sia almeno un insieme chiuso
        assertNotEquals(1, commercio.getInsiemiChiusi().size());
    }

    @Test
    void test_NonChiudi_2Proposte_soddisfacimento2() {
        // Test per verificare che non si possano chiudere 2 proposte con soddisfacimento 2
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

        // Verifica che ci sia almeno un insieme chiuso
        assertNotEquals(1, commercio.getInsiemiChiusi().size());
    }


    //------------------------------------------------------------------------------------------ 
    //---------------------------------Boundary Value Analysis---------------------------------- 
    //------------------------------------------------------------------------------------------

    @Test
    void test_Chiudi_NessunaProposta() {
        // Test per verificare che non ci siano proposte da chiudere
        InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());
        commercio.addInsiemiAperti(insiemeAperto0);
        commercio.cercaProposteChiudibili(insiemeAperto0);

        assertEquals(0, commercio.getInsiemiChiusi().size());
    }

    @Test
    void test_Chiudi_ProposteConValoriEstremi() {
        // Test per verificare che sia possibile chiudere proposte con valori estremi
        InsiemeAperto insiemeAperto0 = new InsiemeAperto(fruitore0.getComprensorioAppartenenza());

        int val = Integer.MAX_VALUE - 1;
        PropostaAperta proposta00 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(1), val),
                new Offerta(gerarchia.getFoglie().get(0))
        );

        PropostaAperta proposta01 = createProposta(
                new Richiesta(gerarchia.getFoglie().get(0), proposta00.getOfferta().getDurata()),
                new Offerta(gerarchia.getFoglie().get(1))
        );

        aggiungiProposteAperte(insiemeAperto0, proposta00, proposta01);

        // Verifica che le proposte siano chiuse correttamente anche con valori estremi
        assertEquals(2, commercio.getInsiemiChiusi().get(0).getProposteChiuse().size());
    }

    @Test
    void test_Chiudi_ProposteConRichiestaZero() {
        // Test per verificare che sia possibile chiudere proposte con richieste di valore zero
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

        // Verifica che le proposte siano chiuse correttamente anche con richieste di valore zero
        assertEquals(2, commercio.getInsiemiChiusi().get(0).getProposteChiuse().size());
    }

    @Test
    void test_Chiudi_ProposteConRichiestaNegativa() {
        // Test per verificare che non si possano chiudere proposte con richieste negative
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

        // Verifica che le proposte non vengano chiuse con richieste negative
        assertEquals(0, commercio.getInsiemiChiusi().size());
    }
}
