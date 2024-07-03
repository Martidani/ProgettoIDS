package it.unibs.ids.progetto;

import java.io.Serializable;

/**
 * La classe Proposta rappresenta un'astrazione di una proposta di scambio tra due prestazioni.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public abstract class Proposta implements Serializable {

    protected String status;
    protected Richiesta richiesta;
    protected Offerta offerta;
    private static double VALORE_ACCETTAZIONE_ORA_PARZIALE = 0.1;
    protected int ID;
    protected Fruitore fruitore;
    
    /**
     * Costruttore della classe Proposta che inizializza i campi principali della proposta.
     * @param richiesta La richiesta di prestazione associata alla proposta.
     * @param offerta L'offerta di prestazione associata alla proposta.
     * @param ID L'identificatore univoco della proposta.
     * @param fruitore Il fruitore che ha creato la proposta.
     */
    public Proposta(Richiesta richiesta, Offerta offerta, int ID, Fruitore fruitore) {
        this.richiesta = richiesta;
        this.offerta = offerta;
        this.ID = ID;
        this.fruitore = fruitore;

        setOfferta();
        setStatus();
    }
    
    /**
     * Metodo getter per ottenere l'identificatore univoco della proposta.
     * @return L'identificatore univoco della proposta.
     */
    public int getID() {
        return ID;
    }

    /**
     * Metodo setter per impostare l'identificatore univoco della proposta.
     * @param ID L'identificatore univoco della proposta da impostare.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Metodo getter per ottenere lo stato attuale della proposta.
     * @return Lo stato attuale della proposta.
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * Metodo getter per ottenere il fruitore che ha creato la proposta.
     * @return Il fruitore che ha creato la proposta.
     */
    public Fruitore getFruitore() {
        return fruitore;
    }

    /**
     * Metodo astratto per impostare lo stato della proposta.
     * Ogni classe concreta derivata da Proposta dovrà implementare questo metodo.
     */
    public abstract void setStatus();

    /**
     * Metodo getter per ottenere la richiesta di prestazione associata alla proposta.
     * @return La richiesta di prestazione associata alla proposta.
     */
    public Richiesta getRichiesta() {
        return richiesta;
    }

    /**
     * Metodo getter per ottenere l'offerta di prestazione associata alla proposta.
     * @return L'offerta di prestazione associata alla proposta.
     */
    public Offerta getOfferta() {
        return offerta;
    }

    /**
     * Metodo per impostare la durata della prestazione offerta in base al fattore di compatibilità tra le foglie delle prestazioni.
     */
    public void setOfferta() {
        int durata = this.richiesta.getDurata();
        Leaf foglia1 = this.richiesta.getFoglia();
        Leaf foglia2 = this.offerta.getFoglia();

        double fattore = foglia1.fattoreFoglia(foglia2);

        // Calcolo della durata dell'offerta basata sul fattore di compatibilità e sul valore di accettazione ora parziale
        this.offerta.setDurata((int) (Math.floor((fattore * durata) + VALORE_ACCETTAZIONE_ORA_PARZIALE)));
    }

}
