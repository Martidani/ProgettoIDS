package it.unibs.ids.progetto.news;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.Albero;
import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.FattoriDiConversione;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.LeafHasChildrenException;
import it.unibs.ids.progetto.RootTreeException;

/**
 * Questa classe gestisce le operazioni relative alla configurazione dei dati.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class WorkConfController {

    /**
     * Metodo per aggiungere un comprensorio alla geografia.
     * 
     * @param geografia  L'oggetto Geografia utilizzato per aggiungere il comprensorio.
     */
    public static void creaComprensorio(Geografia geografia) {
        Comprensorio comprensorio = new Comprensorio();
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
     * Metodo per creare la gerarchia.
     * 
     * @param gerarchia  L'oggetto Gerarchia su cui costruire la gerarchia.
     * @throws RootTreeException Eccezione sollevata in caso di errore nella creazione della radice dell'albero.
     * @throws LeafHasChildrenException 
     */
    public static void creaGerarchia(Gerarchia gerarchia) throws RootTreeException, LeafHasChildrenException {
        ArrayList<Leaf> foglieAttuali = new ArrayList<>();
        NotLeaf root = creaRadice(gerarchia);
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
    public static void stampaGerarchia(Gerarchia gerarchia) {
        String ger = gerarchia.toString();
        System.out.println(ger);
    }

    /**
     * Metodo per creare la radice dell'albero.
     * 
     * @param gerarchia  L'oggetto Gerarchia su cui costruire l'albero.
     * @return           Il nodo radice creato.
     * @throws RootTreeException Eccezione sollevata se il nome radice esiste già nella gerarchia.
     */
    private static NotLeaf creaRadice(Gerarchia gerarchia) throws RootTreeException {
        String radice;
        do {
            radice = InputDati.leggiStringaNonVuota("Nome radice -> ");
        } while (gerarchia.verificaEsistenzaNomeRadice(radice));

        String campo = InputDati.leggiStringaNonVuota("Campo -> ");
        NotLeaf root = new NotLeaf(radice, true, campo);

        creaValoriDominio(root);

        return root;
    }

    /**
     * Metodo per aggiungere i valori del dominio a un nodo.
     * 
     * @param nodo  Il nodo a cui aggiungere i valori del dominio.
     */
    private static void creaValoriDominio(NotLeaf nodo) {
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
    private static void creaNodiFiglio(NotLeaf nodoParent, Gerarchia gerarchia, NotLeaf radice, ArrayList<Leaf> foglieAttuali) throws LeafHasChildrenException {
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
                nodoChild = new Leaf(nome);
                foglieAttuali.add((Leaf) nodoChild);
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
                creaNodiFiglio((NotLeaf) nodo, gerarchia, radice, foglieAttuali);
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
        NotLeaf nodoChild = new NotLeaf(nome, false, campo);

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
    private static void creaFattoriConversione(Gerarchia gerarchia, ArrayList<Leaf> foglieAttuali) throws LeafHasChildrenException {
        System.out.println("\nInserimento fattori di conversione:");
        do {
        	Leaf nodo1 = chiediFoglia("Foglia 1:", gerarchia);
        	Leaf nodo2 = chiediFoglia("Foglia 2:", gerarchia);

            double fattoreDiConversione = chiediFattoreConversione(gerarchia);

            boolean condizione = !foglieAttuali.contains(nodo1)
                    && !foglieAttuali.contains(nodo2);
            if (!condizione) {
                nodo1.addFattoreConversione(nodo2, fattoreDiConversione);
                nodo2.addFattoreConversione(nodo1, 1 / fattoreDiConversione);
            }
        } while (InputDati.yesOrNo("Vuoi continuare l'inserimento? "));

        FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
    }

    /**
     * Metodo per chiedere la foglia e la radice e ottenere il nodo corrispondente.
     * 
     * @param messaggio   Il messaggio da visualizzare.
     * @param gerarchia   L'oggetto Gerarchia su cui cercare il nodo.
     * @return            Il nodo corrispondente alla foglia e alla radice specificate.
     * @throws LeafHasChildrenException 
     */
    private static Leaf chiediFoglia(String messaggio, Gerarchia gerarchia) throws LeafHasChildrenException {
    	Leaf nodo;
        do {
            System.out.println(messaggio);
            String foglia = InputDati.leggiStringaNonVuota("  Nome -> ");
            String radice = InputDati.leggiStringaNonVuota("  Radice -> ");
            nodo = gerarchia.visualizzaFoglia(foglia, radice);
            
        } while (nodo == null);
        return nodo;
    }

    /**
     * Metodo per chiedere il fattore di conversione.
     * 
     * @param gerarchia   L'oggetto Gerarchia su cui verificare il fattore di conversione.
     * @return            Il fattore di conversione inserito.
     */
    private static double chiediFattoreConversione(Gerarchia gerarchia) {
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
    public static void stampaFattori(Gerarchia gerarchia) throws LeafHasChildrenException {
        String foglia = InputDati.leggiStringaNonVuota("Inserisci nome foglia: ");
        String radice = InputDati.leggiStringaNonVuota("Inserisci radice della gerarchia della foglia: ");
        Leaf nodo = gerarchia.visualizzaFoglia(foglia, radice);
        if (nodo == null)
            System.out.println("  Non è stata trovata nessuna corrispondenza");
        else
            System.out.println(nodo.toStringFactors());
    }
}
