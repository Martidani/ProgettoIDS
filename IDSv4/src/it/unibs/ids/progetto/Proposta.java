package it.unibs.ids.progetto;

import java.io.Serializable;

/**
 * La classe Proposta rappresenta una proposta di scambio tra due prestazioni.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public abstract class Proposta implements Serializable {

    protected String status;
    protected PrestazioneOpera richiesta;
    protected PrestazioneOpera offerta;
    protected int ID;
    protected Fruitore fruitore;
    
    
    /**
     * Costruttore della classe Proposta.
     * 
     * @param richiesta La prestazione richiesta nella proposta di scambio.
     * @param offerta La prestazione offerta nella proposta di scambio.
     * @param ID L'identificatore univoco della proposta.
     * @param fruitore Il fruitore che ha effettuato la proposta.
     */
    public Proposta(PrestazioneOpera richiesta, PrestazioneOpera offerta, int ID, Fruitore fruitore) {
        
        this.richiesta = richiesta;
        this.offerta = offerta;
        this.ID = ID;
        this.fruitore = fruitore;

        setOfferta();
        setStatus();
    }
    
    /**
     * Restituisce l'identificatore della proposta.
     * 
     * @return L'identificatore della proposta.
     */
    public int getID() {
		return ID;
	}

    /**
     * Imposta l'identificatore della proposta.
     * 
     * @param ID L'identificatore della proposta da impostare.
     */
	public void setID(int ID) {
		this.ID = ID;
	}

    /**
     * Restituisce lo stato attuale della proposta.
     * 
     * @return Lo stato attuale della proposta.
     */
	public String getStatus() {
        return status;
    }
    
    /**
     * Restituisce il fruitore che ha effettuato la proposta.
     * 
     * @return Il fruitore che ha effettuato la proposta.
     */
    public Fruitore getFruitore() {
    	return fruitore;
    }

    /**
     * Metodo astratto per impostare lo stato della proposta.
     */
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
    
    /**
     * Restituisce una rappresentazione testuale della proposta.
     * 
     * @return Una stringa che rappresenta la proposta.
     */
    public String toString() {
    	StringBuffer str = new StringBuffer();
    	
    	str.append("\nID: " + this.getID());
    	str.append("\n  Richiesta " + this.getRichiesta().toString());
    	str.append("\n  Offerta " + this.getOfferta().toString());
    	
		return str.toString();    	
    }
    
    
    
}
