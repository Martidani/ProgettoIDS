package it.unibs.ids.progetto;

import java.io.Serializable;

/**
 * La classe Richiesta rappresenta una specifica richiesta di prestazione relativa a un'opera.
 * Estende la classe PrestazioneOpera
 */
public class Richiesta extends PrestazioneOpera implements Serializable {

    /**
     * Costruttore della classe Richiesta che inizializza la foglia dell'albero e la durata della prestazione.
     * @param foglia La foglia dell'albero associata alla richiesta di prestazione.
     * @param durata La durata della prestazione richiesta, espressa in unità di tempo.
     */
    public Richiesta(Leaf foglia, int durata) {
        this.foglia = foglia;
        this.durata = durata;
    }

}
