package it.unibs.ids.progetto.main.controller;

import it.unibs.ids.progetto.eccezioni.RootTreeException;
import it.unibs.ids.progetto.main.model.Model;

/**
 * La classe ControllerConf gestisce le operazioni relative alla configurazione e gestione del sistema.
 * Implementa l'interfaccia Controller per fornire metodi per la registrazione degli utenti e il login,
 * oltre a operazioni specifiche per la creazione del comprensorio, della gerarchia e la visualizzazione di dati.
 */
public class ControllerConf implements Controller {

    // Controller per l'accesso alle configurazioni
    private ControllerConfAccess controllerConfAccess;

    // Controller per il lavoro con le configurazioni
    private ControllerConfWork controllerConfWork;

    /**
     * Costruttore della classe ControllerConf che inizializza i controller per l'accesso e il lavoro con il modello.
     * @param model Il modello del sistema da utilizzare per le operazioni di configurazione.
     */
    public ControllerConf(Model model) {
        this.controllerConfAccess = new ControllerConfAccess(model);
        this.controllerConfWork = new ControllerConfWork(model);
    }

    /**
     * Metodo per creare un comprensorio utilizzando il controller specifico per il lavoro.
     */
    public void creaComprensorio() {
        controllerConfWork.creaComprensorio();
    }

    /**
     * Metodo per creare la gerarchia delle configurazioni utilizzando il controller specifico per il lavoro.
     * @throws RootTreeException Eccezione lanciata in caso di problemi con la radice dell'albero.
     */
    public void creaGerarchia() throws RootTreeException {
        controllerConfWork.creaGerarchia();
    }

    /**
     * Metodo per stampare la geografia del sistema utilizzando il controller specifico per il lavoro.
     * @return Una stringa contenente la rappresentazione della geografia.
     */
    public String stampaGeografia() {
        return controllerConfWork.stampaGeografia().toString();
    }

    /**
     * Metodo per stampare la gerarchia del sistema utilizzando il controller specifico per il lavoro.
     * @return Una stringa contenente la rappresentazione della gerarchia.
     */
    public String stampaGerarchia() {
        return controllerConfWork.stampaGerarchia().toString();
    }

    /**
     * Metodo per stampare i fattori del sistema utilizzando il controller specifico per il lavoro.
     * @return Una stringa contenente la rappresentazione dei fattori.
     */
    public String stampaFattori() {
        return controllerConfWork.stampaFattori().toString();
    }

    /**
     * Metodo per gestire la registrazione di un utente utilizzando il controller specifico per l'accesso.
     */
    @Override
    public void registrazione() {
        controllerConfAccess.registrazione();
    }

    /**
     * Metodo per gestire il login di un utente utilizzando il controller specifico per l'accesso.
     * @return Il risultato del login, come un identificatore univoco dell'utente loggato.
     */
    @Override
    public int login() {
        return controllerConfAccess.login();
    }

    /**
     * Metodo per visualizzare le proposte relative alle foglie del sistema utilizzando il controller specifico per il lavoro.
     * @return Una stringa contenente la rappresentazione delle proposte relative alle foglie.
     */
    public String visualizzaProposteFoglia() {
        return controllerConfWork.visualizzaProposteFoglia();
    }

    /**
     * Metodo per visualizzare gli insiemi chiusi nel sistema utilizzando il controller specifico per il lavoro.
     * @return Una stringa contenente la rappresentazione degli insiemi chiusi.
     */
    public String visualizzaInsiemiChiusi() {
        return controllerConfWork.visualizzaInsiemiChiusi();
    }

}
