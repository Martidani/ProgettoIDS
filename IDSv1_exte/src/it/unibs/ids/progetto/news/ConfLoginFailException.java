package it.unibs.ids.progetto.news;

/**
 * Eccezione personalizzata per gestire il caso 
 * in cui un tentativo di login da parte di un configuratore 
 * fallisca a causa di credenziali errate.
 */
public class ConfLoginFailException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Costruttore predefinito per l'eccezione ConfLoginFailException.
     * Viene chiamato quando un tentativo di login da parte di un configuratore fallisce a causa di credenziali errate.
     */
    public ConfLoginFailException() {
        super("Credenziali di accesso del configuratore non valide!");
    }
}
