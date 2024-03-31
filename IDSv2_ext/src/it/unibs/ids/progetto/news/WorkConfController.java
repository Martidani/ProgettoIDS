package it.unibs.ids.progetto.news;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.Albero;
import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.FattoriDiConversione;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.RootTreeException;


/**
 * Questa classe gestisce le operazioni relative alla configurazione dei dati.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class WorkConfController implements WorkController {

	public WorkConfController() {
		super();

	}

	/**
     * Metodo per creare la gerarchia.
     * 
     * @param gerarchia  L'oggetto Gerarchia su cui costruire la gerarchia.
     * @throws RootTreeException Eccezione sollevata in caso di errore nella creazione della radice dell'albero.
     */
    public static void creaGerarchia(Gerarchia gerarchia) throws RootTreeException {
        ArrayList<Nodo> foglieAttuali = new ArrayList<>();
        Nodo root = creaRadice(gerarchia);
        creaNodiFiglio(root, gerarchia, root, foglieAttuali);
        gerarchia.addAlbero(new Albero(root));
        creaFattoriConversione(gerarchia, foglieAttuali);
    }

    /**
     * Metodo per stampare la geografia.
     * 
     * @param geografia  L'oggetto Geografia da stampare.
     */
    public static void stampaGeografia(Geografia geografia) {
        System.out.println(geografia.toString());
    }

    /**
     * Metodo per stampare la gerarchia.
     * 
     * @param gerarchia  L'oggetto Gerarchia da stampare.
     */
    public void navigaGerarchia(Gerarchia gerarchia) {
        String ger = gerarchia.toString();
        System.out.println(ger);
    }



    /**
     * Metodo per stampare i fattori di conversione di un nodo.
     * 
     * @param gerarchia  L'oggetto Gerarchia su cui visualizzare i fattori di conversione.
     */
    public static void stampaFattori(Gerarchia gerarchia) {
        String foglia = InputDati.leggiStringaNonVuota("Inserisci nome foglia: ");
        String radice = InputDati.leggiStringaNonVuota("Inserisci radice della gerarchia della foglia: ");
        Nodo nodo = gerarchia.visualizzaFoglia(foglia, radice);
        if (nodo == null)
            System.out.println("  Non è stata trovata nessuna corrispondenza");
        else
            System.out.println(nodo.toStringFactors());
    }
    

	/**
	 * Metodo per aggiungere un comprensorio.
	 * 
	 * @param gestioneUtenza  L'oggetto GestioneUtenza utilizzato per aggiungere il comprensorio.
	 */
	public static void creaComprensorio(Geografia geografia) {
		
		String nome;
		do {
			nome = InputDati.leggiStringaNonVuota("Nome: ");
		} while (geografia.verificaEsistenzaComprensorio(nome));
		
	    Comprensorio comprensorio = new Comprensorio(nome);
	    System.out.println("Inserisci comprensorio (Exit per uscire) ");
	    String comune;

	    do {
	        comune = InputDati.leggiStringaNonVuota("  comune -> ");
	        comprensorio.addComune(comune);
	    } while (!comune.equalsIgnoreCase("Exit"));

	    int size = comprensorio.getComprensorio().size();
	    comprensorio.getComprensorio().remove(size - 1);

	    geografia.addComprensorio(comprensorio);
	}

	
	/**
	 * Metodo per creare la radice dell'albero.
	 * 
	 * @param gerarchia L'oggetto Gerarchia utilizzato per verificare l'esistenza del nome radice.
	 * @return Il nodo radice creato.
	 */
	private static Nodo creaRadice(Gerarchia gerarchia) {
	    String radice;
	    do {
	        radice = InputDati.leggiStringaNonVuota("Nome radice -> ");
	    } while (gerarchia.verificaEsistenzaNomeRadice(radice));

	    String campo = InputDati.leggiStringaNonVuota("Campo -> ");
	    Nodo root = new Nodo(radice, true, campo);

	    creaValoriDominio(root);

	    return root;
	}
	/**
	 * Metodo per aggiungere i valori del dominio a un nodo.
	 * 
	 * @param nodo Il nodo a cui aggiungere i valori del dominio.
	 */
	private static void creaValoriDominio(Nodo nodo) {
	    int num = 0;
	    do {
	        num++;
	        String valoreDominio = InputDati.leggiStringaNonVuota(num + "' valore del dominio -> ");
	        if (InputDati.yesOrNo("  Vuoi inserire una descrizione di " + valoreDominio + "? ")) {
	            String descrizioneDominio = InputDati.leggiStringaNonVuota("Descrizione -> ");
	            nodo.addElementiDominio(valoreDominio, descrizioneDominio);
	        } else {
	            nodo.addElementiDominio(valoreDominio);
	        }
	    } while (InputDati.yesOrNo("Vuoi aggiugere un altro elemento al dominio? "));
	}
	/**
	 * Metodo per creare i figli di un nodo.
	 * 
	 * @param nodoParent     Il nodo genitore.
	 * @param gerarchia      L'oggetto Gerarchia utilizzato per gestire la struttura gerarchica.
	 * @param radice         La radice dell'albero gerarchico.
	 * @param foglieAttuali  La lista delle foglie attuali.
	 */
	private static void creaNodiFiglio(Nodo nodoParent, Gerarchia gerarchia, Nodo radice, ArrayList<Nodo> foglieAttuali) {
	    int numFigli = 0;
	    do {
	        numFigli++;
	        System.out.println("\n" + numFigli + "' figlio (di " + nodoParent.getNome() 
	        + " [" +nodoParent.getDominio(numFigli)+ "]): ");
      
	        String nome;
	        do {
	            nome = InputDati.leggiStringaNonVuota("Nome -> ");
	        } while (radice.verificaEsistenzaNome(nome));

	        boolean isFoglia = InputDati.yesOrNo("È foglia? ");
	        Nodo nodoChild;
	        if (isFoglia) {
	            nodoChild = new Nodo(nome);
	            foglieAttuali.add(nodoChild);
	        } else {
	            nodoChild = creaNonFoglia(nome);
	        }

	        try {
	            nodoParent.addChild(nodoChild);
	        } catch (Exception e) {
	            e.getMessage();
	        }
	    } while (numFigli < nodoParent.getDominio().size());

	    for (Nodo nodo : nodoParent.getChildren()) {
	        if (!nodo.isLeaf()) {
	            creaNodiFiglio(nodo, gerarchia, radice, foglieAttuali);
	        }
	    }
	}
	/**
	 * Metodo per creare un nodo non foglia.
	 * 
	 * @param nome  Il nome del nodo.
	 * @return      Il nodo non foglia creato.
	 */
	private static Nodo creaNonFoglia(String nome) {
	    String campo = InputDati.leggiStringaNonVuota("Campo -> ");
	    Nodo nodoChild = new Nodo(nome, false, campo);

	    creaValoriDominio(nodoChild);

	    return nodoChild;
	}

	
	/**
	 * Metodo per inserire i fattori di conversione tra nodi.
	 * 
	 * @param gerarchia       L'oggetto Gerarchia utilizzato per gestire la struttura gerarchica.
	 * @param foglieAttuali  La lista delle foglie attuali.
	 * @throws Exception     Eccezione in caso di problemi durante l'inserimento.
	 */
	private static void creaFattoriConversione(Gerarchia gerarchia, ArrayList<Nodo> foglieAttuali) {
        System.out.println("\nInserimento fattori di conversione:");
        do {
            Nodo nodo1 = chiediFoglia("Foglia 1:", gerarchia);
            Nodo nodo2 = chiediFoglia("Foglia 2:", gerarchia);

            double fattoreDiConversione = chiediFattoreConversione(gerarchia);

            boolean condizione = !foglieAttuali.contains(nodo1)
                    && !foglieAttuali.contains(nodo2);
            if (!condizione) {
                nodo1.addFattoreConversione(nodo2, fattoreDiConversione);
                nodo2.addFattoreConversione(nodo1, 1/fattoreDiConversione);
            }
        } while (InputDati.yesOrNo("Vuoi continuare l'inserimento? "));

        FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
	}
	/**
	 * Metodo per chiedere la foglia e la radice e ottenere il nodo corrispondente.
	 * 
	 * @param messaggio   Il messaggio da visualizzare.
	 * @param gerarchia   L'oggetto Gerarchia utilizzato per cercare il nodo.
	 * @return            Il nodo corrispondente alla foglia e alla radice specificate.
	 */
	private static Nodo chiediFoglia(String messaggio, Gerarchia gerarchia) {
	    Nodo nodo;
	    do {
	        System.out.println(messaggio);
	        String foglia = InputDati.leggiStringaNonVuota("  Nome -> ");
	        String radice = InputDati.leggiStringaNonVuota("  Radice -> ");
	        nodo = gerarchia.visualizzaFoglia(foglia, radice);
	    } while (nodo == null );
	    return nodo;
	}
	/**
	 * Metodo per chiedere il fattore di conversione.
	 * @param gerarchia   L'oggetto Gerarchia utilizzato per verificare il fattore di conversione.
	 * @return            Il fattore di conversione inserito.
	 */
	private static double chiediFattoreConversione(Gerarchia gerarchia) {
	    double fattoreDiConversione;
	    do {
	        fattoreDiConversione = InputDati.leggiDouble("Fattore di conversione -> ");
	    } while (!FattoriDiConversione.verificaFattoreConversione(fattoreDiConversione));
	    return fattoreDiConversione;
	}



}
