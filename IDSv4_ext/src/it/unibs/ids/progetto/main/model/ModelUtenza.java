package it.unibs.ids.progetto.main.model;

import java.io.Serializable;
import it.unibs.ids.progetto.Configuratore;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.PropostaAperta;
import it.unibs.ids.progetto.Utente;
import it.unibs.ids.progetto.Utenza;
import it.unibs.ids.progetto.servizi.DefaultInitializer;
import it.unibs.ids.progetto.servizi.FileManager;

/**
 * ModelUtenza gestisce le operazioni relative all'utenza all'interno dell'applicazione.
 */
public class ModelUtenza implements Serializable {

    private static final long serialVersionUID = 1L;
    private Utenza utenza;  // Oggetto che gestisce l'utenza

    /**
     * Costruttore della classe ModelUtenza.
     * Carica l'utenza da file, se disponibile, altrimenti inizializza con valori di default.
     */
    public ModelUtenza() {
        // Caricamento da file
        this.utenza = FileManager.caricaUtenza();
        
        if (utenza == null) {
            // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
            this.utenza = DefaultInitializer.getDefaultInitializer().getUtenza();
        } else {
            System.out.println("Lettura da file: " + FileManager.getUtenzaFile());
        }
    }

    /**
     * Restituisce l'oggetto Utenza gestito dal modello.
     * @return Oggetto Utenza gestito dal modello
     */
    public Utenza getUtenza() {
        return utenza;
    }

    /**
     * Aggiunge un utente di tipo Configuratore all'utenza.
     * @param configuratore Configuratore da aggiungere
     */
    public void addUtente(Configuratore configuratore) {
        utenza.addUtente(configuratore);
    }

    /**
     * Autentica un configuratore dato ID e password.
     * @param iD ID del configuratore da autenticare
     * @param pSSW Password del configuratore da autenticare
     * @return Oggetto Utente configuratore autenticato, se esiste; altrimenti null
     */
    public Utente autenticazioneConfiguratore(String iD, String pSSW) {
        return utenza.autenticazioneConfiguratore(iD, pSSW);
    }

    /**
     * Verifica l'esistenza di un ID utente nell'utenza.
     * @param iD ID utente da verificare
     * @return true se l'ID utente esiste, false altrimenti
     */
    public boolean verificaEsistenzaID(String iD) {
        return utenza.verificaEsistenzaID(iD);
    }

    /**
     * Salva lo stato attuale dell'utenza su file.
     */
    public void save() {
        FileManager.salvaSuFile(utenza);
    }

    /**
     * Aggiunge un utente di tipo Fruitore all'utenza.
     * @param fruitore Fruitore da aggiungere
     */
    public void addUtente(Fruitore fruitore) {
        utenza.addUtente(fruitore);
    }

    /**
     * Autentica un fruitore dato ID e password.
     * @param iD ID del fruitore da autenticare
     * @param pSSW Password del fruitore da autenticare
     * @return Oggetto Utente fruitore autenticato, se esiste; altrimenti null
     */
    public Utente autenticazioneFruitore(String iD, String pSSW) {
        return utenza.autenticazioneFruitore(iD, pSSW);
    }

    /**
     * Restituisce l'utente di sessione attualmente autenticato.
     * @return Utente di sessione attualmente autenticato
     */
    public Utente getUtenteDiSessione() {
        return utenza.getUtenteDiSessione();
    }

    /**
     * Imposta l'utente di sessione attualmente autenticato.
     * @param utente Utente di sessione da impostare
     */
    public void setUtenteDiSessione(Utente utente) {
        utenza.setUtenteDiSessione(utente);
    }

    /**
     * Aggiunge una proposta aperta al fruitore di sessione attualmente autenticato.
     * @param proposta Proposta aperta da aggiungere
     */
    public void addProposte(PropostaAperta proposta) {
        ((Fruitore) utenza.getUtenteDiSessione()).addProposte(proposta);
    }

}

