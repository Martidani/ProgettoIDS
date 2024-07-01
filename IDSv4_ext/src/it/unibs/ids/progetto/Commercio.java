package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Commercio implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private int numeroProposte;
	private List<InsiemeAperto> insiemiAperti;
	private Fruitore fruitoreDiSessione;
	
	private List<InsiemeChiuso> insiemiChiusi;
	private InsiemeRitirato insiemeRitirato;
	
	
	public Commercio() {
		super();
		this.numeroProposte = 0;
		this.insiemiAperti = new ArrayList<>();
		this.insiemiChiusi = new ArrayList<>();
		this.insiemeRitirato = new InsiemeRitirato();
	}
	
	
	public void setFruitoreDiSessione(Fruitore utenteDiSessione) {
		this.fruitoreDiSessione = utenteDiSessione;
	}
	

	public Fruitore getFruitoreDiSessione() {
		return fruitoreDiSessione;
	}
	
	public InsiemeAperto getInsiemeApertoDiSessione() {
		return getInsiemeAperto(fruitoreDiSessione.getComprensorioAppartenenza());
	}
	

	public int numProposte() {
		setNumProposte();
		return numeroProposte;
	}
	
	public void setNumProposte() {
		this.numeroProposte++;
	}
	public void decrementaNumProposte() {
		this.numeroProposte--;
	}
	
	public List<InsiemeAperto> getInsiemiAperti() {
		return insiemiAperti;
	}
	public InsiemeRitirato getInsiemeRitirato() {
		return insiemeRitirato;
	}


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
	public void addInsiemiAperti(InsiemeAperto insiemeAperto) {
		this.insiemiAperti.add(insiemeAperto);
	}
	public List<InsiemeChiuso> getInsiemiChiusi() {
		return insiemiChiusi;
	}
	public void addInsiemiChiusi(InsiemeChiuso insiemeChiuso) {
		this.insiemiChiusi.add(insiemeChiuso);
	}
	
	public InsiemeAperto esistePropostaAperta(PropostaAperta propostaApertaInput) {
		
		for (InsiemeAperto insiemeAperto : insiemiAperti) {
			if (insiemeAperto.esistePropostaAperta(propostaApertaInput)) {
				return insiemeAperto;
			}
		}
		
		return null;
	}
	
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
	
	public PropostaAperta cercaProposta (int ID) {
		for (PropostaAperta propostaAperta : getInsiemeApertoDiSessione().getProposteAperte()) {
			if (propostaAperta.getID() == ID)
				return propostaAperta;		
		}
		return null;
	}
	
	private void chiudi(InsiemeAperto insiemeAperto,List<PropostaAperta> proposteAperte) {
		
		InsiemeChiuso insiemeChiuso = new InsiemeChiuso();
		
		for (PropostaAperta propostaAperta : proposteAperte) {
			PropostaChiusa propostaChiusa = new PropostaChiusa(propostaAperta.getRichiesta(), propostaAperta.getOfferta(), 
					propostaAperta.getID(),propostaAperta.getFruitore());
			
			insiemeChiuso.addProposteChiuse(propostaChiusa);
			insiemeAperto.eliminaPropostaAperta(propostaAperta);
		}
		
		
		insiemiChiusi.add(insiemeChiuso);

		
		
	}
	
	public void cercaProposteChiudibili(InsiemeAperto insiemeAperto) {
		
	     List<PropostaAperta> listaChiudibili = CommercioRegole.cercaProposteChiudibili(insiemeAperto);
	     if (listaChiudibili != null)
	         chiudi(insiemeAperto, listaChiudibili);
	    
	}

	
    
  
	    
}
