package it.unibs.ids.progetto;

import java.util.ArrayList;

public class Compresiorio {
	
	/**
	 * 
	 * Attributi 
	 * 
	 * ArrayList<String>
	 * 
	 */
	
	private ArrayList<String> comprensorio;
	
	
	

	public Compresiorio(ArrayList<String> comprensorio) {
	
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
