package it.unibs.ids.progetto.main.model;

import java.io.Serializable;
import it.unibs.ids.progetto.Commercio;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.InsiemeAperto;
import it.unibs.ids.progetto.PropostaAperta;
import it.unibs.ids.progetto.Utente;
import it.unibs.ids.progetto.servizi.DefaultInitializer;
import it.unibs.ids.progetto.servizi.FileManager;

/**
 * ModelCommercio gestisce le operazioni relative al commercio all'interno dell'applicazione.
 */
public class ModelCommercio implements Serializable {

    private static final long serialVersionUID = 1L;
    private Commercio commercio;  // Oggetto che gestisce il commercio

    /**
     * Costruttore della classe ModelCommercio.
     * Carica il commercio da file, se disponibile, altrimenti inizializza con valori di default.
     */
    public ModelCommercio() {
        // Caricamento da file
        this.commercio = FileManager.caricaCommercio();
        
        if (commercio == null) {
            // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
            commercio = DefaultInitializer.getDefaultInitializer().getCommercio();
        } else {
            System.out.println("Lettura da file: " + FileManager.getCommercioFile());
        }
    }

    /**
     * Restituisce l'oggetto Commercio gestito dal modello.
     * @return Oggetto Commercio gestito dal modello
     */
    public Commercio getCommercio() {
        return commercio;
    }

    /**
     * Salva lo stato attuale del commercio su file.
     */
    public void save() {
        FileManager.salvaSuFile(commercio);
    }

    /**
     * Permette di ritirare una proposta aperta dal commercio.
     * @param proposta La proposta aperta da ritirare
     */
    public void ritira(PropostaAperta proposta) {
        commercio.ritira(proposta);
    }

    /**
     * Metodo che esegue un metodo specifico sul commercio utilizzando un insieme aperto.
     * @param insiemeAperto Insieme aperto su cui eseguire l'operazione
     */
    public void metodo(InsiemeAperto insiemeAperto) {
        commercio.cercaProposteChiudibili(insiemeAperto);
    }

    /**
     * Restituisce il numero di proposte attualmente gestite dal commercio.
     * @return Numero di proposte presenti nel commercio
     */
    public int numProposte() {
        return commercio.numProposte();
    }

    /**
     * Restituisce l'insieme aperto di sessione gestito dal commercio.
     * @return Insieme aperto di sessione
     */
    public InsiemeAperto getInsiemeApertoDiSessione() {
        return commercio.getInsiemeApertoDiSessione();
    }

    /**
     * Decrementa il numero di proposte gestite dal commercio.
     */
    public void decrementaNumProposte() {
        commercio.decrementaNumProposte();
    }

    /**
     * Cerca una proposta all'interno del commercio dato il suo identificativo.
     * @param s1 Identificativo della proposta da cercare
     * @return PropostaAperta corrispondente all'identificativo, se presente; altrimenti null
     */
    public PropostaAperta cercaProposta(int s1) {
        return commercio.cercaProposta(s1);
    }

    /**
     * Imposta l'utente di sessione attuale nel commercio.
     * @param utenteDiSessione Utente di sessione da impostare
     */
    public void setUtenteDiSessione(Utente utenteDiSessione) {
        if (utenteDiSessione.getTipoUtente() == Fruitore.TIPOUTENTE)
            commercio.setFruitoreDiSessione((Fruitore) utenteDiSessione);
        else
            commercio.setFruitoreDiSessione(null);
    }

}

