package it.unibs.ids.progetto;

import java.io.Serializable;

/**
 * La classe PrestazioneOpera rappresenta un'astrazione di una prestazione relativa a un'opera.
 */
public abstract class PrestazioneOpera implements Serializable {

    // Riferimento a una foglia dell'albero
    protected Leaf foglia;

    // Durata della prestazione
    protected int durata;

    /**
     * Metodo getter per ottenere la foglia dell'albero associata alla prestazione.
     * @return L'oggetto foglia associato alla prestazione.
     */
    public Leaf getFoglia() {
        return foglia;
    }

    /**
     * Metodo getter per ottenere la durata della prestazione.
     * @return La durata della prestazione in unità di tempo.
     */
    public int getDurata() {
        return durata;
    }

    /**
     * Metodo setter per impostare la durata della prestazione.
     * @param durata La durata della prestazione da impostare in unità di tempo.
     */
    public void setDurata(int durata) {
        this.durata = durata;
    }

    /**
     * Metodo getter per ottenere il nome della foglia associata alla prestazione.
     * @return Il nome della foglia associata alla prestazione.
     */
    public String getNome() {
        return foglia.getNome();
    }

}
