package it.unibs.ids.progetto.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibs.ids.progetto.*;

class FattoriDiConversioneTest {

    private Utenza utenza;
    private Gerarchia gerarchia;

    @BeforeEach
    void setUp() {
        utenza = DefaultInitializer.getDefaultInitializer().getUtenza();
        gerarchia = new Gerarchia();
    }


    @Test
    void test_FattoreConversione_TraFoglieDiStessoAlbero() throws Exception {
        NotLeaf nodo0 = creaNodoRadice(DefaultInitializer.ROOT_NAME_0, DefaultInitializer.ROOT_FIELD_0, DefaultInitializer.ROOT_DOMAIN_0);

        Leaf nodo00 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo01 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo10 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_10, DefaultInitializer.ROOT_NAME_0);

        nodo0.addChild(nodo00);
        nodo0.addChild(nodo01);
        nodo0.addChild(nodo10);

        nodo00.addFattoreConversione(nodo01, DefaultInitializer.FACTOR_VAL_00);
        nodo01.addFattoreConversione(nodo00, 1 / DefaultInitializer.FACTOR_VAL_00);
        nodo00.addFattoreConversione(nodo10, DefaultInitializer.FACTOR_VAL_10);
        nodo10.addFattoreConversione(nodo00, 1 / DefaultInitializer.FACTOR_VAL_10);

        creaAlbero(nodo0);

