package it.unibs.ids.progetto.printer;

import it.unibs.fp.mylib.InputDati;

/**
 * Questa classe rappresenta un menu testuale generico a più voci.
 * Si suppone che la voce per uscire sia sempre associata alla scelta 0 
 * e sia presentata in fondo al menu.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class PrintMenu {
	
    private String titolo;
    private String[] voci;
   
    /**
     * Costruttore della classe MyMenu.
     * 
     * @param titolo Il titolo del menu
     * @param voci L'array di stringhe contenente le voci del menu
     */
    public PrintMenu(String titolo, String[] voci) {
        this.titolo = titolo;
        this.voci = voci;
    }
    
    
    //--------------------------------------------------------------------------------------------------------
    // formattazione dei menu base
    final private static String CORNICE = "-------------------------------------------------------------------";
    final private static String VOCE_USCITA = "0\tEsci";
    final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";

    public int scegli() {
        stampaMenu();
        return InputDati.leggiInteroRange(RICHIESTA_INSERIMENTO, 0, voci.length);
    }
    public void stampaMenu() {
        System.out.println(CORNICE);
        System.out.println(titolo);
        System.out.println(CORNICE);
        for (int i = 0; i < voci.length; i++) {
            System.out.println((i + 1) + "\t" + voci[i]);
        }
        System.out.println();
        System.out.println(VOCE_USCITA );
        System.out.println();
    }
    
    //-------------------------------------------------------------------------------------------------------- 
    // formattazione dei menu dell'applicazione
    final private static String[] vociSistema =  {"Configuratore","Fruitore"};
    final private static String[] vociAccesso =  {"Registrazione","Login"};
    final private static String[] vociC =  {"Introdurre comprensorio geografico", "Introdurre albero", 
    		"Visualizza comprensorio",  "Visualizza gerarchia", "Visualizza fattori di conversione", 
    		"Visualizza proposte", "Visualizza insiemi chiusi"};
	final private static String[] vociF = {"Naviga nella gerarchia", "Proponi uno scambio", 
			"Visualizza proposte", "Ritira Proposte"};
    
	
    public static PrintMenu menuSistema() {
    	return new PrintMenu("Menu sistema", vociSistema);
    }
    public static PrintMenu menuAccessoC() {
    	return new PrintMenu("Menu accesso configuratore", vociAccesso);
    }
    public static PrintMenu menuAccessoF() {
    	return new PrintMenu("Menu accesso fruitore", vociAccesso);
    }
    public static PrintMenu menuConfiguratore() {
    	return new PrintMenu("Menu principale configuratore", vociC);
    }
    public static PrintMenu menuFruitore() {
    	return new PrintMenu("Menu principale fruitore", vociF);
    }


}