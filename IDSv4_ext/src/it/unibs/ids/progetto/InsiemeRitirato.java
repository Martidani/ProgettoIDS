package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe InsiemeRitirato rappresenta un insieme di proposte ritirate.
 * Implementa l'interfaccia Serializable per consentire la serializzazione degli oggetti.
 */
public class InsiemeRitirato implements Serializable {

    // Costante per la versione della classe serializzata
    private static final long serialVersionUID = 1L;

    // Lista delle proposte ritirate
    private List<PropostaRitirata> proposteRitirate;

    /**
     * Costruttore della classe che inizializza la lista delle proposte ritirate.
     */
    public InsiemeRitirato() {
        this.proposteRitirate = new ArrayList<>();
    }

    /**
     * Metodo getter per ottenere la lista delle proposte ritirate.
     * @return La lista delle proposte ritirate.
     */
    public List<PropostaRitirata> getProposteRitirate() {
        return proposteRitirate;
    }

    /**
     * Metodo per aggiungere una proposta ritirata all'insieme.
     * @param propostaRitirata La proposta ritirata da aggiungere.
     */
    public void addProposteRitirate(PropostaRitirata propostaRitirata) {
        this.proposteRitirate.add(propostaRitirata);
    }

}
