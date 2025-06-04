package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe InsiemeChiuso rappresenta un insieme di proposte chiuse.
 * 
 *
 */
public class InsiemeChiuso implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<PropostaChiusa> proposteChiuse;
	
	/**
	 * Costruttore della classe InsiemeChiuso.
	 * Inizializza una lista vuota di proposte chiuse.
	 */
	public InsiemeChiuso() {
		this.proposteChiuse = new ArrayList<>();
	}

	/**
	 * Restituisce la lista delle proposte chiuse.
	 * 
	 * @return La lista delle proposte chiuse
	 */
	public List<PropostaChiusa> getProposteChiuse() {
		return proposteChiuse;
	}

	/**
	 * Aggiunge una proposta chiusa alla lista delle proposte chiuse.
	 * 
	 * @param propostaChiusa La proposta chiusa da aggiungere
	 */
	public void addProposteChiuse(PropostaChiusa propostaChiusa) {
		this.proposteChiuse.add(propostaChiusa);
	}
}
