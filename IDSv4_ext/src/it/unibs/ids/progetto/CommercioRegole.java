package it.unibs.ids.progetto;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe CommercioRegole contiene metodi per la ricerca e la gestione delle proposte commerciali chiudibili
 * all'interno di un insieme aperto.
 */
public class CommercioRegole {

	/**
	 * Cerca le proposte chiudibili all'interno di un insieme aperto.
	 * @param insiemeAperto l'insieme aperto in cui cercare le proposte chiudibili.
	 * @return una lista di proposte aperte chiudibili, o null se non ve ne sono.
	 */
	public static List<PropostaAperta> cercaProposteChiudibili(InsiemeAperto insiemeAperto) {
	    List<PropostaAperta> lista;
	    for (PropostaAperta propostaAperta : insiemeAperto.getProposteAperte()) {
	        lista = algoritmo(insiemeAperto.getProposteAperte(), propostaAperta);
	        if (lista != null && !lista.isEmpty()) {
	            return lista;
	        }
	    }
	    return null;
	}

	/**
	 * Algoritmo per trovare proposte chiudibili a partire da una proposta aperta.
	 * @param proposteAperte la lista di tutte le proposte aperte.
	 * @param propostaAperta1 la proposta aperta da cui partire per la ricerca.
	 * @return una lista di proposte aperte chiudibili, o null se non ve ne sono.
	 */
	private static List<PropostaAperta> algoritmo(List<PropostaAperta> proposteAperte, PropostaAperta propostaAperta1) {
    	List<PropostaAperta> proposteChiudibili = new ArrayList<>(); 

    	for (PropostaAperta propostaAperta2 : proposteAperte) {
    		List<PropostaAperta> copia = new ArrayList<>();
    		copia.addAll(proposteAperte);
    		if (!propostaAperta2.equals(propostaAperta1)) {
    			if (soddisfacimentoTotale(propostaAperta1, propostaAperta2)) {
        			proposteChiudibili.add(propostaAperta2);
        			proposteChiudibili.add(propostaAperta1);
        			return proposteChiudibili;
        		} else if (soddisfacimento1(propostaAperta1, propostaAperta2)) {
        			 proposteChiudibili = proposteSoddisfacimento1(copia, propostaAperta1,
        					 propostaAperta2, new ArrayList<PropostaAperta>());
        		} else if (soddisfacimento2(propostaAperta1, propostaAperta2)) {
        			 proposteChiudibili = proposteSoddisfacimento2(copia, propostaAperta1,
        					 propostaAperta2, new ArrayList<PropostaAperta>());
        		}
        		if (proposteChiudibili != null) {
            		if (!proposteChiudibili.isEmpty()) {
            			proposteChiudibili.add(propostaAperta2);
            			return proposteChiudibili;
            		}
        		}
    		}
    	}
		return null;
    }

	/**
	 * Verifica se una proposta soddisfa la condizione di soddisfacimento1 rispetto ad un'altra proposta.
	 * @param propostaA la prima proposta.
	 * @param propostaB la seconda proposta.
	 * @return true se soddisfa la condizione di soddisfacimento1, false altrimenti.
	 */
	private static boolean soddisfacimento1(Proposta propostaA, Proposta propostaB) {
	    return propostaA.getOfferta().getFoglia().getNome().
	    		equals(propostaB.getRichiesta().getFoglia().getNome()) 
	    		&& propostaA.getOfferta().getFoglia().root.
	    		equals(propostaB.getRichiesta().getFoglia().root) 
	    		&& propostaA.getOfferta().getDurata() 
	    		== propostaB.getRichiesta().getDurata();
	}

	/**
	 * Verifica se una proposta soddisfa la condizione di soddisfacimento2 rispetto ad un'altra proposta.
	 * @param propostaA la prima proposta.
	 * @param propostaB la seconda proposta.
	 * @return true se soddisfa la condizione di soddisfacimento2, false altrimenti.
	 */
	private static boolean soddisfacimento2(Proposta propostaA, Proposta propostaB) {
	    return propostaA.getRichiesta().getFoglia().getNome().
	    		equals(propostaB.getOfferta().getFoglia().getNome()) 
	    		&& propostaA.getRichiesta().getFoglia().root.
	    		equals(propostaB.getOfferta().getFoglia().root) 
	    		&& propostaA.getRichiesta().getDurata() 
	    		== propostaB.getOfferta().getDurata();
	}

