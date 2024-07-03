package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe InsiemeAperto rappresenta un insieme di proposte aperte all'interno di un comprensorio.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class InsiemeAperto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Comprensorio comprensorio;
	private List<PropostaAperta> proposteAperte;
	
	/**
	 * Costruttore della classe InsiemeAperto.
	 * 
	 * @param comprensorio Il comprensorio a cui appartengono le proposte aperte
	 */
	public InsiemeAperto(Comprensorio comprensorio) {
		this.comprensorio = comprensorio;
		this.proposteAperte = new ArrayList<>();
	}

	/**
	 * Restituisce il comprensorio a cui appartengono le proposte aperte.
	 * 
	 * @return Il comprensorio di appartenenza
	 */
	public Comprensorio getComprensorio() {
		return comprensorio;
	}

	/**
	 * Restituisce la lista delle proposte aperte.
	 * 
	 * @return La lista delle proposte aperte
	 */
	public List<PropostaAperta> getProposteAperte() {
		return proposteAperte;
	}

	/**
	 * Aggiunge una proposta aperta alla lista delle proposte aperte.
	 * 
	 * @param proposta La proposta aperta da aggiungere
	 */
	public void addPropostaAperta(PropostaAperta proposta) {
		this.proposteAperte.add(proposta);
	}
	
	/**
	 * Aggiunge una lista di proposte aperte alla lista delle proposte aperte.
	 * 
	 * @param proposteAperte La lista di proposte aperte da aggiungere
	 */
	public void addProposteAperte(List<PropostaAperta> proposteAperte) {
		for (PropostaAperta propostaAperta : proposteAperte) {
			this.addPropostaAperta(propostaAperta);
		}
	}

	/**
	 * Elimina una proposta aperta dalla lista delle proposte aperte.
	 * 
	 * @param propostaDaEliminare La proposta aperta da eliminare
	 */
	public void eliminaPropostaAperta(PropostaAperta propostaDaEliminare) {
		this.proposteAperte.remove(propostaDaEliminare);
	}
	
	/**
	 * Verifica se una proposta aperta è presente nella lista delle proposte aperte.
	 * 
	 * @param proposta La proposta aperta da verificare
	 * @return true se la proposta è presente, altrimenti false
	 */
	public boolean contains(PropostaAperta proposta) {
		return proposteAperte.contains(proposta);
	}
	
	/**
	 * Verifica se esiste una proposta aperta nella lista delle proposte aperte con lo stesso ID
	 * della proposta aperta passata come input.
	 * 
	 * @param propostaApertaInput La proposta aperta da cercare
	 * @return true se esiste una proposta aperta con lo stesso ID, altrimenti false
	 */
	public boolean esistePropostaAperta(PropostaAperta propostaApertaInput) {
		for (PropostaAperta propostaAperta : proposteAperte) {
			if (propostaAperta.getID() == propostaApertaInput.getID()) {
				return true;
			}
		}
		return false;
	}
}

