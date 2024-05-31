package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe InsiemeChiuso rappresenta un insieme di proposte di scambio che sono state chiuse.
 */
public class InsiemeChiuso implements Serializable {

    private List<PropostaChiusa> proposteChiuse;

    /**
     * Costruttore della classe InsiemeChiuso.
     * Inizializza la lista delle proposte chiuse come vuota.
     */
    public InsiemeChiuso() {
        this.proposteChiuse = new ArrayList<>();
    }

    /**
     * Restituisce la lista delle proposte chiuse.
     *
     * @return La lista delle proposte chiuse.
     */
    public List<PropostaChiusa> getProposteChiuse() {
        return proposteChiuse;
    }

    /**
     * Aggiunge una proposta chiusa all'insieme.
     *
     * @param propostaChiusa La proposta chiusa da aggiungere.
     */
    public void addPropostaChiusa(PropostaChiusa propostaChiusa) {
        this.proposteChiuse.add(propostaChiusa);
    }

    /**
     * Restituisce una rappresentazione testuale dell'insieme di proposte chiuse.
     *
     * @return Una stringa che rappresenta l'insieme di proposte chiuse.
     */
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        for (PropostaChiusa propostaChiusa : proposteChiuse) {
            str.append(propostaChiusa.toString()).append("\n");
        }

        return str.toString();
    }
}
