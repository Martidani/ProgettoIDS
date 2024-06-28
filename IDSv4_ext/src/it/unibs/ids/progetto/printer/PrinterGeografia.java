package it.unibs.ids.progetto.printer;

import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.Geografia;

public class PrinterGeografia {

	private Geografia geografia ;
    
	public PrinterGeografia(Geografia geografia) {
        this.geografia = geografia;     
	}
	
    /**
     * Restituisce una rappresentazione testuale del comprensorio
     * con elencati i nomi dei comuni presenti.
     * 
     * @return String La rappresentazione testuale del comprensorio
     */
	private static String toStringComprensorio(Comprensorio comprensorio) {
        StringBuffer bf = new StringBuffer();
        bf.append(comprensorio.getNome() + "\n");
        for (String comune : comprensorio.getComprensorio()) {
            bf.append(" - " + comune);
            bf.append("\n");
        }
        
        return bf.toString();
    }
    
	
	/**
	 * Restituisce una rappresentazione testuale della geografia, includendo la lista dei comprensori.
	 * 
	 * @return Una stringa che rappresenta la geografia
	 */
	public String toStringGeografia() {
		StringBuffer bf = new StringBuffer();
		bf.append("\n");
		for (Comprensorio comprensorio : geografia.getComprensori()) {
			bf.append(toStringComprensorio(comprensorio));
			bf.append("\n");
		}
		
		return bf.toString();
	}
}
