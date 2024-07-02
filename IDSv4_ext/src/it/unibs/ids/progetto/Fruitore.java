package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;

import it.unibs.ids.progetto.servizi.MailAddress;


/**
 * La classe Configuratore rappresenta un utente con privilegi di configurazione.
 * Estende la classe astratta Utente.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Fruitore extends Utente implements Serializable {

	
    private static final long serialVersionUID = 1L;
    public static final char TIPOUTENTE = 'f';
    
    private MailAddress indirizzo;
    private Comprensorio comprensorioAppartenenza;
    private ArrayList<Proposta> proposte;
    
    /**
     * Costruttore della classe Fruitore.
     * Crea un nuovo fruitore con le credenziali specificate.
     * 
     * @param credenziali Le credenziali associate al fruitore
     */
    public Fruitore(Comprensorio comprensorioAppartenenza, Credenziali credenziali, MailAddress indirizzo) {
        super(TIPOUTENTE, credenziali);
        this.indirizzo = indirizzo;
        this.comprensorioAppartenenza = comprensorioAppartenenza;
        this.proposte = new ArrayList<Proposta>();
        setIsDefinitivo(true);
    }

	public MailAddress getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(MailAddress indirizzo) {
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
 
	
	public ArrayList<Proposta> getProposte() {
		return proposte;
	}

	public void addProposte(Proposta proposta) {
		this.proposte.add(proposta);
	}


}