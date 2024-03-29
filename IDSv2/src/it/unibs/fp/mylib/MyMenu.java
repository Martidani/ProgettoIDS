package it.unibs.fp.mylib;

/**
 * Questa classe rappresenta un menu testuale generico a più voci.
 * Si suppone che la voce per uscire sia sempre associata alla scelta 0 
 * e sia presentata in fondo al menu.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class MyMenu {
	
	
    // Costanti per la formattazione del menu
    final private static String CORNICE = "-------------------------------------------------------------------";
    final private static String VOCE_USCITA = "0\tEsci";
    final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";

    private String titolo;
    private String[] voci;

    /**
     * Costruttore della classe MyMenu.
     * 
     * @param titolo Il titolo del menu
     * @param voci L'array di stringhe contenente le voci del menu
     */
    public MyMenu(String titolo, String[] voci) {
        this.titolo = titolo;
        this.voci = voci;
    }

    
    /**
     * Metodo per permettere all'utente di scegliere un'opzione dal menu.
     * 
     * @return Il numero dell'opzione scelta dall'utente
     */
    public int scegli() {
        stampaMenu();
        return InputDati.leggiInteroRange(RICHIESTA_INSERIMENTO, 0, voci.length);
    }

    /**
     * Metodo per stampare il menu a schermo.
     */
    public void stampaMenu() {
        System.out.println(CORNICE);
        System.out.println(titolo);
        System.out.println(CORNICE);
        for (int i = 0; i < voci.length; i++) {
            System.out.println((i + 1) + "\t" + voci[i]);
        }
        System.out.println();
        System.out.println(VOCE_USCITA);
        System.out.println();
    }


}