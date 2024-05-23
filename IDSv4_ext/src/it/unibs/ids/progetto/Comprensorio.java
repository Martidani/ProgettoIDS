package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe Comprensorio rappresenta un insieme di comuni.
 * Gli oggetti di questa classe contengono una lista di comuni
 * all'interno di un determinato territorio.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Comprensorio implements Serializable {

	
    private static final long serialVersionUID = 1L;
    private String nome;
    private ArrayList<String> comprensorio;
    
    /**
     * Costruttore della classe Comprensorio.
     * Crea un nuovo oggetto Comprensorio con una lista vuota di comuni.
     */
    public Comprensorio(String nome) {
        this.comprensorio = new ArrayList<>();
        this.nome = nome;
    }

    
    public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	/**
     * Restituisce la lista di comuni contenuti nel comprensorio.
     * 
     * @return ArrayList<String> La lista di comuni
     */
    public ArrayList<String> getComprensorio() {
        return comprensorio;
    }

    /**
     * Aggiunge un nuovo comune alla lista del comprensorio.
     * 
     * @param luogo Il nome del comune da aggiungere
     */
    public void addComune(String luogo) {
        this.comprensorio.add(luogo);
    }
    
   

}