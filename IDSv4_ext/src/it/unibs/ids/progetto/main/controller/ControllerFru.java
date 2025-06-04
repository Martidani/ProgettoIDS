package it.unibs.ids.progetto.main.controller;

import it.unibs.ids.progetto.main.model.Model;

/**
 * La classe ControllerFru gestisce le operazioni relative al fruitore nel sistema.
 * Implementa l'interfaccia Controller per fornire metodi per la registrazione degli utenti e il login,
 * oltre a operazioni specifiche per la gestione della geografia, della navigazione nella gerarchia,
 * della proposta di scambio e della gestione delle proposte.
 */
public class ControllerFru implements Controller {

    // Controller per l'accesso del fruitore
    private ControllerFruAccess controllerFruAccess;

    // Controller per il lavoro del fruitore
    private ControllerFruWork controllerFruWork;

    /**
     * Costruttore della classe ControllerFru che inizializza i controller per l'accesso e il lavoro con il modello.
     * @param model Il modello del sistema da utilizzare per le operazioni relative al fruitore.
     */
    public ControllerFru(Model model) {
        this.controllerFruAccess = new ControllerFruAccess(model);
        this.controllerFruWork = new ControllerFruWork(model);
    }

    /**
     * Metodo per stampare la geografia del sistema utilizzando il controller specifico per il lavoro del fruitore.
     * @return Una stringa contenente la rappresentazione della geografia.
     */
    public String stampaGeografia() {
        return controllerFruWork.stampaGeografia().toString();
    }

    /**
     * Metodo per gestire la registrazione di un utente fruitore utilizzando il controller specifico per l'accesso.
     */
    @Override
    public void registrazione() {
        controllerFruAccess.registrazione();
    }

    /**
     * Metodo per gestire il login di un utente fruitore utilizzando il controller specifico per l'accesso.
     * @return Il risultato del login, come un identificatore univoco dell'utente loggato.
     */
    @Override
    public int login() {
        return controllerFruAccess.login();
    }

    /**
     * Metodo per navigare nella gerarchia del sistema utilizzando il controller specifico per il lavoro del fruitore.
     */
    public void navigaGerarchia() {
        controllerFruWork.navigaGerarchia();
    }

    /**
     * Metodo per proporre uno scambio utilizzando il controller specifico per il lavoro del fruitore.
     */
    public void proponiScambio() {
        controllerFruWork.proponiScambio();
    }

    /**
     * Metodo per visualizzare le proposte relative al fruitore utilizzando il controller specifico per il lavoro del fruitore.
     */
    public void visualizzaProposte() {
        controllerFruWork.visualizzaProposte();
    }

    /**
     * Metodo per ritirare le proposte del fruitore utilizzando il controller specifico per il lavoro del fruitore.
     */
    public void ritiraProposte() {
        controllerFruWork.ritiraProposte();
    }
}
