package it.unibs.ids.progetto;

import java.io.Serializable;

/**
 * La classe PropostaRitirata rappresenta una specifica implementazione di una proposta di scambio ritirata tra due prestazioni.
 */
public class PropostaRitirata extends Proposta implements Serializable {

    // Versione della classe per la serializzazione
    private static final long serialVersionUID = 1L;

    /**
     * Costruttore della classe PropostaRitirata che inizializza una proposta ritirata con i parametri specificati.
     * @param richiesta La richiesta di prestazione associata alla proposta.
     * @param offerta L'offerta di prestazione associata alla proposta.
     * @param ID L'identificatore univoco della proposta.
     * @param fruitore Il fruitore che ha creato la proposta.
     */
    public PropostaRitirata(Richiesta richiesta, Offerta offerta, int ID, Fruitore fruitore) {
        super(richiesta, offerta, ID, fruitore);
    }

    /**
     * Metodo override per impostare lo stato della proposta ritirata.
     * Nella classe PropostaRitirata, lo stato viene impostato come "Ritirata".
     */
    @Override
    public void setStatus() {
        this.status = "Ritirata";
    }

}
