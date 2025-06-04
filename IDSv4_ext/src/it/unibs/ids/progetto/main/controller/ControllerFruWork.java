package it.unibs.ids.progetto.main.controller;

import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.InsiemeAperto;
import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.NotLeaf;
import it.unibs.ids.progetto.Offerta;
import it.unibs.ids.progetto.PropostaAperta;
import it.unibs.ids.progetto.Richiesta;
import it.unibs.ids.progetto.main.model.Model;
import it.unibs.ids.progetto.main.model.ModelCommercio;
import it.unibs.ids.progetto.main.model.ModelGerarchia;
import it.unibs.ids.progetto.main.model.ModelUtenza;
import it.unibs.ids.progetto.main.view.InputView;
import it.unibs.ids.progetto.servizi.printer.PrintManager;
import it.unibs.ids.progetto.servizi.printer.PrinterLeaf;
import it.unibs.ids.progetto.servizi.printer.PrinterNotLeaf;

/**
 * ControllerFruWork gestisce le operazioni principali dell'applicazione,
 * coordinando i modelli e la gestione dei dati.
 */
public class ControllerFruWork {

    private PrintManager printManager;  // Gestore di stampa per l'output

    private ModelUtenza modelUtenza;  // Modello per le operazioni legate agli utenti
    private ModelGerarchia modelGerarchia;  // Modello per la gerarchia dei nodi
    private ModelCommercio modelCommercio;  // Modello per le operazioni commerciali

    /**
     * Costruttore del controller che inizializza i modelli e il gestore di stampa.
     * @param model Il modello principale dell'applicazione
     */
    public ControllerFruWork(Model model) {
        super();
        this.modelUtenza = model.getModelUtenza();
        this.modelGerarchia = model.getModelGerarchia();
        this.modelCommercio = model.getModelCommercio();
        this.printManager = new PrintManager(model);
    }

    /**
     * Permette di navigare nella gerarchia dei nodi a partire dalla radice scelta.
     */
    public void navigaGerarchia() {
        String ger = printManager.toStringRadici();
        System.out.println(ger + "\n");

        String nomeRadice = InputView.leggiStringaNonVuota("Scegli radice -> ");
        NotLeaf radice = modelGerarchia.visualizzaRadice(nomeRadice);
        System.out.println(PrinterNotLeaf.toNavigationString(radice) + "\n");
        Nodo child;

        do {
            int valoreDominio = InputView.leggiIntero("Scegli l'opzione -> ");
            child = radice.getChildren().get(valoreDominio - 1);

            if (child.isLeaf())
                System.out.println(PrinterLeaf.toNavigationString(child) + "\n");
            else
                System.out.println(PrinterNotLeaf.toNavigationString(child) + "\n");

        } while (!child.isLeaf());
    }

    /**
     * Restituisce una stringa rappresentante la geografia dell'applicazione.
     * @return Una stringa rappresentante la geografia
     */
    public String stampaGeografia() {
        return printManager.toStringGeografia();
    }

    /**
     * Permette all'utente di proporre uno scambio commerciale.
     */
    public void proponiScambio() {
        InsiemeAperto insiemeAperto = modelCommercio.getInsiemeApertoDiSessione();

        Leaf fogliaRichiesta = inserimentoPrestazioneOpera(true);
        int durata = InputView.leggiInteroPositivo("Inserisci durata -> ");
        Leaf fogliaOfferta = inserimentoPrestazioneOpera(false);

        Offerta offerta = new Offerta(fogliaOfferta);
        Richiesta richiesta = new Richiesta(fogliaRichiesta, durata);
        Fruitore fruitore = (Fruitore) modelUtenza.getUtenteDiSessione();
        PropostaAperta proposta = new PropostaAperta(richiesta, offerta, modelCommercio.numProposte(), fruitore);

        System.out.println("\nOfferta: ");
        System.out.println("[" + offerta.getNome() + ", " + offerta.getDurata() + " ore]");

        if (InputView.yesOrNo("Confermi l'offerta?")) {
            modelUtenza.addProposte(proposta);
            insiemeAperto.addPropostaAperta(proposta);
            modelCommercio.metodo(insiemeAperto);
        } else {
            modelCommercio.decrementaNumProposte();
        }
    }

    /**
     * Metodo privato per l'inserimento di una foglia di prestazione opera.
     * @param modeFun True se è una richiesta, False se è un'offerta
     * @return La foglia di prestazione opera inserita
     */
    private Leaf inserimentoPrestazioneOpera(boolean modeFun) {
        String nomePrestazione;
        String nomeRadicePrestazione;
        Leaf foglia;
        do {
            if (modeFun)
                nomePrestazione = InputView.leggiStringaNonVuota("Inserisci richiesta [foglia di appartenenza] -> ");
            else
                nomePrestazione = InputView.leggiStringaNonVuota("Inserisci offerta [foglia di appartenenza] -> ");

            nomeRadicePrestazione = InputView.leggiStringaNonVuota("Inserisci radice -> ");
            foglia = modelGerarchia.visualizzaFoglia(nomePrestazione, nomeRadicePrestazione);
        } while (foglia == null);
        return foglia;
    }

    /**
     * Visualizza le proposte aperte, chiuse e ritirate.
     */
    public void visualizzaProposte() {
        System.out.println();
        String proposteA = printManager.toStringProposteAperte();
        String proposteC = printManager.toStringProposteChiuse();
        String proposteR = printManager.toStringProposteRitirate();
        if (!proposteA.isBlank()) {
            System.out.println("\nProposte Aperte: \n{" + proposteA + "}\n");
        } else
            System.out.println("\n{\nNon ci sono Proposte Aperte!\n}\n");
        if (!proposteC.isBlank()) {
            System.out.println("\nProposte Chiuse: \n{" + proposteC + "}\n");
        } else
            System.out.println("\n{\nNon ci sono Proposte Chiuse!\n}\n");
        if (!proposteR.isBlank()) {
            System.out.println("\nProposte Ritirate: \n{" + proposteR + "}");
        } else
            System.out.println("\n{\nNon ci sono Proposte Ritirate!\n}\n");
    }

    /**
     * Permette di ritirare una proposta aperta.
     */
    public void ritiraProposte() {
        String proposte = printManager.toStringProposteAperte();

        int s1;
        PropostaAperta proposta;
        System.out.println("Proposte da ritirare: \n\n" + proposte);

        if (!proposte.isBlank() && InputView.yesOrNo("\nVuoi ritirare una proposta? \n")) {
            do {
                s1 = InputView.leggiInteroNonNegativo("\nInserisci ID proposta: ");

            } while ((proposta = modelCommercio.cercaProposta(s1)) == null);

            modelCommercio.ritira(proposta);
        } else
            System.out.println("Non ci sono proposte (aperte) ritirabili");
    }

}
