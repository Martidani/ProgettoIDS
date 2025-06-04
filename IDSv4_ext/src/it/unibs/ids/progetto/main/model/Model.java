package it.unibs.ids.progetto.main.model;

import java.io.Serializable;
import it.unibs.ids.progetto.Commercio;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Utenza;

/**
 * Model rappresenta il modello principale dell'applicazione, gestisce i vari sottosistemi
 * come Utenti, Gerarchia, Geografia e Commercio.
 */
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;
    private ModelUtenza modelUtenza;  // Modello per la gestione degli utenti
    private ModelGerarchia modelGerarchia;  // Modello per la gestione della gerarchia
    private ModelGeografia modelGeografia;  // Modello per la gestione della geografia
    private ModelCommercio modelCommercio;  // Modello per la gestione del commercio

    /**
     * Costruttore di default che inizializza tutti i modelli necessari.
     */
    public Model() {
        super();
        this.modelUtenza = new ModelUtenza();
        this.modelGerarchia = new ModelGerarchia();
        this.modelGeografia = new ModelGeografia();
        this.modelCommercio = new ModelCommercio();
    }

    /**
     * Restituisce il modello per la gestione degli utenti.
     * @return ModelUtenza per la gestione degli utenti
     */
    public ModelUtenza getModelUtenza() {
        return modelUtenza;
    }

    /**
     * Restituisce il modello per la gestione della gerarchia.
     * @return ModelGerarchia per la gestione della gerarchia
     */
    public ModelGerarchia getModelGerarchia() {
        return modelGerarchia;
    }

    /**
     * Restituisce il modello per la gestione della geografia.
     * @return ModelGeografia per la gestione della geografia
     */
    public ModelGeografia getModelGeografia() {
        return modelGeografia;
    }

    /**
     * Restituisce il modello per la gestione del commercio.
     * @return ModelCommercio per la gestione del commercio
     */
    public ModelCommercio getModelCommercio() {
        return modelCommercio;
    }

    /**
     * Restituisce l'istanza di Utenza gestita dal modello.
     * @return Utenza gestita dal modello
     */
    public Utenza getUtenza() {
        return modelUtenza.getUtenza();
    }

    /**
     * Restituisce l'istanza di Gerarchia gestita dal modello.
     * @return Gerarchia gestita dal modello
     */
    public Gerarchia getGerarchia() {
        return modelGerarchia.getGerarchia();
    }

    /**
     * Restituisce l'istanza di Geografia gestita dal modello.
     * @return Geografia gestita dal modello
     */
    public Geografia getGeografia() {
        return modelGeografia.getGeografia();
    }

    /**
     * Restituisce l'istanza di Commercio gestita dal modello.
     * @return Commercio gestito dal modello
     */
    public Commercio getCommercio() {
        return modelCommercio.getCommercio();
    }

    /**
     * Salva lo stato di tutti i modelli contenuti nel Model.
     * Questo metodo richiama i metodi save() dei singoli modelli per effettuare il salvataggio.
     */
    public void save() {
        modelGerarchia.save();
        modelUtenza.save();
        modelGeografia.save();
        modelCommercio.save();
    }

}
