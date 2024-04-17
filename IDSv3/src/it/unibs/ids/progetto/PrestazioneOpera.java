package it.unibs.ids.progetto;

/**
 * La classe PrestazioneOpera rappresenta una singola prestazione 
 * d'opera, che puo essere sia offerta che richiesta.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class PrestazioneOpera {

	private Nodo foglia;
	private int durata;
	
    /**
     * Costruttore che inizializza una PrestazioneOpera con una foglia e una durata specificate.
     *
     * @param foglia La foglia della gerarchia associata alla prestazione.
     * @param durata La durata della prestazione.
     * @throws NodeNotLeafException Se la foglia specificata non è una foglia valida.
     */
    public PrestazioneOpera(Nodo foglia, int durata) throws NodeNotLeafException {
        if (foglia.isLeaf()) {
            this.foglia = foglia;
            this.durata = durata;
        } else {
            throw new NodeNotLeafException();
        }
    }

    /**
     * Costruttore che inizializza una PrestazioneOpera con una foglia specificata e durata 0.
     *
     * @param foglia La foglia della gerarchia associata alla prestazione.
     * @throws NodeNotLeafException Se la foglia specificata non è una foglia valida.
     */
    public PrestazioneOpera(Nodo foglia) throws NodeNotLeafException {
        if (foglia.isLeaf()) {
            this.foglia = foglia;
        } else {
            throw new NodeNotLeafException();
        }
    }

    /**
     * Restituisce la foglia associata alla prestazione.
     *
     * @return La foglia associata alla prestazione.
     */
    public Nodo getFoglia() {
        return foglia;
    }

    /**
     * Restituisce la durata della prestazione.
     *
     * @return La durata della prestazione.
     */
    public int getDurata() {
        return durata;
    }

    /**
     * Imposta la durata della prestazione.
     *
     * @param durata La durata della prestazione da impostare.
     */
    public void setDurata(int durata) {
        this.durata = durata;
    }

    /**
     * Restituisce il nome della prestazione.
     *
     * @return Il nome della prestazione.
     */
    public String getNome() {
        return foglia.getNome();
    }
}