	/**
	 * Verifica se una proposta soddisfa sia la condizione di soddisfacimento1 che di soddisfacimento2 rispetto ad un'altra proposta.
	 * @param propostaA la prima proposta.
	 * @param propostaB la seconda proposta.
	 * @return true se soddisfa entrambe le condizioni, false altrimenti.
	 */
	private static boolean soddisfacimentoTotale(Proposta propostaA, Proposta propostaB) {
	    return soddisfacimento1(propostaA, propostaB) && soddisfacimento2(propostaA, propostaB);
	}

	/**
	 * Cerca le proposte che soddisfano la condizione di soddisfacimento1.
	 * @param proposteAperte la lista di tutte le proposte aperte.
	 * @param propostaOriginale la proposta originale da cui partire per la ricerca.
	 * @param propostaAperta1 la proposta aperta corrente.
	 * @param proposteChiudibili la lista delle proposte chiudibili trovate finora.
	 * @return una lista di proposte aperte chiudibili, o null se non ve ne sono.
	 */
	private static List<PropostaAperta> proposteSoddisfacimento1(
    		List<PropostaAperta> proposteAperte, PropostaAperta propostaOriginale, 
    		PropostaAperta propostaAperta1, List<PropostaAperta> proposteChiudibili) {

    	proposteAperte.remove(propostaAperta1);
		
		if (proposteAperte != null) {
    		if (!proposteAperte.isEmpty()) {
		    	for (PropostaAperta propostaAperta2 : proposteAperte) {
		        	if (!propostaAperta2.equals(propostaAperta1)) {
		        		if (soddisfacimento1(propostaAperta1, propostaAperta2)) {
		        			proposteChiudibili.add(propostaAperta2);
		        			if (propostaAperta2.equals(propostaOriginale)) 
		        				return proposteChiudibili;
		        			else {
		        		    	List<PropostaAperta> proposteAperteCopia = new ArrayList<PropostaAperta>();
		        		    	proposteAperteCopia.addAll(proposteAperte);
		        		    	return proposteChiudibili = proposteSoddisfacimento1(proposteAperteCopia, propostaOriginale,  
		        						propostaAperta2, proposteChiudibili);
		        			}		
		        		}
		        	}	
				}
    		}
    	}
		return null;   
}

	/**
	 * Cerca le proposte che soddisfano la condizione di soddisfacimento2.
	 * @param proposteAperte la lista di tutte le proposte aperte.
	 * @param propostaOriginale la proposta originale da cui partire per la ricerca.
	 * @param propostaAperta1 la proposta aperta corrente.
	 * @param proposteChiudibili la lista delle proposte chiudibili trovate finora.
	 * @return una lista di proposte aperte chiudibili, o null se non ve ne sono.
	 */
    private static List<PropostaAperta> proposteSoddisfacimento2(
    		List<PropostaAperta> proposteAperte, PropostaAperta propostaOriginale, 
    		PropostaAperta propostaAperta1, List<PropostaAperta> proposteChiudibili) {

    	proposteAperte.remove(propostaAperta1);
		
		if (proposteAperte != null) {
    		if (!proposteAperte.isEmpty()) {
		    	for (PropostaAperta propostaAperta2 : proposteAperte) {
		        	if (!propostaAperta2.equals(propostaAperta1)) {
		        		if (soddisfacimento2(propostaAperta1, propostaAperta2)) {
		        			proposteChiudibili.add(propostaAperta2);
		        			if (propostaAperta2.equals(propostaOriginale)) 
		        				return proposteChiudibili;
		        			else {
		        		    	List<PropostaAperta> proposteAperteCopia = new ArrayList<PropostaAperta>();
		        		    	proposteAperteCopia.addAll(proposteAperte);
		        		    	return proposteChiudibili = proposteSoddisfacimento2(proposteAperteCopia, propostaOriginale,  
		        						propostaAperta2, proposteChiudibili);
		        			}		
		        		}
		        	}	
				}
    		}
    	}
		return null;   
}
}

