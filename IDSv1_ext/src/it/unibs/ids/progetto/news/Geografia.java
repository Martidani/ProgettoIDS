package it.unibs.ids.progetto.news;
import java.io.Serializable;
import java.util.ArrayList;
import it.unibs.ids.progetto.Comprensorio;

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
	private ArrayList<Comprensorio> geografia;
	
	/**
	 * Costruttore della classe Geografia.
	 */
	public Geografia() {
		this.geografia = new ArrayList<>();
	}
	
	
	/**
	 * Aggiunge un comprensorio alla lista dei comprensori geografici nel sistema.
	 * 
	 * @param comprensorio Il comprensorio da aggiungere
	 */
	public void addComprensorio(Comprensorio comprensorio) {
		this.geografia.add(comprensorio);
	}
	
	/**
	 * Restituisce la lista dei comprensori geografici nel sistema.
	 * 
	 * @return La lista dei comprensori geografici
	 */
	public ArrayList<Comprensorio> getGeografia() {
		return geografia;
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
		for (Comprensorio comprensorio : geografia) {
			bf.append("C" + num + "\n");
			bf.append(comprensorio.toString());
			bf.append("\n");
			num++;
		}
		
		return bf.toString();
	}
	
	
}