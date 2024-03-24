package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;

public class Comprensorio implements Serializable {
	

	
	private ArrayList<String> comprensorio;
	


	public Comprensorio() {
		this.comprensorio = new ArrayList<>();
	}


	public ArrayList<String> getComprensorio() {
		return comprensorio;
	}

	public void addComune(String luogo) {
		this.comprensorio.add(luogo);
	}
	
	
	public String toString() {
		StringBuffer bf = new StringBuffer();
		for (String comune : comprensorio) {
			bf.append(" - " + comune);
			bf.append("\n");
		}
		
		return bf.toString();
	}
	
	
	
}
