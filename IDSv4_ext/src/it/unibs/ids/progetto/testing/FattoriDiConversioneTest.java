package it.unibs.ids.progetto.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibs.ids.progetto.*;
import it.unibs.ids.progetto.servizi.DefaultInitializer;

/**
 * Questa classe contiene i test JUnit per verificare il comportamento dei fattori di conversione
 * tra nodi foglia in alberi gerarchici.
 */
class FattoriDiConversioneTest {

    private Utenza utenza;
    private Gerarchia gerarchia;

    @BeforeEach
    void setUp() {
        // Inizializzazione delle strutture di base prima di ogni test
        utenza = DefaultInitializer.getDefaultInitializer().getUtenza();
        gerarchia = new Gerarchia();
    }

    /**
     * Metodo privato per creare un nodo radice con specifici parametri.
     * @param nome Nome del nodo radice
     * @param campo Campo del nodo radice
     * @param dominio Dominio del nodo radice
     * @return Il nodo radice creato
     */
    private NotLeaf creaNodoRadice(String nome, String campo, String[] dominio) {
        NotLeaf nodo = new NotLeaf(nome, null, campo);
        for (String domainValue : dominio) {
            nodo.addElementiDominio(domainValue);
        }
        return nodo;
    }

    /**
     * Metodo privato per creare un nodo foglia con specifici parametri.
     * @param nome Nome del nodo foglia
     * @param nomeRadice Nome della radice a cui il nodo foglia appartiene
     * @return Il nodo foglia creato
     */
    private Leaf creaNodoFoglia(String nome, String nomeRadice) {
        return new Leaf(nome, nomeRadice);
    }

    /**
     * Metodo privato per creare un albero gerarchico a partire da un nodo radice.
     * @param nodoRadice Nodo radice dell'albero
     * @return L'albero creato
     * @throws Exception Se si verificano eccezioni durante la creazione dell'albero
     */
    private Albero creaAlbero(NotLeaf nodoRadice) throws Exception {
        Albero albero = new Albero(nodoRadice);
        albero.setUtente(utenza.autenticazioneConfiguratore(DefaultInitializer.DEFAULT_C1_USERNAME,
                DefaultInitializer.DEFAULT_C1_PASSWORD));
        gerarchia.addAlbero(albero);
        FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
        return albero;
    }

    /**
     * Metodo privato per aggiungere un fattore di conversione tra due nodi foglia.
     * @param nodo1 Primo nodo foglia
     * @param nodo2 Secondo nodo foglia
     * @param fattore Fattore di conversione da nodo1 a nodo2
     */
    private void addFattoreConversione(Leaf nodo1, Leaf nodo2, double fattore ) {
        nodo1.addFattoreConversione(nodo2, fattore);
        nodo2.addFattoreConversione(nodo1, 1/fattore);
    }

    //------------------------------------------------------------------------------------------ 
    //---------------------------Equivalence Partitioning Test---------------------------------- 
    //------------------------------------------------------------------------------------------

    /**
     * Testa il fattore di conversione tra foglie dello stesso albero.
     * @throws Exception Se si verificano eccezioni durante l'esecuzione del test
     */
    @Test
    void test_FattoreConversione_TraFoglieDiStessoAlbero() throws Exception {
        // Creazione della radice e dei nodi foglia
        NotLeaf nodo0 = creaNodoRadice(DefaultInitializer.ROOT_NAME_0, DefaultInitializer.ROOT_FIELD_0, DefaultInitializer.ROOT_DOMAIN_0);
        Leaf nodo00 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo01 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo10 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_10, DefaultInitializer.ROOT_NAME_0);

        // Aggiunta dei nodi foglia alla radice
        nodo0.addChild(nodo00);
        nodo0.addChild(nodo01);
        nodo0.addChild(nodo10);

        // Aggiunta dei fattori di conversione
        addFattoreConversione(nodo00, nodo01, DefaultInitializer.FACTOR_VAL_00);
        addFattoreConversione(nodo00, nodo10, DefaultInitializer.FACTOR_VAL_00);

        // Creazione dell'albero e esecuzione del test
        creaAlbero(nodo0);

