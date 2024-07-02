package it.unibs.ids.progetto.servizi.printer;


import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.Utenza;

public class PrinterUtenza {

	private Utenza utenza ;
    
	public PrinterUtenza(Utenza utenza) {
        this.utenza = utenza;     
	}
	
	public static String toStringFruitore(Fruitore fruitore) {
		StringBuffer str = new StringBuffer();
		str.append("\nUtente: " + fruitore.getID() + " - " + ( fruitore).getIndirizzo().getEmail() + "\n");
		return str.toString();
	}
	
}
