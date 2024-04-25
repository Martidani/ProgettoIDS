package it.unibs.ids.progetto.news.main.model;
import java.io.Serializable;
import it.unibs.ids.progetto.Albero;
import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.Configuratore;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Utente;
import it.unibs.ids.progetto.Utenza;
import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.NotLeaf;

public class Model implements Serializable {

    private static final long serialVersionUID = 1L;
	private ModelUtenza modelUtenza;
    private ModelGerarchia modelGerarchia;
    private ModelGeografia modelGeografia ;
    
	public Model() {
		super();
        this.modelUtenza = new ModelUtenza();
        this.modelGerarchia = new ModelGerarchia();
        this.modelGeografia = new ModelGeografia();
        
	}

	
	public Utenza getUtenza() {
		return modelUtenza.getUtenza();
	}
	public Gerarchia getGerarchia() {
		return modelGerarchia.getGerarchia();
	}
	public Geografia getGeografia() {
		return modelGeografia.getGeografia();
	}

	public void addUtente(Configuratore configuratore) {
		modelUtenza.addUtente(configuratore);
	}
	public Utente autenticazioneConfiguratore(String iD, String pSSW) {
		return modelUtenza.autenticazioneConfiguratore(iD, pSSW);
	}
	public boolean verificaEsistenzaID(String iD) {
		return modelUtenza.verificaEsistenzaID(iD);
	}

	public boolean verificaEsistenzaComprensorio(String nome) {
		return modelGeografia.verificaEsistenzaComprensorio(nome);
	}

	public void addComprensorio(Comprensorio comprensorio) {
		modelGeografia.addComprensorio(comprensorio);
	}
	public void addAlbero(Albero albero) {
		modelGerarchia.addAlbero(albero);
	}
	public String toStringGeografia() {
		return modelGeografia.toStringGeografia();
	}
	public String toStringGerarchia() {
		return modelGerarchia.toStringGerarchia();
	}
	public boolean verificaEsistenzaNomeRadice(String radice) {
		return modelGerarchia.verificaEsistenzaNomeRadice(radice);
	}
	public void addTransitivoFattoreConversione() {
		modelGerarchia.addTransitivoFattoreConversione();		
	}
	public Leaf visualizzaFoglia(String foglia, String radice) {
		return modelGerarchia.visualizzaFoglia(foglia, radice);
	}

	public String toStringRadici() {
		return modelGerarchia.toStringRadici().toString();
	}

	public NotLeaf visualizzaRadice(String nomeRadice) {
		return modelGerarchia.visualizzaRadice(nomeRadice);
	}

	public Comprensorio cercaComprensorio(String c) {
		return modelGeografia.cercaComprensorio(c);
	}

	public void addUtente(Fruitore fruitore) {
		modelUtenza.addUtente(fruitore);	
	}

	public Utente autenticazioneFruitore(String iD, String pSSW) {
		return modelUtenza.autenticazioneFruitore(iD, pSSW);
	}  
	
	public void save() {
		modelGerarchia.save();
        modelUtenza.save();
        modelGeografia.save();
	}
}