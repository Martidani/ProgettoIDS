package it.unibs.ids.progetto.news;

import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.PrestazioneOpera;

/**
 * La classe PropostaDiScambio rappresenta una proposta di scambio tra due prestazioni.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public abstract class Proposta {

    protected String status;
    protected PrestazioneOpera richiesta;
    protected PrestazioneOpera offerta;
    protected int ID;
    
    
    public Proposta(PrestazioneOpera richiesta, PrestazioneOpera offerta,int ID) {
        
        this.richiesta = richiesta;
        this.offerta = offerta;
        this.ID=ID;

        setOfferta();
        setStatus();
    }
    
    public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	
	
	public String getStatus() {
        return status;
    }
    
    
    public abstract void setStatus();

    /**
     * Restituisce la prestazione richiesta nella proposta di scambio.
     *
     * @return La prestazione richiesta.
     */
    public PrestazioneOpera getRichiesta() {
        return richiesta;
    }

    /**
     * Restituisce la prestazione offerta nella proposta di scambio.
     *
     * @return La prestazione offerta.
     */
    public PrestazioneOpera getOfferta() {
        return offerta;
    }

    /**
     * Imposta la durata della prestazione offerta in base al fattore di compatibilità tra le foglie delle prestazioni.
     */
    public void setOfferta() {
        int durata = this.richiesta.getDurata();
        Nodo foglia1 = this.richiesta.getFoglia();
        Nodo foglia2 = this.offerta.getFoglia();

        double fattore = foglia1.fattoreFoglia(foglia2);

        durata = (int) (fattore * durata);

        this.offerta.setDurata(durata);
    }
}