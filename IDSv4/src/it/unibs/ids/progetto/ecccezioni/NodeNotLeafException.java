package it.unibs.ids.progetto.ecccezioni;

/**
 * Eccezione lanciata quando un nodo non è una foglia.
 */
public class NodeNotLeafException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Costruttore della classe che imposta un messaggio predefinito.
     */
    public NodeNotLeafException() {
        super("Il nodo non è una foglia!");
    }
}
