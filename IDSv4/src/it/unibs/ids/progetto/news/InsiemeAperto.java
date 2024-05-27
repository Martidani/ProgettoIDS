package it.unibs.ids.progetto.news;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.unibs.ids.progetto.Comprensorio;

/**
 * La classe InsiemeAperto rappresenta un insieme di proposte di scambio aperte e in attesa di risposta.
 */
public class InsiemeAperto implements Serializable {
    
    private Comprensorio comprensorio;
    private List<PropostaAperta> proposteAperte;

    /**
     * Costruttore della classe InsiemeAperto.
     *
     * @param comprensorio Il comprensorio associato all'insieme di proposte aperte.
     */
    public InsiemeAperto(Comprensorio comprensorio) {
        this.comprensorio = comprensorio;
        this.proposteAperte = new ArrayList<>();
    }

    /**
     * Restituisce il comprensorio associato all'insieme di proposte aperte.
     *
     * @return Il comprensorio associato all'insieme di proposte aperte.
     */
    public Comprensorio getComprensorio() {
        return comprensorio;
    }

    /**
     * Restituisce la lista delle proposte aperte.
     *
     * @return La lista delle proposte aperte.
     */
    public List<PropostaAperta> getProposteAperte() {
        return proposteAperte;
    }

    /**
     * Aggiunge una proposta aperta all'insieme.
     *
     * @param proposta La proposta aperta da aggiungere.
     */
    public void addPropostaAperta(PropostaAperta proposta) {
        this.proposteAperte.add(proposta);
    }

    /**
     * Aggiunge una lista di proposte aperte all'insieme.
     *
     * @param proposteAperte La lista di proposte aperte da aggiungere.
     */
    public void addProposteAperte(List<PropostaAperta> proposteAperte) {
        for (PropostaAperta propostaAperta : proposteAperte) {
            this.addPropostaAperta(propostaAperta);
        }
    }

    /**
     * Elimina una proposta aperta dall'insieme.
     *
     * @param propostaDaEliminare La proposta aperta da eliminare.
     */
    public void eliminaPropostaAperta(PropostaAperta propostaDaEliminare) {
        if (proposteAperte != null) 
            this.proposteAperte.remove(propostaDaEliminare);
    }

    /**
     * Verifica se una proposta aperta è presente nell'insieme.
     *
     * @param proposta La proposta aperta da verificare.
     * @return true se la proposta è presente, altrimenti false.
     */
    public boolean contains(PropostaAperta proposta) {
        return proposteAperte.contains(proposta);
    }

    /**
     * Verifica se esiste una proposta aperta con lo stesso identificatore nell'insieme.
     *
     * @param propostaApertaInput La proposta aperta da cercare.
     * @return true se esiste una proposta con lo stesso identificatore, altrimenti false.
     */
    public boolean esistePropostaAperta(PropostaAperta propostaApertaInput) {
        for (PropostaAperta propostaAperta : proposteAperte) {
            if (propostaAperta.getID() == propostaApertaInput.getID()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Restituisce una rappresentazione testuale dell'insieme di proposte aperte.
     *
     * @return Una stringa che rappresenta l'insieme di proposte aperte.
     */
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        for (PropostaAperta propostaAperta : proposteAperte) {
            str.append(propostaAperta.toString()).append("\n");
        }

        return str.toString();
    }
}
