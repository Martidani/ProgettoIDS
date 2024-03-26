package it.unibs.ids.progetto.news;
import java.io.*;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Utenza;

/**
 * DataManager è una classe utilizzata per gestire il caricamento e il salvataggio dei dati da e verso file di testo.
 * Supporta il caricamento e il salvataggio di oggetti delle classi Gerarchia, Geografia e Utenza.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class FileManager {
    
	
	private static final String SAVE_G_ERR = "Errore durante il salvataggio della gerarchia su file: ";
	private static final String SAVE_U_ERR = "Errore durante il salvataggio dell' utenza su file: ";
	private static final String SAVE_GE_ERR = "Errore durante il salvataggio della geografia su file: ";
	
	private static final String UPLOAD_U_ERR = "Errore durante il caricamento dell' utenza: ";
	private static final String UPLOAD_G_ERR = "Errore durante il caricamento della gerarchia: ";
	private static final String UPLOAD_GE_ERR = "Errore durante il caricamento della geografia: ";
	
    private static final String UTENZA_FILE = "utenza.txt";
    private static final String GERARCHIA_FILE = "gerarchia.txt";
    private static final String GEOGRAFIA_FILE = "geografia.txt";

    
    /**
     * Restituisce il percorso del file utilizzato per la memorizzazione della gerarchia.
     * 
     * @return Il percorso del file della gerarchia
     */
    public static String getGerarchiaFile() {
        return GERARCHIA_FILE;
    }

    /**
     * Restituisce il percorso del file utilizzato per la memorizzazione dell' utenza.
     * 
     * @return Il percorso del file delL' utenza
     */
    public static String getUtenzaFile() {
        return UTENZA_FILE;
    }
    
    /**
     * Restituisce il percorso del file utilizzato per la memorizzazione della geografia.
     * 
     * @return Il percorso del file della geografia
     */
    public static String getGeografiaFile() {
        return GEOGRAFIA_FILE;
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
     * Carica l' utenza da un file di testo.
     * 
     * @return L' utenza caricata, null in caso di errore durante il caricamento.
     */
    public static Utenza caricaUtenza() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(getUtenzaFile()))) {
            return (Utenza) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(UPLOAD_U_ERR + e.getMessage());
            return null;
        }
    }

    /**
     * Salva l' utenza su un file di testo.
     * 
     * @param gestioneUtenza L' utenza da salvare.
     */
    public static void salvaSuFile(Utenza utenza) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(getUtenzaFile()))) {
            outputStream.writeObject(utenza);
        } catch (IOException e) {
            System.err.println(SAVE_U_ERR + e.getMessage());
        }
    }
    
    /**
     * Carica la geografia da un file di testo.
     * 
     * @return La geografia caricata, null in caso di errore durante il caricamento.
     */
    public static Geografia caricaGeografia() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(getGeografiaFile()))) {
            return (Geografia) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(UPLOAD_GE_ERR + e.getMessage());
            return null;
        }
    }

    /**
     * Salva la geografia su un file di testo.
     * 
     * @param geografia La geografia da salvare.
     */
    public static void salvaSuFile(Geografia geografia) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(getGeografiaFile()))) {
            outputStream.writeObject(geografia);
        } catch (IOException e) {
            System.err.println(SAVE_GE_ERR + e.getMessage());
        }
    }


}