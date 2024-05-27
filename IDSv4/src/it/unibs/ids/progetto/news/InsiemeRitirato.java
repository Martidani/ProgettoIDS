package it.unibs.ids.progetto.news;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe InsiemeRitirato rappresenta un insieme di proposte di scambio che sono state ritirate.
 */
public class InsiemeRitirato implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<PropostaRitirata> proposteRitirate;

    /**
     * Costruttore della classe InsiemeRitirato.
     * Inizializza la lista delle proposte ritirate come vuota.
     */
    public InsiemeRitirato() {
        this.proposteRitirate = new ArrayList<>();
    }

    /**
     * Restituisce la lista delle proposte ritirate.
     *
     * @return La lista delle proposte ritirate.
     */
    public List<PropostaRitirata> getProposteRitirate() {
        return proposteRitirate;
    }

    /**
     * Aggiunge una proposta ritirata all'insieme.
     *
     * @param propostaRitirata La proposta ritirata da aggiungere.
     */
    public void addPropostaRitirata(PropostaRitirata propostaRitirata) {
        this.proposteRitirate.add(propostaRitirata);
    }

    /**
     * Restituisce una rappresentazione testuale dell'insieme di proposte ritirate.
     *
     * @return Una stringa che rappresenta l'insieme di proposte ritirate.
     */
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        for (PropostaRitirata propostaRitirata : proposteRitirate) {
            str.append(propostaRitirata.toString()).append("\n");
        }

        return str.toString();
    }
}
