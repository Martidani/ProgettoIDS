package it.unibs.ids.progetto.news;

/**
 * Eccezione personalizzata per gestire il caso in cui unafoglia 
 * tenti di aggiungere figli, il che non è consentito.
 */
public class RootTreeException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Costruttore predefinito per l'eccezione LeafHasChildrenException.
     * Viene chiamato quando una foglia tenta di aggiungere figli.
     */
    public RootTreeException() {
        super("Il nodo in testa all'albero deve essere radice");
    }
    
    
}