        // Verifica del risultato atteso
        assertEquals(1 / DefaultInitializer.FACTOR_VAL_00 * DefaultInitializer.FACTOR_VAL_10, nodo01.fattoreFoglia(nodo10));
    }

    /**
     * Testa il fattore di conversione tra foglie di alberi diversi.
     * @throws Exception Se si verificano eccezioni durante l'esecuzione del test
     */
    @Test
    void test_FattoreConversione_TraFoglieDiAlberiDiversi() throws Exception {
        // Creazione della radice e dei nodi foglia per il primo albero
        NotLeaf nodo0 = creaNodoRadice(DefaultInitializer.ROOT_NAME_0, DefaultInitializer.ROOT_FIELD_0, DefaultInitializer.ROOT_DOMAIN_0);
        Leaf nodo00 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo01 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);

        // Aggiunta dei nodi foglia alla radice
        nodo0.addChild(nodo00);
        nodo0.addChild(nodo01);

        // Aggiunta del fattore di conversione nel primo albero
        addFattoreConversione(nodo00, nodo01, DefaultInitializer.FACTOR_VAL_00);

        // Creazione e aggiunta del secondo albero
        creaAlbero(nodo0);

        // Creazione della radice e dei nodi foglia per il secondo albero
        NotLeaf nodo1 = creaNodoRadice(DefaultInitializer.ROOT_NAME_1, DefaultInitializer.ROOT_FIELD_1, DefaultInitializer.ROOT_DOMAIN_1);
        Leaf nodo10 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_10, DefaultInitializer.ROOT_NAME_1);

        // Aggiunta del nodo foglia al secondo albero
        nodo1.addChild(nodo10);

        // Aggiunta del fattore di conversione nel secondo albero
        addFattoreConversione(nodo10, nodo00, DefaultInitializer.FACTOR_VAL_10);

        // Creazione del secondo albero
        creaAlbero(nodo1);

        // Verifica del risultato atteso
        assertEquals(DefaultInitializer.FACTOR_VAL_00 * DefaultInitializer.FACTOR_VAL_10, nodo10.fattoreFoglia(nodo01));
    }

    /**
     * Testa il fattore di conversione identità per lo stesso nodo foglia.
     * @throws Exception Se si verificano eccezioni durante l'esecuzione del test
     */
    @Test
    void test_FattoreConversione_Identita() throws Exception {
        // Creazione della radice e dei nodi foglia
        NotLeaf nodo0 = creaNodoRadice(DefaultInitializer.ROOT_NAME_0, DefaultInitializer.ROOT_FIELD_0, DefaultInitializer.ROOT_DOMAIN_0);
        Leaf nodo00 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo01 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo10 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_10, DefaultInitializer.ROOT_NAME_0);

        // Aggiunta dei nodi foglia alla radice
        nodo0.addChild(nodo00);
        nodo0.addChild(nodo01);
        nodo0.addChild(nodo10);

        // Aggiunta dei fattori di conversione
        addFattoreConversione(nodo00, nodo01, DefaultInitializer.FACTOR_VAL_00);
        addFattoreConversione(nodo00, nodo10, DefaultInitializer.FACTOR_VAL_10);

        // Creazione dell'albero e esecuzione del test
        creaAlbero(nodo0);

        // Verifica del risultato atteso
        assertEquals(1.0, nodo00.fattoreFoglia(nodo00));
    }

    /**
     * Testa che il fattore di conversione tra foglie di uno stesso albero non sia corretto.
     * @throws Exception Se si verificano eccezioni durante l'esecuzione del test
     */
    @Test
    void test_FattoreNonCorretto_TraFoglieDiStessoAlbero() throws Exception {
        // Creazione della radice e dei nodi foglia
        NotLeaf nodo0 = creaNodoRadice(DefaultInitializer.ROOT_NAME_0, DefaultInitializer.ROOT_FIELD_0, DefaultInitializer.ROOT_DOMAIN_0);
        Leaf nodo00 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo01 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo10 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_10, DefaultInitializer.ROOT_NAME_0);

        // Aggiunta dei nodi foglia alla radice
        nodo0.addChild(nodo00);
        nodo0.addChild(nodo01);
        nodo0.addChild(nodo10);

        // Aggiunta dei fattori di conversione
        addFattoreConversione(nodo00, nodo01, DefaultInitializer.FACTOR_VAL_00);
        addFattoreConversione(nodo00, nodo10, DefaultInitializer.FACTOR_VAL_10);

        // Creazione dell'albero e esecuzione del test
        creaAlbero(nodo0);

        // Verifica del risultato atteso
        assertNotEquals(1 / DefaultInitializer.FACTOR_VAL_10 * DefaultInitializer.FACTOR_VAL_20, nodo01.fattoreFoglia(nodo10));
    }

    /**
     * Testa che il fattore di conversione tra foglie di alberi diversi non sia corretto.
     * @throws Exception Se si verificano eccezioni durante l'esecuzione del test
     */
    @Test
    void test_FattoreNonCorretto_TraFoglieDiAlberiDiversi() throws Exception {
        // Creazione della radice e dei nodi foglia per il primo albero
        NotLeaf nodo0 = creaNodoRadice(DefaultInitializer.ROOT_NAME_0, DefaultInitializer.ROOT_FIELD_0, DefaultInitializer.ROOT_DOMAIN_0);
        Leaf nodo00 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo01 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);

        // Aggiunta dei nodi foglia alla radice
        nodo0.addChild(nodo00);
        nodo0.addChild(nodo01);

        // Aggiunta del fattore di conversione nel primo albero
        addFattoreConversione(nodo00, nodo01, DefaultInitializer.FACTOR_VAL_00);

        // Creazione e aggiunta del secondo albero
        creaAlbero(nodo0);

        // Creazione della radice e dei nodi foglia per il secondo albero
        NotLeaf nodo1 = creaNodoRadice(DefaultInitializer.ROOT_NAME_1, DefaultInitializer.ROOT_FIELD_1, DefaultInitializer.ROOT_DOMAIN_1);
        Leaf nodo10 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_10, DefaultInitializer.ROOT_NAME_1);

        // Aggiunta del nodo foglia al secondo albero
        nodo1.addChild(nodo10);

        // Aggiunta del fattore di conversione nel secondo albero
        addFattoreConversione(nodo10, nodo01, DefaultInitializer.FACTOR_VAL_10);

        // Creazione del secondo albero
        creaAlbero(nodo1);

        // Verifica del risultato atteso
        assertNotEquals(1 / DefaultInitializer.FACTOR_VAL_00 * DefaultInitializer.FACTOR_VAL_10, nodo10.fattoreFoglia(nodo01));
    }

    //------------------------------------------------------------------------------------------
    //---------------------------------Boundary Value Analysis----------------------------------
    //------------------------------------------------------------------------------------------

    /**
     * Testa il fattore di conversione con il valore massimo consentito.
     * @throws Exception Se si verificano eccezioni durante l'esecuzione del test
     */
    @Test
    void test_FattoreConversione_LimiteSuperiore() throws Exception {
        // Creazione della radice e dei nodi foglia
        NotLeaf nodo0 = creaNodoRadice(DefaultInitializer.ROOT_NAME_0, DefaultInitializer.ROOT_FIELD_0, DefaultInitializer.ROOT_DOMAIN_0);
        Leaf nodo00 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo01 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);

        // Aggiunta dei nodi foglia alla radice
        nodo0.addChild(nodo00);
        nodo0.addChild(nodo01);

        // Aggiunta del fattore di conversione con il valore massimo
        addFattoreConversione(nodo00, nodo01, FattoriDiConversione.MAX_FATTORECONVERSIONE);

        // Creazione dell'albero e esecuzione del test
        creaAlbero(nodo0);

        // Verifica del risultato atteso
        assertEquals(FattoriDiConversione.MAX_FATTORECONVERSIONE, nodo00.fattoreFoglia(nodo01));
        assertEquals(FattoriDiConversione.MIN_FATTORECONVERSIONE, nodo01.fattoreFoglia(nodo00));
    }

    /**
     * Testa il fattore di conversione con il valore minimo consentito.
     * @throws Exception Se si verificano eccezioni durante l'esecuzione del test
     */
    @Test
    void test_FattoreConversione_LimiteInferiore() throws Exception {
        // Creazione della radice e dei nodi foglia
        NotLeaf nodo0 = creaNodoRadice(DefaultInitializer.ROOT_NAME_0, DefaultInitializer.ROOT_FIELD_0, DefaultInitializer.ROOT_DOMAIN_0);
        Leaf nodo00 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo01 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);

        // Aggiunta dei nodi foglia alla radice
        nodo0.addChild(nodo00);
        nodo0.addChild(nodo01);

        // Aggiunta del fattore di conversione con il valore minimo
        addFattoreConversione(nodo00, nodo01, FattoriDiConversione.MIN_FATTORECONVERSIONE);

        // Creazione dell'albero e esecuzione del test
        creaAlbero(nodo0);

        // Verifica del risultato atteso
        assertEquals(FattoriDiConversione.MIN_FATTORECONVERSIONE, nodo00.fattoreFoglia(nodo01));
        assertEquals(FattoriDiConversione.MAX_FATTORECONVERSIONE, nodo01.fattoreFoglia(nodo00));
    }

    /**
     * Testa che il fattore di conversione sia nullo quando non viene aggiunto nessun valore.
     * @throws Exception Se si verificano eccezioni durante l'esecuzione del test
     */
    @Test
    void test_FattoreConversione_Null() throws Exception {
        // Creazione della radice e dei nodi foglia
        NotLeaf nodo0 = creaNodoRadice(DefaultInitializer.ROOT_NAME_0, DefaultInitializer.ROOT_FIELD_0, DefaultInitializer.ROOT_DOMAIN_0);
        Leaf nodo00 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_00, DefaultInitializer.ROOT_NAME_0);
        Leaf nodo01 = creaNodoFoglia(DefaultInitializer.CHILD_NAME_01, DefaultInitializer.ROOT_NAME_0);

        // Aggiunta dei nodi foglia alla radice
        nodo0.addChild(nodo00);
        nodo0.addChild(nodo01);

        // Non aggiungiamo alcun fattore di conversione

        // Creazione dell'albero e esecuzione del test
        creaAlbero(nodo0);

        // Verifica del risultato atteso
        assertEquals(0, nodo00.fattoreFoglia(nodo01));
    }
}
