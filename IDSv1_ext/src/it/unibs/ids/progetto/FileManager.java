package it.unibs.ids.progetto;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
	
    private static final String UTENZA_FILE = "utenza.JSON";
    private static final String GERARCHIA_FILE = "gerarchia.txt";
    private static final String GEOGRAFIA_FILE = "geografia.JSON";

    
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
        try (FileReader fileReader = new FileReader(getUtenzaFile())) {
            JSONParser parser = new JSONParser();
            JSONArray utenzaJSON = (JSONArray) parser.parse(fileReader);

            Utenza utenza = new Utenza();

            // Ciclo su ogni oggetto JSON che rappresenta un comprensorio
            for (Object obj : utenzaJSON) {
            	
                JSONObject utenteJSON = (JSONObject) obj;
                
        		String tipoUtente =  (String) utenteJSON.keySet().iterator().next();
        		JSONArray credenzialiJSON = (JSONArray) utenteJSON.get(tipoUtente);

        		String id = (String) credenzialiJSON.get(0);
        		String password =(String) credenzialiJSON.get(1);
        		Credenziali credenziali = new Credenziali(id, password);
                    
        		boolean tipo =  (boolean) credenzialiJSON.get(2);
        		credenziali.setDefinitive(tipo);

        		Utente utente = null;
        		if (tipoUtente.equals("c"))  {
        			utente = new Configuratore();
        			utente.setIsDefinitivo(tipo);
        			utente.setCredenziali(credenziali);
        			utenza.addUtente(utente);
        		}

            }

            return utenza;
        } catch (IOException | org.json.simple.parser.ParseException e) {
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
    	JSONArray utenzaJSON = new JSONArray();

        // Ciclo su ogni comprensorio
        for (Utente utente : utenza.getUtenti()) {
        	JSONObject utenteJSON = new JSONObject();
        	
        	JSONArray credenzialiJSON = new JSONArray();
        	Credenziali credenziali = utente.getCredenziali();
        	credenzialiJSON.add(credenziali.getID());
        	credenzialiJSON.add(credenziali.getPassword());
        	credenzialiJSON.add(credenziali.isDefinitive());
        	
        	utenteJSON.put(utente.getTipoUtente(), credenzialiJSON);
            
            utenzaJSON.add(utenteJSON);
        }
    	
    	
        try (FileWriter fileWriter = new FileWriter(getUtenzaFile())) {
            fileWriter.write(utenzaJSON.toJSONString());
        } catch (IOException e) {
            System.err.println(SAVE_U_ERR + e.getMessage());
        }
    }
    

    
    
    
    public static void salvaSuFile(Geografia geografia) {
    	JSONArray geografiaJSON = new JSONArray();

        // Ciclo su ogni comprensorio
        for (Comprensorio comprensorio : geografia.getComprensori()) {
        	JSONObject comprensorioJSON = new JSONObject();
        	
        	JSONArray comuniJSON = new JSONArray();
        	for (String comune : comprensorio.getComprensorio()) {
            	comuniJSON.add(comune);
        	}
            
            comprensorioJSON.put(comprensorio.getNome(), comuniJSON);
            
            geografiaJSON.add(comprensorioJSON);
        }

        try (FileWriter fileWriter = new FileWriter(getGeografiaFile())) {
            fileWriter.write(geografiaJSON.toJSONString());
        } catch (IOException e) {
            System.err.println(SAVE_GE_ERR + e.getMessage());
        }
        
    }    

    public static Geografia caricaGeografia() {
        try (FileReader fileReader = new FileReader(getGeografiaFile())) {
            JSONParser parser = new JSONParser();
            JSONArray geografiaJSON = (JSONArray) parser.parse(fileReader);

            Geografia geografia = new Geografia();

            // Ciclo su ogni oggetto JSON che rappresenta un comprensorio
            for (Object obj : geografiaJSON) {
                JSONObject comprensorioJSON = (JSONObject) obj;
                String nomeComprensorio = (String) comprensorioJSON.keySet().iterator().next();
                JSONArray comuniJSON = (JSONArray) comprensorioJSON.get(nomeComprensorio);

                Comprensorio comprensorio = new Comprensorio(nomeComprensorio);

                // Ciclo su ogni comune all'interno del comprensorio
                for (Object comuneObj : comuniJSON) {
                    String comune = (String) comuneObj;
                    comprensorio.addComune(comune);
                }

                geografia.addComprensorio(comprensorio);
            }

            return geografia;
        } catch (IOException | org.json.simple.parser.ParseException e) {
            System.err.println(UPLOAD_GE_ERR + e.getMessage());
            return null;
        }
    }


}