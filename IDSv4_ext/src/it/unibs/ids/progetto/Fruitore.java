package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;

import it.unibs.ids.progetto.servizi.MailAddress;

/**
 * La classe Fruitore rappresenta un utente con privilegi di fruizione.
 * Estende la classe astratta Utente e implementa Serializable.
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
     * 
     * @param comprensorioAppartenenza il comprensorio di appartenenza del fruitore
     * @param credenziali le credenziali dell'utente
     * @param indirizzo l'indirizzo email del fruitore
     */
    public Fruitore(Comprensorio comprensorioAppartenenza, Credenziali credenziali, MailAddress indirizzo) {
        super(TIPOUTENTE, credenziali);
        this.indirizzo = indirizzo;
        this.comprensorioAppartenenza = comprensorioAppartenenza;
        this.proposte = new ArrayList<Proposta>();
        setIsDefinitivo(true);
    }

    /**
     * Restituisce l'indirizzo email del fruitore.
     * 
     * @return l'indirizzo email del fruitore
     */
    public MailAddress getIndirizzo() {
        return indirizzo;
    }

    /**
     * Imposta l'indirizzo email del fruitore.
     * 
     * @param indirizzo il nuovo indirizzo email del fruitore
     */
    public void setIndirizzo(MailAddress indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Restituisce il comprensorio di appartenenza del fruitore.
     * 
     * @return il comprensorio di appartenenza del fruitore
     */
    public Comprensorio getComprensorioAppartenenza() {
        return comprensorioAppartenenza;
    }

    /**
     * Imposta il comprensorio di appartenenza del fruitore.
     * 
     * @param comprensorioAppartenenza il nuovo comprensorio di appartenenza del fruitore
     */
    public void setComprensorioAppartenenza(Comprensorio comprensorioAppartenenza) {
        this.comprensorioAppartenenza = comprensorioAppartenenza;
    }

    /**
     * Imposta se le credenziali dell'utente sono definitive.
     * 
     * @param x true se le credenziali sono definitive, false altrimenti
     */
    @Override
    public void setIsDefinitivo(boolean x) {
        this.credenziali.setDefinitive(x);
    }

    /**
     * Restituisce la lista di proposte del fruitore.
     * 
     * @return la lista di proposte del fruitore
     */
    public ArrayList<Proposta> getProposte() {
        return proposte;
    }

    /**
     * Aggiunge una proposta alla lista di proposte del fruitore.
     * 
     * @param proposta la proposta da aggiungere
     */
    public void addProposte(Proposta proposta) {
        this.proposte.add(proposta);
    }
}
