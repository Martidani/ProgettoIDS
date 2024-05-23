package it.unibs.ids.progetto.news;

import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.NotLeaf;

public class NotLeafPrintManager{

	 public static String toNavigationString(NotLeaf nodo) {
	    	StringBuilder b = new StringBuilder();
	    	b.append(" nome: " + nodo.getNome());
	    	

	        b.append("\n |campo: " + nodo.getCampo());
	        b.append("\n |dominio: ");
	        int var = 0;
	        for (String[] valore : nodo.getDominio()) {
	        	var++;
	    		b.append("\n  " + var + " - " + valore[0]);
	    		if (valore.length>1)
	    			b.append(", " + valore [1]);
	    	}

	    	return b.toString();
	    }
	

		/**
		 * Genera una stringa del dominio associato al nodo.
		 * 
		 * @return Una stringa rappresentante il dominio associato al nodo.
		 */
		public static String toString(String blank,NotLeaf nodo) {
			StringBuffer bf = new StringBuffer();
			bf.append(" " + nodo.getNome() + "\n" );
			bf.append(blank + "campo: " + nodo.getCampo() + "\n" );
			bf.append(blank + toStringDomain(nodo) + "\n" );
			bf.append(blank + toStringChildren(nodo));
			
			return bf.toString();
		}
		

		/**
		 * Genera una stringa del dominio associato al nodo.
		 * 
		 * @return Una stringa rappresentante il dominio associato al nodo.
		 */
		private static String toStringDomain(NotLeaf nodo) {
			StringBuffer bf = new StringBuffer();
			bf.append("dominio: [");
			for (String[] elem : nodo.getDominio()) {
				bf.append("(valore: " + elem[0].toString());
				if (elem.length > 1)
					bf.append(", descrizione: " + elem[1].toString());
				bf.append(")");
			}
			bf.append("]");
			return bf.toString();
		}

		/**
		 * Genera una rappresentazione testuale dei figli del nodo.
		 * 
		 * @return Una stringa rappresentante i figli del nodo.
		 */
		private static String toStringChildren(NotLeaf nodo) {
			StringBuffer bf = new StringBuffer();
			int num = nodo.getChildren().size();
			if (num == 1)
				bf.append(num + " figlio: [");
			else
				bf.append(num + " figli: [");
			for (Nodo nodo2 : nodo.getChildren()) {
				bf.append("(" + nodo2.getNome());
				bf.append(")");
			}
			bf.append("]");
			return bf.toString();
		}

}