        assertEquals(1 / DefaultInitializer.FACTOR_VAL_00 * DefaultInitializer.FACTOR_VAL_10, nodo01.fattoreFoglia(nodo10));
    }

    @Test
    void test_FattoreConversione_TraFoglieDiAlberiDiversi() throws Exception {
        NotLeaf nodo0 = creaNodoRadice(DefaultInitializer.ROOT_NAME_0, DefaultInitializer.ROOT_FIELD_0, DefaultInitializer.ROOT_DOMAIN_0);

        Leaf nodo00 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo01 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);

        nodo0.addChild(nodo00);
        nodo0.addChild(nodo01);

        nodo00.addFattoreConversione(nodo01, DefaultInitializer.FACTOR_VAL_00);
        nodo01.addFattoreConversione(nodo00, 1 / DefaultInitializer.FACTOR_VAL_00);

        creaAlbero(nodo0);

        NotLeaf nodo1 = creaNodoRadice(DefaultInitializer.ROOT_NAME_1, DefaultInitializer.ROOT_FIELD_1, DefaultInitializer.ROOT_DOMAIN_1);

        Leaf nodo10 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_10, DefaultInitializer.ROOT_NAME_1);

        nodo1.addChild(nodo10);

        nodo10.addFattoreConversione(nodo00, DefaultInitializer.FACTOR_VAL_10);
        nodo00.addFattoreConversione(nodo10, 1 / DefaultInitializer.FACTOR_VAL_10);

        creaAlbero(nodo1);

        assertEquals(DefaultInitializer.FACTOR_VAL_00 * DefaultInitializer.FACTOR_VAL_10, nodo10.fattoreFoglia(nodo01));
    }

    @Test
    void test_FattoreConversione_Identita() throws Exception {
        NotLeaf nodo0 = creaNodoRadice(DefaultInitializer.ROOT_NAME_0, DefaultInitializer.ROOT_FIELD_0, DefaultInitializer.ROOT_DOMAIN_0);

        Leaf nodo00 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo01 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo10 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_10, DefaultInitializer.ROOT_NAME_0);

        nodo0.addChild(nodo00);
        nodo0.addChild(nodo01);
        nodo0.addChild(nodo10);

        nodo00.addFattoreConversione(nodo01, DefaultInitializer.FACTOR_VAL_00);
        nodo01.addFattoreConversione(nodo00, 1 / DefaultInitializer.FACTOR_VAL_00);
        nodo00.addFattoreConversione(nodo10, DefaultInitializer.FACTOR_VAL_10);
        nodo10.addFattoreConversione(nodo00, 1 / DefaultInitializer.FACTOR_VAL_10);

        creaAlbero(nodo0);

        assertEquals(1.0, nodo00.fattoreFoglia(nodo00));
    }

    @Test
    void test_FattoreNonCorretto_TraFoglieDiStessoAlbero() throws Exception {
        NotLeaf nodo0 = creaNodoRadice(DefaultInitializer.ROOT_NAME_0, DefaultInitializer.ROOT_FIELD_0, DefaultInitializer.ROOT_DOMAIN_0);

        Leaf nodo00 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo01 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo10 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_10, DefaultInitializer.ROOT_NAME_0);

        nodo0.addChild(nodo00);
        nodo0.addChild(nodo01);
        nodo0.addChild(nodo10);

        nodo00.addFattoreConversione(nodo01, DefaultInitializer.FACTOR_VAL_00);
        nodo01.addFattoreConversione(nodo00, 1 / DefaultInitializer.FACTOR_VAL_00);
        nodo00.addFattoreConversione(nodo10, DefaultInitializer.FACTOR_VAL_10);
        nodo10.addFattoreConversione(nodo00, 1 / DefaultInitializer.FACTOR_VAL_10);

        creaAlbero(nodo0);

        assertNotEquals(1 / DefaultInitializer.FACTOR_VAL_10 * DefaultInitializer.FACTOR_VAL_20, nodo01.fattoreFoglia(nodo10));
    }

    @Test
    void test_FattoreNonCorretto_TraFoglieDiAlberiDiversi() throws Exception {
        NotLeaf nodo0 = creaNodoRadice(DefaultInitializer.ROOT_NAME_0, DefaultInitializer.ROOT_FIELD_0, DefaultInitializer.ROOT_DOMAIN_0);

        Leaf nodo00 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo01 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);

        nodo0.addChild(nodo00);
        nodo0.addChild(nodo01);

        nodo00.addFattoreConversione(nodo01, DefaultInitializer.FACTOR_VAL_00);
        nodo01.addFattoreConversione(nodo00, 1 / DefaultInitializer.FACTOR_VAL_00);

        creaAlbero(nodo0);

        NotLeaf nodo1 = creaNodoRadice(DefaultInitializer.ROOT_NAME_1, DefaultInitializer.ROOT_FIELD_1, DefaultInitializer.ROOT_DOMAIN_1);

        Leaf nodo10 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_10, DefaultInitializer.ROOT_NAME_1);

        nodo1.addChild(nodo10);

        nodo10.addFattoreConversione(nodo00, DefaultInitializer.FACTOR_VAL_10);
        nodo00.addFattoreConversione(nodo10, 1 / DefaultInitializer.FACTOR_VAL_10);

        creaAlbero(nodo1);

        assertNotEquals(1 / DefaultInitializer.FACTOR_VAL_00 * DefaultInitializer.FACTOR_VAL_10, nodo10.fattoreFoglia(nodo01));
    }

    @Test
    void test_FattoreNonCorretto_Identita() throws Exception {
        NotLeaf nodo0 = creaNodoRadice(DefaultInitializer.ROOT_NAME_0, DefaultInitializer.ROOT_FIELD_0, DefaultInitializer.ROOT_DOMAIN_0);

        Leaf nodo00 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo01 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo10 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_10, DefaultInitializer.ROOT_NAME_0);

        nodo0.addChild(nodo00);
        nodo0.addChild(nodo01);
        nodo0.addChild(nodo10);

        nodo00.addFattoreConversione(nodo01, DefaultInitializer.FACTOR_VAL_00);
        nodo01.addFattoreConversione(nodo00, 1 / DefaultInitializer.FACTOR_VAL_00);
        nodo00.addFattoreConversione(nodo10, DefaultInitializer.FACTOR_VAL_10);
        nodo10.addFattoreConversione(nodo00, 1 / DefaultInitializer.FACTOR_VAL_10);

        creaAlbero(nodo0);

        assertNotEquals(1.1, nodo00.fattoreFoglia(nodo00));
    }
    
    
    // metodi di utilità
    private NotLeaf creaNodoRadice(String nome, String campo, String[] dominio) {
        NotLeaf nodo = new NotLeaf(nome, null, campo);
        for (String domainValue : dominio) {
            nodo.addElementiDominio(domainValue);
        }
        return nodo;
    }

    private Leaf creaNodoFoglia(String nome, String nomeRadice) {
        return new Leaf(nome, nomeRadice);
    }

    private Albero creaAlbero(NotLeaf nodoRadice) throws Exception {
        Albero albero = new Albero(nodoRadice);
        albero.setUtente(utenza.autenticazioneConfiguratore(DefaultInitializer.DEFAULT_C1_USERNAME,
                DefaultInitializer.DEFAULT_C1_PASSWORD));
        gerarchia.addAlbero(albero);
        FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
        return albero;
    }
}
