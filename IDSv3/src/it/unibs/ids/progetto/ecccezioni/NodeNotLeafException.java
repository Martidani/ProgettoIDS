package it.unibs.ids.progetto.ecccezioni;


public class NodeNotLeafException extends Exception {
    
    private static final long serialVersionUID = 1L;

    
    public NodeNotLeafException() {
        super("Il nodo non e' foglia! ");
    }
}
