package it.unibs.ids.progetto.news.main;
import java.io.Serializable;
import it.unibs.ids.progetto.Albero;
import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.Configuratore;
import it.unibs.ids.progetto.DefaultInitializer;
import it.unibs.ids.progetto.FattoriDiConversione;
import it.unibs.ids.progetto.FileManager;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Utente;
import it.unibs.ids.progetto.Utenza;
import it.unibs.ids.progetto.news.Leaf;

public class Model implements Serializable {

    private static final long serialVersionUID = 1L;
	private Utenza utenza;
    private Gerarchia gerarchia;
    private Geografia geografia ;
    
	public Model() {
		super();
		
        // Caricamento da file
        this.utenza = FileManager.caricaUtenza();
        this.gerarchia = FileManager.caricaGerarchia();
        this.geografia = FileManager.caricaGeografia();
        
        if (utenza == null || gerarchia == null || geografia == null) {
            // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
            DefaultInitializer defaultInitializer = DefaultInitializer.getDefaultInitializer();
            gerarchia = defaultInitializer.getGerarchia();
            utenza = defaultInitializer.getUtenza();
            geografia = defaultInitializer.getGeografia();
        } else {
            System.out.println("Lettura da file: " + FileManager.getUtenzaFile() 
            + ", " + FileManager.getGerarchiaFile() + ", " + FileManager.getGeografiaFile());
        }
	}

	
	public Utenza getUtenza() {
		return utenza;
	}
	public Gerarchia getGerarchia() {
		return gerarchia;
	}
	public Geografia getGeografia() {
		return geografia;
	}

	public void addUtente(Configuratore configuratore) {
		utenza.addUtente(configuratore);
	}
	public Utente autenticazioneConfiguratore(String iD, String pSSW) {
		return utenza.autenticazioneConfiguratore(iD, pSSW);
	}
	public boolean verificaEsistenzaID(String iD) {
		return utenza.verificaEsistenzaID(iD);
	}

	public boolean verificaEsistenzaComprensorio(String nome) {
		return geografia.verificaEsistenzaComprensorio(nome);
	}

	public void addComprensorio(Comprensorio comprensorio) {
		geografia.addComprensorio(comprensorio);
	}
	public void addAlbero(Albero albero) {
		gerarchia.addAlbero(albero);
	}
	public char[] toStringGeografia() {
		geografia.toString();
		return null;
	}
	public String toStringGerarchia() {
		gerarchia.toString();
		return null;
	}
	public boolean verificaEsistenzaNomeRadice(String radice) {
		return gerarchia.verificaEsistenzaNomeRadice(radice);
	}
	public void addTransitivoFattoreConversione() {
        FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);		
	}
	public Leaf visualizzaFoglia(String foglia, String radice) {
		return gerarchia.visualizzaFoglia(foglia, radice);
	}

	public void save() {
        FileManager.salvaSuFile(gerarchia);
        FileManager.salvaSuFile(utenza);
        FileManager.salvaSuFile(geografia);
	}   	
}
