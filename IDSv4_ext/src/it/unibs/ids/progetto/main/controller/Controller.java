package it.unibs.ids.progetto.main.controller;

/**
 * L'interfaccia Controller definisce i metodi necessari per gestire le operazioni principali del controller.
 */
public interface Controller {

    /**
     * Metodo astratto per gestire la registrazione di un utente.
     */
    public abstract void registrazione();

    /**
     * Metodo astratto per gestire il login di un utente.
     * @return Il risultato del login, come un identificatore univoco dell'utente loggato o un valore che indica l'esito del tentativo di login.
     */
    public abstract int login();
    
}
