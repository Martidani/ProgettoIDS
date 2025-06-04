package it.unibs.ids.progetto.servizi.printer;

import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.Utenza;

/**
 * La classe PrinterUtenza si occupa di fornire metodi per visualizzare informazioni relative a un oggetto Utenza.
 */
public class PrinterUtenza {

	private Utenza utenza;
    
	/**
	 * Costruttore della classe PrinterUtenza.
	 * @param utenza Oggetto di tipo Utenza da visualizzare.
	 */
	public PrinterUtenza(Utenza utenza) {
        this.utenza = utenza;     
	}
	
	/**
	 * Metodo statico per ottenere una rappresentazione in formato stringa di un oggetto Fruitore.
	 * @param fruitore Fruitore da rappresentare.
	 * @return Stringa rappresentante il Fruitore.
	 */
	public static String toStringFruitore(Fruitore fruitore) {
		StringBuffer str = new StringBuffer();
		str.append("\nUtente: " + fruitore.getID() + " - " + fruitore.getIndirizzo().getEmail() + "\n");
		return str.toString();
	}
	
}
