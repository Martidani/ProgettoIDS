package it.unibs.ids.progetto;

import java.util.ArrayList;

public class Comprensorio {
	
	/**
	 * 
	 * Attributi 
	 * 
	 * ArrayList<String>
	 * 
	 */
	
	private ArrayList<String> comprensorio;
	
	
	

	public Comprensorio(ArrayList<String> comprensorio) {
	
		this.comprensorio = new ArrayList<>();
	}

	public ArrayList<String> getComprensorio() {
		return comprensorio;
	}

	public void addComprensorio(String luogo) {
		this.comprensorio.add(luogo);
	}
	
	
	
	/**
	 * 
	 * 
	 * Metodi
	 * 
	 * Get e Setters per visualizzare/modifcare 
	 */

}
