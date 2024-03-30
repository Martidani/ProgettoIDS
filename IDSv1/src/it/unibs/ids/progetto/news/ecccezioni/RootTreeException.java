package it.unibs.ids.progetto.news.ecccezioni;

/**
 * Eccezione personalizzata per gestire il caso in cui un nodo 
 * non radice tenti di essere inserito in testa ad un albero,
 * il che non è consentito.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class RootTreeException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Costruttore predefinito per l'eccezione RootTreeException.
     * Viene chiamato quando un nodo non radice tenta di essere inserito in testa all'albero.
     */
    public RootTreeException() {
        super("Il nodo in testa all'albero deve essere radice");
    }
}
