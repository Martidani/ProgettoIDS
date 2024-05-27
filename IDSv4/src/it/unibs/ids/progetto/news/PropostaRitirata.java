package it.unibs.ids.progetto.news;

import java.io.Serializable;

import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.PrestazioneOpera;

/**
 * La classe PropostaRitirata rappresenta una proposta di scambio che è stata ritirata.
 */
public class PropostaRitirata extends Proposta implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * Costruttore della classe PropostaRitirata.
     *
     * @param richiesta La prestazione richiesta nella proposta di scambio.
     * @param offerta La prestazione offerta nella proposta di scambio.
     * @param ID L'identificatore univoco della proposta.
     * @param fruitore Il fruitore che ha effettuato la proposta.
     */
    public PropostaRitirata(PrestazioneOpera richiesta, PrestazioneOpera offerta, int ID, Fruitore fruitore) {
        super(richiesta, offerta, ID, fruitore);
    }

    /**
     * Imposta lo stato della proposta come "Ritirata".
     */
    @Override
    public void setStatus() {
        this.status = "Ritirata";
    }
}
