package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe Geografia rappresenta l'insieme di 
 * tutti i comprensori del sistema
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Geografia implements Serializable {

	
	private static final long serialVersionUID = 1L;
	// Lista dei comprensori geografici nel sistema
	private ArrayList<Comprensorio> comprensori;
	
    
	/**
	 * Costruttore della classe Geografia.
	 */
    public Geografia() {
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
	
	
	public Comprensorio cercaComprensorio (String nome) {
		
		for (Comprensorio comprensorio : comprensori) {
			if (comprensorio.getNome().equals(nome))
				return comprensorio;
		}
		return null;
	}
	
	public boolean verificaEsistenzaComprensorio (String nome) {
		if (cercaComprensorio(nome) == null)
			return false;
		return true;
	}
	
}