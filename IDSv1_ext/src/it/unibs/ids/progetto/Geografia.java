package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe Geografia rappresenta l'insieme di 
 * tutti i comprensori del sistema
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class Geografia implements Serializable {

	
	private static final long serialVersionUID = 1L;
	// Lista dei comprensori geografici nel sistema
	private ArrayList<Comprensorio> comprensori;
	
    private static Geografia geografia;
    //singleton
    public static Geografia getGeografia() {
    	if (geografia == null)
    		geografia = new Geografia(); 
    	return geografia;
    }
    
	/**
	 * Costruttore della classe Geografia.
	 */
	private Geografia() {
		this.comprensori = new ArrayList<>();
	}
	
	
	/**
	 * Aggiunge un comprensorio alla lista dei comprensori geografici nel sistema.
	 * 
	 * @param comprensorio Il comprensorio da aggiungere
	 */
	public void addComprensorio(Comprensorio comprensorio) {
		this.comprensori.add(comprensorio);
	}
	
	/**
	 * Restituisce la lista dei comprensori geografici nel sistema.
	 * 
	 * @return La lista dei comprensori geografici
	 */
	public ArrayList<Comprensorio> getComprensori() {
		return comprensori;
	}
	
	/**
	 * Restituisce una rappresentazione testuale della geografia, includendo la lista dei comprensori.
	 * 
	 * @return Una stringa che rappresenta la geografia
	 */
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer();
		int num = 0;
		for (Comprensorio comprensorio : comprensori) {
			bf.append("C" + num + "\n");
			bf.append(comprensorio.toString());
			bf.append("\n");
			num++;
		}
		
		return bf.toString();
	}
	
	
}