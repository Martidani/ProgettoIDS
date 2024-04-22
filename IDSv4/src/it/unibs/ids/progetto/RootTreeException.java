package it.unibs.ids.progetto;

/**
 * Eccezione personalizzata per gestire il caso in cui un nodo 
 * non radice tenti di essere inserito in testa ad un albero,
 * il che non è consentito.
 */
public class RootTreeException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Costruttore predefinito per l'eccezione RootTreeException.
     * Viene chiamato quando una foglia tenta di aggiungere figli.
     */
    public RootTreeException() {
        super("Il nodo in testa all'albero deve essere radice");
    }
    
    
}
