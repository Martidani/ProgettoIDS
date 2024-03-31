package it.unibs.ids.progetto;

/**
 * Eccezione personalizzata per gestire il caso in cui una foglia 
 * tenti di aggiungere figli, il che non è consentito.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class LeafHasChildrenException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Costruttore predefinito per l'eccezione LeafHasChildrenException.
     * Viene chiamato quando una foglia tenta di aggiungere figli.
     */
    public LeafHasChildrenException() {
        super("Le foglie non possono avere figli");
    }
}
