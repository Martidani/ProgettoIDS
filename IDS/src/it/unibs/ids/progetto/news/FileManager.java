package it.unibs.ids.progetto.news;
import java.io.*;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.GestioneUtenza;

/**
 * DataManager è una classe utilizzata per gestire il caricamento e il salvataggio dei dati da e verso file di testo.
 * Supporta il caricamento e il salvataggio di oggetti delle classi Gerarchia e GestioneUtenza.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class FileManager {
    
	
	private static final String SAVE_G_ERR = "Errore durante il salvataggio della gerarchia su file: ";
	private static final String SAVE_GU_ERR = "Errore durante il salvataggio della gestione utenza su file: ";
	private static final String UPLOAD_GU_ERR = "Errore durante il caricamento della gestione utenza: ";
	private static final String UPLOAD_G_ERR = "Errore durante il caricamento della gerarchia: ";
    private static final String GESTIONE_UTENZA_FILE = "gestioneUtenza.txt";
    private static final String GERARCHIA_FILE = "gerarchia.txt";

    
    /**
     * Restituisce il percorso del file utilizzato per la memorizzazione della gerarchia.
     * 
     * @return Il percorso del file della gerarchia
     */
    public static String getGerarchiaFile() {
        return GERARCHIA_FILE;
    }

    /**
     * Restituisce il percorso del file utilizzato per la memorizzazione della gestione utenza.
     * 
     * @return Il percorso del file della gestione utenza
     */
    public static String getGestioneUtenzaFile() {
        return GESTIONE_UTENZA_FILE;
    }
	
    /**
     * Carica la gerarchia da un file di testo.
     * 
     * @return La gerarchia caricata, null in caso di errore durante il caricamento.
     */
    public static Gerarchia caricaGerarchia() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(getGerarchiaFile()))) {
            return (Gerarchia) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(UPLOAD_G_ERR + e.getMessage());
            return null;
        }
    }

    /**
     * Salva la gerarchia su un file di testo.
     * 
     * @param gerarchia La gerarchia da salvare.
     */
    public static void salvaSuFile(Gerarchia gerarchia) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(getGerarchiaFile()))) {
            outputStream.writeObject(gerarchia);
        } catch (IOException e) {
            System.err.println(SAVE_G_ERR + e.getMessage());
        }
    }

    /**
     * Carica la gestione utenza da un file di testo.
     * 
     * @return La gestione utenza caricata, null in caso di errore durante il caricamento.
     */
    public static GestioneUtenza caricaGestioneUtenza() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(getGestioneUtenzaFile()))) {
            return (GestioneUtenza) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(UPLOAD_GU_ERR + e.getMessage());
            return null;
        }
    }

    /**
     * Salva la gestione utenza su un file di testo.
     * 
     * @param gestioneUtenza La gestione utenza da salvare.
     */
    public static void salvaSuFile(GestioneUtenza gestioneUtenza) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(getGestioneUtenzaFile()))) {
            outputStream.writeObject(gestioneUtenza);
        } catch (IOException e) {
            System.err.println(SAVE_GU_ERR + e.getMessage());
        }
    }


}