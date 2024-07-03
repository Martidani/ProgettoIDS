package it.unibs.ids.progetto;

import java.io.Serializable;

/**
 * La classe PropostaAperta rappresenta una specifica implementazione di una proposta di scambio aperta tra due prestazioni.
 */
public class PropostaAperta extends Proposta implements Serializable {

    // Versione della classe per la serializzazione
    private static final long serialVersionUID = 1L;

    /**
     * Costruttore della classe PropostaAperta che inizializza una proposta aperta con i parametri specificati.
     * @param richiesta La richiesta di prestazione associata alla proposta.
     * @param offerta L'offerta di prestazione associata alla proposta.
     * @param ID L'identificatore univoco della proposta.
     * @param fruitore Il fruitore che ha creato la proposta.
     */
    public PropostaAperta(Richiesta richiesta, Offerta offerta, int ID, Fruitore fruitore) {
        super(richiesta, offerta, ID, fruitore);
    }

    /**
     * Metodo override per impostare lo stato della proposta aperta.
     * Nella classe PropostaAperta, lo stato viene impostato come "Aperta".
     */
    @Override
    public void setStatus() {
        this.status = "Aperta";
    }

}
