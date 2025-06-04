package it.unibs.ids.progetto.servizi;
import java.io.*;

import it.unibs.ids.progetto.Commercio;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Utenza;

/**
 * FileManager è una classe utilizzata per gestire il caricamento e il salvataggio 
 * di oggetti delle classi Gerarchia, Geografia, Utenza e Commercio.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class FileManager {
	
    private static final String UTENZA_FILE = "utenza.txt";
    private static final String GERARCHIA_FILE = "gerarchia.txt";
    private static final String GEOGRAFIA_FILE = "geografia.txt";
    private static final String COMMERCIO_FILE = "commercio.txt";

    
    /**
     * Restituisce il percorso del file utilizzato per la memorizzazione della gerarchia.
     * 
     * @return Il file della gerarchia
     */
    public static File getGerarchiaFile() {
        return new File(GERARCHIA_FILE);
    }
    
    /**
     * Restituisce il percorso del file utilizzato per la memorizzazione del commercio.
     * 
     * @return Il file del commercio
     */
    public static File getCommercioFile() {
        return new File(COMMERCIO_FILE);
    }

    /**
     * Restituisce il percorso del file utilizzato per la memorizzazione dell' utenza.
     * 
     * @return Il file dell' utenza
     */
    public static File getUtenzaFile() {
        return new File(UTENZA_FILE);
    }
    
    /**
     * Restituisce il percorso del file utilizzato per la memorizzazione della geografia.
     * 
     * @return Il file della geografia
     */
    public static File getGeografiaFile() {
        return new File(GEOGRAFIA_FILE);
    }
	
    /**
     * Carica la gerarchia da un file di testo.
     * 
     * @return La gerarchia caricata, null in caso di errore durante il caricamento.
     */
    public static Gerarchia caricaGerarchia() {
    	return (Gerarchia) FileService.caricaSingoloOggetto(getGerarchiaFile());
    }

    /**
     * Salva la gerarchia su un file di testo.
     * 
     * @param gerarchia La gerarchia da salvare.
     */
    public static void salvaSuFile(Gerarchia gerarchia) {
    	FileService.salvaSingoloOggetto(getGerarchiaFile(), gerarchia);
    }

    /**
     * Carica l' utenza da un file di testo.
     * 
     * @return L' utenza caricata, null in caso di errore durante il caricamento.
     */
    public static Utenza caricaUtenza() {
        return (Utenza) FileService.caricaSingoloOggetto(getUtenzaFile());
    }

    /**
     * Salva l' utenza su un file di testo.
     * 
     * @param gestioneUtenza L' utenza da salvare.
     */
    public static void salvaSuFile(Utenza utenza) {
    	FileService.salvaSingoloOggetto(getUtenzaFile(), utenza);
    }
    
    /**
     * Carica la geografia da un file di testo.
     * 
     * @return La geografia caricata, null in caso di errore durante il caricamento.
     */
    public static Geografia caricaGeografia() {
        return (Geografia) FileService.caricaSingoloOggetto(getGeografiaFile());
    }

    /**
     * Salva la geografia su un file di testo.
     * 
     * @param geografia La geografia da salvare.
     */
    public static void salvaSuFile(Geografia geografia) {
    	FileService.salvaSingoloOggetto(getGeografiaFile(), geografia);
    }
    
    /**
     * Carica il commercio da un file di testo.
     * 
     * @return Il commercio caricato, null in caso di errore durante il caricamento.
     */
    public static Commercio caricaCommercio() {
    	return (Commercio) FileService.caricaSingoloOggetto(getCommercioFile());
    }
    
    /**
     * Salva il commercio su un file di testo.
     * 
     * @param Il commercio da salvare.
     */
    public static void salvaSuFile(Commercio commercio) {
    	FileService.salvaSingoloOggetto(getCommercioFile(), commercio);
    }


}