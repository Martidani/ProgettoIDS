package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Commercio gestisce le proposte commerciali aperte, chiuse e ritirate di un certo comprensorio.
 * 
 */
public class Commercio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int numeroProposte; // Numero totale di proposte commerciali
	private List<InsiemeAperto> insiemiAperti; // Lista di insiemi aperti di proposte commerciali
	private Fruitore fruitoreDiSessione; // Fruitore corrente della sessione
	
	private List<InsiemeChiuso> insiemiChiusi; // Lista di insiemi chiusi di proposte commerciali
	private InsiemeRitirato insiemeRitirato; // Insieme delle proposte ritirate
	
	/**
	 * Costruttore della classe Commercio.
	 * Inizializza le liste e il numero di proposte a 0.
	 */
	public Commercio() {
		super();
		this.numeroProposte = 0;
		this.insiemiAperti = new ArrayList<>();
		this.insiemiChiusi = new ArrayList<>();
		this.insiemeRitirato = new InsiemeRitirato();
	}
	
	/**
	 * Imposta il fruitore corrente della sessione.
	 * @param utenteDiSessione il fruitore da impostare.
	 */
	public void setFruitoreDiSessione(Fruitore utenteDiSessione) {
		this.fruitoreDiSessione = utenteDiSessione;
	}
	
	/**
	 * Restituisce il fruitore corrente della sessione.
	 * @return il fruitore di sessione.
	 */
	public Fruitore getFruitoreDiSessione() {
		return fruitoreDiSessione;
	}
	
	/**
	 * Restituisce l'insieme aperto corrente della sessione in base al comprensorio di appartenenza del fruitore.
	 * @return l'insieme aperto di sessione.
	 */
	public InsiemeAperto getInsiemeApertoDiSessione() {
		return getInsiemeAperto(fruitoreDiSessione.getComprensorioAppartenenza());
	}
	
	/**
	 * Restituisce il numero totale di proposte commerciali.
	 * @return il numero di proposte.
	 */
	public int numProposte() {
		setNumProposte();
		return numeroProposte;
	}
	
	/**
	 * Incrementa il numero totale di proposte commerciali.
	 */
	public void setNumProposte() {
		this.numeroProposte++;
	}
	
	/**
	 * Decrementa il numero totale di proposte commerciali.
	 */
	public void decrementaNumProposte() {
		this.numeroProposte--;
	}
	
	/**
	 * Restituisce la lista degli insiemi aperti.
	 * @return la lista degli insiemi aperti.
	 */
	public List<InsiemeAperto> getInsiemiAperti() {
		return insiemiAperti;
	}
	
	/**
	 * Restituisce l'insieme delle proposte ritirate.
	 * @return l'insieme ritirato.
	 */
	public InsiemeRitirato getInsiemeRitirato() {
		return insiemeRitirato;
	}

	/**
	 * Restituisce l'insieme aperto associato ad un certo comprensorio.
	 * Se non esiste, crea un nuovo insieme aperto.
	 * @param comprensorio il comprensorio di riferimento.
	 * @return l'insieme aperto corrispondente.
	 */
	public InsiemeAperto getInsiemeAperto(Comprensorio comprensorio) {
		for (InsiemeAperto insiemeAperto : insiemiAperti) {
			if (insiemeAperto.getComprensorio().getNome()
					.equals(comprensorio.getNome())) 
				return insiemeAperto;
		}
		InsiemeAperto insiemeAperto = new InsiemeAperto(comprensorio);
		insiemiAperti.add(insiemeAperto);
		return insiemeAperto;
	}

	/**
	 * Aggiunge un insieme aperto alla lista degli insiemi aperti.
	 * @param insiemeAperto l'insieme aperto da aggiungere.
	 */
	public void addInsiemiAperti(InsiemeAperto insiemeAperto) {
		this.insiemiAperti.add(insiemeAperto);
	}

	/**
	 * Restituisce la lista degli insiemi chiusi.
	 * @return la lista degli insiemi chiusi.
	 */
	public List<InsiemeChiuso> getInsiemiChiusi() {
		return insiemiChiusi;
	}

	/**
	 * Aggiunge un insieme chiuso alla lista degli insiemi chiusi.
	 * @param insiemeChiuso l'insieme chiuso da aggiungere.
	 */
	public void addInsiemiChiusi(InsiemeChiuso insiemeChiuso) {
		this.insiemiChiusi.add(insiemeChiuso);
	}
	
	/**
	 * Verifica se esiste una proposta aperta corrispondente a quella fornita in input.
	 * @param propostaApertaInput la proposta aperta da cercare.
	 * @return l'insieme aperto contenente la proposta se esiste, null altrimenti.
	 */
	public InsiemeAperto esistePropostaAperta(PropostaAperta propostaApertaInput) {
		for (InsiemeAperto insiemeAperto : insiemiAperti) {
			if (insiemeAperto.esistePropostaAperta(propostaApertaInput)) {
				return insiemeAperto;
			}
		}
		return null;
	}
	
	/**
	 * Ritira una proposta aperta specificata dal fruitore di sessione.
	 * @param propostaAperta la proposta aperta da ritirare.
	 */
	public void ritira(PropostaAperta propostaAperta) {
		InsiemeAperto insiemeAperto = this.getInsiemeAperto(fruitoreDiSessione.getComprensorioAppartenenza());
		InsiemeAperto insiemeApertoCopia = new InsiemeAperto(fruitoreDiSessione.getComprensorioAppartenenza());
		insiemeApertoCopia.addProposteAperte(insiemeAperto.getProposteAperte());
		
		for (PropostaAperta propostaAperta2: insiemeApertoCopia.getProposteAperte()) {
			if (propostaAperta2.getFruitore().getID().equals(fruitoreDiSessione.getID())) {
				if (propostaAperta.getID() == propostaAperta2.getID()) {
					PropostaRitirata propostaRitirata = 
							new PropostaRitirata(propostaAperta.getRichiesta(), propostaAperta.getOfferta(),
							propostaAperta.getID(), propostaAperta.getFruitore());
					insiemeAperto.eliminaPropostaAperta(propostaAperta);
					this.insiemeRitirato.addProposteRitirate(propostaRitirata);
				}
			}
		}
	}
	
	/**
	 * Cerca una proposta aperta per ID all'interno dell'insieme aperto della sessione.
	 * @param ID l'ID della proposta aperta da cercare.
	 * @return la proposta aperta se trovata, null altrimenti.
	 */
	public PropostaAperta cercaProposta(int ID) {
		for (PropostaAperta propostaAperta : getInsiemeApertoDiSessione().getProposteAperte()) {
			if (propostaAperta.getID() == ID)
				return propostaAperta;		
		}
		return null;
	}
	
	/**
	 * Chiude le proposte aperte specificate e le aggiunge all'insieme chiuso.
	 * @param insiemeAperto l'insieme aperto contenente le proposte da chiudere.
	 * @param proposteAperte la lista delle proposte aperte da chiudere.
	 */
	private void chiudi(InsiemeAperto insiemeAperto, List<PropostaAperta> proposteAperte) {
		InsiemeChiuso insiemeChiuso = new InsiemeChiuso();
		
		for (PropostaAperta propostaAperta : proposteAperte) {
			PropostaChiusa propostaChiusa = new PropostaChiusa(propostaAperta.getRichiesta(), propostaAperta.getOfferta(), 
					propostaAperta.getID(), propostaAperta.getFruitore());
			
			insiemeChiuso.addProposteChiuse(propostaChiusa);
			insiemeAperto.eliminaPropostaAperta(propostaAperta);
		}
		
		insiemiChiusi.add(insiemeChiuso);
	}
	
	/**
	 * Cerca le proposte aperte chiudibili all'interno di un insieme aperto e le chiude.
	 * @param insiemeAperto l'insieme aperto in cui cercare le proposte chiudibili.
	 */
	public void cercaProposteChiudibili(InsiemeAperto insiemeAperto) {
		List<PropostaAperta> listaChiudibili = CommercioRegole.cercaProposteChiudibili(insiemeAperto);
		if (listaChiudibili != null)
			chiudi(insiemeAperto, listaChiudibili);
	}
}

