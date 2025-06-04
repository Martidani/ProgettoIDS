package it.unibs.ids.progetto;

import java.io.Serializable;

/**
 * La classe PropostaAperta rappresenta una proposta di scambio che è aperta e in attesa di risposta.
 */
public class PropostaAperta extends Proposta implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Costruttore della classe PropostaAperta.
     *
     * @param richiesta La prestazione richiesta nella proposta di scambio.
     * @param offerta La prestazione offerta nella proposta di scambio.
     * @param ID L'identificatore univoco della proposta.
     * @param fruitore Il fruitore che ha effettuato la proposta.
     */
    public PropostaAperta(PrestazioneOpera richiesta, PrestazioneOpera offerta, int ID, Fruitore fruitore) {
        super(richiesta, offerta, ID, fruitore);
    }

    /**
     * Imposta lo stato della proposta come "Aperta".
     */
    @Override
    public void setStatus() {
        this.status = "Aperta";
    }
}
