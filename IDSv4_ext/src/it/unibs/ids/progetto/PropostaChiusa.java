package it.unibs.ids.progetto;

import java.io.Serializable;

/**
 * La classe PropostaChiusa rappresenta una specifica implementazione di una proposta di scambio chiusa tra due prestazioni.
 */
public class PropostaChiusa extends Proposta implements Serializable {

    // Versione della classe per la serializzazione
    private static final long serialVersionUID = 1L;

    /**
     * Costruttore della classe PropostaChiusa che inizializza una proposta chiusa con i parametri specificati.
     * @param richiesta La richiesta di prestazione associata alla proposta.
     * @param offerta L'offerta di prestazione associata alla proposta.
     * @param ID L'identificatore univoco della proposta.
     * @param fruitore Il fruitore che ha creato la proposta.
     */
    public PropostaChiusa(Richiesta richiesta, Offerta offerta, int ID, Fruitore fruitore) {
        super(richiesta, offerta, ID, fruitore);
    }

    /**
     * Metodo override per impostare lo stato della proposta chiusa.
     * Nella classe PropostaChiusa, lo stato viene impostato come "Chiusa".
     */
    @Override
    public void setStatus() {
        this.status = "Chiusa";
    }

}
