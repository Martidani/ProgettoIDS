package it.unibs.ids.progetto.news.main.controller;

import java.util.ArrayList;
import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.Albero;
import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.FattoriDiConversione;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.RootTreeException;
import it.unibs.ids.progetto.news.LeafPrintManager;
import it.unibs.ids.progetto.news.main.model.Model;
import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.NotLeaf;

/**
 * Questa classe gestisce le operazioni relative alla configurazione dei dati.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class ControllerConfWork  {

	protected Model model;
	
	public ControllerConfWork (Model model) {
		super();
		this.model = model;
	}
	
    /**
     * Metodo per aggiungere un comprensorio alla geografia.
     * 
     * @param geografia  L'oggetto Geografia utilizzato per aggiungere il comprensorio.
     */
    public void creaComprensorio() {
		
		String nome;
		do {
			nome = InputDati.leggiStringaNonVuota("Nome: ");
		} while (model.verificaEsistenzaComprensorio(nome));
		
	    Comprensorio comprensorio = new Comprensorio(nome);

        System.out.println("Inserisci comprensorio (Exit per uscire) ");
        String comune;

        do {
            comune = InputDati.leggiStringaNonVuota("  comune -> ");
            comprensorio.addComune(comune);
        } while (!comune.equalsIgnoreCase("Exit"));

        int size = comprensorio.getComprensorio().size();
        comprensorio.getComprensorio().remove(size - 1);

        model.addComprensorio(comprensorio);
    }

    /**
     * Metodo per creare la gerarchia.
     * 
     * @param gerarchia  L'oggetto Gerarchia su cui costruire la gerarchia.
     * @throws RootTreeException Eccezione sollevata in caso di errore nella creazione della radice dell'albero.
     * @throws LeafHasChildrenException 
     */
    public  void creaGerarchia( ) throws RootTreeException {
        ArrayList<Leaf> foglieAttuali = new ArrayList<>();
        NotLeaf root = creaRadice();
        creaNodiFiglio(root, root, foglieAttuali);
        model.addAlbero(new Albero(root));
        creaFattoriConversione( foglieAttuali);
    }

    /**
     * Metodo per stampare la geografia.
     * 
     * @param geografia  L'oggetto Geografia da stampare.
     */
    public  String stampaGeografia( ) {
        return model.toStringGeografia().toString();
    }

    /**
     * Metodo per stampare la gerarchia.
     * 
     * @param gerarchia  L'oggetto Gerarchia da stampare.
     */
    public  String stampaGerarchia( ) {
        return model.toStringGerarchia().toString();
    }

    /**
     * Metodo per creare la radice dell'albero.
     * 
     * @param gerarchia  L'oggetto Gerarchia su cui costruire l'albero.
     * @return           Il nodo radice creato.
     * @throws RootTreeException Eccezione sollevata se il nome radice esiste già nella gerarchia.
     */
    private  NotLeaf creaRadice( ) throws RootTreeException {
        String radice;
        do {
            radice = InputDati.leggiStringaNonVuota("Nome radice -> ");
        } while (model.verificaEsistenzaNomeRadice(radice));

        String campo = InputDati.leggiStringaNonVuota("Campo -> ");
        NotLeaf root = new NotLeaf(radice, null, campo);

        creaValoriDominio(root);

        return root;
    }

    /**
     * Metodo per aggiungere i valori del dominio a un nodo.
     * 
     * @param nodo  Il nodo a cui aggiungere i valori del dominio.
     */
    private static  void creaValoriDominio(NotLeaf nodo) {
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
     * @param gerarchia      L'oggetto Gerarchia su cui costruire la gerarchia.
     * @param radice         La radice dell'albero gerarchico.
     * @param foglieAttuali  La lista delle foglie attuali.
     * @throws LeafHasChildrenException 
     */
    private  void creaNodiFiglio(NotLeaf nodoParent , NotLeaf radice, ArrayList<Leaf> foglieAttuali) {
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
                nodoChild = new Leaf(nome, radice.getNome());
                foglieAttuali.add((Leaf) nodoChild);
            } else {
                nodoChild = creaNonFoglia(nome, radice);
            }

            try {
                nodoParent.addChild(nodoChild);
            } catch (Exception e) {
                e.getMessage();
            }
        } while (numFigli < nodoParent.getDominio().size());

        for (Nodo nodo : nodoParent.getChildren()) {
            if (!nodo.isLeaf()) {
                creaNodiFiglio((NotLeaf) nodo, radice, foglieAttuali);
            }
        }
    }

    /**
     * Metodo per creare un nodo non foglia.
     * 
     * @param nome  Il nome del nodo.
     * @return      Il nodo non foglia creato.
     */
	private static Nodo creaNonFoglia(String nome, Nodo radice) {
	    String campo = InputDati.leggiStringaNonVuota("Campo -> ");
	    NotLeaf nodoChild = new NotLeaf(nome, radice.getNome(), campo);

	    creaValoriDominio(nodoChild);

	    return nodoChild;
	}

    /**
     * Metodo per inserire i fattori di conversione tra nodi.
     * 
     * @param gerarchia       L'oggetto Gerarchia su cui inserire i fattori di conversione.
     * @param foglieAttuali  La lista delle foglie attuali.
     * @throws LeafHasChildrenException 
     * @throws Exception     Eccezione in caso di problemi durante l'inserimento.
     */
    private  void creaFattoriConversione(ArrayList<Leaf> foglieAttuali) {
        System.out.println("\nInserimento fattori di conversione:");
        do {
        	Leaf nodo1 = chiediFoglia("Foglia 1:");
        	Leaf nodo2 = chiediFoglia("Foglia 2:");

            double fattoreDiConversione = chiediFattoreConversione();

            boolean condizione = !foglieAttuali.contains(nodo1)
                    && !foglieAttuali.contains(nodo2);
            if (!condizione) {
                nodo1.addFattoreConversione(nodo2, fattoreDiConversione);
                nodo2.addFattoreConversione(nodo1, 1 / fattoreDiConversione);
            }
        } while (InputDati.yesOrNo("Vuoi continuare l'inserimento? "));

        model.addTransitivoFattoreConversione();
    }

    /**
     * Metodo per chiedere la foglia e la radice e ottenere il nodo corrispondente.
     * 
     * @param messaggio   Il messaggio da visualizzare.
     * @param gerarchia   L'oggetto Gerarchia su cui cercare il nodo.
     * @return            Il nodo corrispondente alla foglia e alla radice specificate.
     * @throws LeafHasChildrenException 
     */
    private  Leaf chiediFoglia(String messaggio ) {
    	Leaf nodo;
        do {
            System.out.println(messaggio);
            String foglia = InputDati.leggiStringaNonVuota("  Nome -> ");
            String radice = InputDati.leggiStringaNonVuota("  Radice -> ");
            nodo = model.visualizzaFoglia(foglia, radice);
            
        } while (nodo == null);
        return nodo;
    }

    /**
     * Metodo per chiedere il fattore di conversione.
     * 
     * @param gerarchia   L'oggetto Gerarchia su cui verificare il fattore di conversione.
     * @return            Il fattore di conversione inserito.
     */
    private  double chiediFattoreConversione( ) {
        double fattoreDiConversione;
        do {
            fattoreDiConversione = InputDati.leggiDouble("Fattore di conversione -> ");
        } while (!FattoriDiConversione.verificaFattoreConversione(fattoreDiConversione));
        return fattoreDiConversione;
    }

    /**
     * Metodo per stampare i fattori di conversione di un nodo.
     * 
     * @param gerarchia  L'oggetto Gerarchia su cui visualizzare i fattori di conversione.
     * @throws LeafHasChildrenException 
     */
    public  String stampaFattori( )  {
    	StringBuffer str = new StringBuffer();
    	
        String foglia = InputDati.leggiStringaNonVuota("Inserisci nome foglia: ");
        String radice = InputDati.leggiStringaNonVuota("Inserisci radice della gerarchia della foglia: ");
        Leaf nodo = model.visualizzaFoglia(foglia, radice);
        if (nodo == null)
            str.append("  Non è stata trovata nessuna corrispondenza");
        else
            str.append(LeafPrintManager.toStringFactors(nodo));
        
        return str.toString();
    }


    /**
     * Metodo per stampare la gerarchia.
     * 
     * @param gerarchia  L'oggetto Gerarchia da stampare.
     */
    public String navigaGerarchia( ) {
        return model.toStringGerarchia().toString();
    }

	public String visualizzaProposteFoglia() {
		StringBuffer str = new StringBuffer();
		
		Nodo foglia = chiediFoglia("Foglia: ");
		String proposteA =model.visualizzaProposteAperte(foglia);
		String proposteC =model.visualizzaProposteChiuse(foglia);
		String proposteR =model.visualizzaProposteRitirate(foglia);
		
		System.out.println();
		if (!proposteA.isBlank()) {
			str.append(" Proposte Aperte: \n" + proposteA);
		}
		if (!proposteC.isBlank()) {
			str.append(" Proposte Chiuse: \n" + proposteC);
		}
		if (!proposteR.isBlank()) {
			str.append(" Proposte Ritirate: \n" + proposteR);
		}
		return str.toString();
	}
	

		
	
}
