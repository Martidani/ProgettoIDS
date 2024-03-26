package it.unibs.ids.progetto.news;

public class LeafHasChildrenException extends Exception {
    
	private static final long serialVersionUID = 1L;

	public LeafHasChildrenException() {
        super("Le foglie non possono avere figli");
    }
	
	
	
}