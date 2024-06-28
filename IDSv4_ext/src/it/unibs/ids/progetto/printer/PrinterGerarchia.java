package it.unibs.ids.progetto.printer;

import it.unibs.ids.progetto.Albero;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.NotLeaf;

public class PrinterGerarchia {

	private Gerarchia gerarchia ;
    
	public PrinterGerarchia(Gerarchia gerarchia) {
        this.gerarchia = gerarchia;     
	}
	
	/**
     * Restituisce una rappresentazione testuale della 
     * gerarchia (di tutti i suoi alberi).
     * 
     * @return Una stringa che rappresenta la gerarchia
     */
	public String toStringGerarchia() {
    	StringBuffer builder = new StringBuffer();
        builder.append("\n");
        for (Nodo nodo : gerarchia.getRadici())  {
            try {
                builder.append(toStringAlbero(new Albero((NotLeaf) nodo)));
                builder.append("\n\n");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return builder.toString();     
    }
    
    
    /**
     * Restituisce una rappresentazione testuale della 
     * gerarchia (di tutti i suoi alberi).
     * 
     * @param alberi La lista degli alberi di nodi
     * @return Una stringa che rappresenta la gerarchia
     */
    public String toStringRadici() {
        StringBuffer bf = new StringBuffer();
        
        for (Nodo nodo : gerarchia.getRadici())  {
            bf.append("\n* " + nodo.getNome());

        }
        return bf.toString();
            
    }
    
    /**
     * Restituisce una rappresentazione testuale dell'albero.
     * 
     * @return Una stringa che rappresenta l'albero
     */
    private static String toStringAlbero(Albero albero) {
        StringBuffer bf = new StringBuffer();
        iterative(bf, albero.getRadice(), 1);
        
        return bf.toString();
    }

    /**
     * Metodo ausiliario ricorsivo per generare una rappresentazione testuale dell'albero.
     * 
     * @param bf Il buffer in cui aggiungere la rappresentazione
     * @param nodo Il nodo corrente
     * @param depth La profondità del nodo nella gerarchia
     */
    private static void iterative(StringBuffer bf, Nodo nodo, int depth) {
        String asterischi = "*".repeat(depth); // Genera una stringa di asterischi in base al grado di "figlio di"
        String blank = " ".repeat(depth + 1);
        if (nodo.isLeaf()) {
            bf.append(asterischi + " " + nodo.getNome());
        } else {
            bf.append(asterischi + PrinterNotLeaf.toString(blank,(NotLeaf)nodo));
            for (Nodo nodoChild : ((NotLeaf)nodo).getChildren()) {
                bf.append("\n");
                iterative(bf, nodoChild, depth + 1);
            }
        }
    }
}
