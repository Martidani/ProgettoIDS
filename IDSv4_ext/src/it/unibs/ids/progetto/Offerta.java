package it.unibs.ids.progetto;

import java.io.Serializable;

/**
 * La classe Offerta rappresenta una specifica offerta di prestazione relativa a un'opera.
 * Estende la classe PrestazioneOpera
 */
public class Offerta extends PrestazioneOpera implements Serializable {

    /**
     * Costruttore della classe Offerta che inizializza la foglia dell'albero associata alla prestazione.
     * @param foglia La foglia dell'albero associata all'offerta di prestazione.
     */
    public Offerta(Leaf foglia) {
        this.foglia = foglia;
    }

}
