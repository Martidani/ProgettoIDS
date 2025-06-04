package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;

import it.unibs.ids.progetto.news.PropostaDiScambio;

/**
 * La classe Configuratore rappresenta un utente con privilegi di configurazione.
 * Estende la classe astratta Utente.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Fruitore extends Utente implements Serializable {

	
    private static final long serialVersionUID = 1L;
    public static final char TIPOUTENTE = 'f';
    
    private String indirizzo;
    private Comprensorio comprensorioAppartenenza;
    private ArrayList<PropostaDiScambio> proposte;
    
    /**
     * Costruttore della classe Fruitore.
     * Crea un nuovo fruitore con le credenziali specificate.
     * 
     * @param credenziali Le credenziali associate al fruitore
     */
    public Fruitore(Comprensorio comprensorioAppartenenza, Credenziali credenziali, String indirizzo) {
        super(TIPOUTENTE, credenziali);
        this.indirizzo = indirizzo;
        this.comprensorioAppartenenza = comprensorioAppartenenza;
        this.proposte = new ArrayList<PropostaDiScambio>();
        setIsDefinitivo(true);
    }

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Comprensorio getComprensorioAppartenenza() {
		return comprensorioAppartenenza;
	}

	public void setComprensorioAppartenenza(Comprensorio comprensorioAppartenenza) {
		this.comprensorioAppartenenza = comprensorioAppartenenza;
	}

	@Override
	public void setIsDefinitivo(boolean x) {
		this.credenziali.setDefinitive(x);
	}
 
	
	public ArrayList<PropostaDiScambio> getProposte() {
		return proposte;
	}

	public void addProposte(PropostaDiScambio proposta) {
		this.proposte.add(proposta);
	}


}