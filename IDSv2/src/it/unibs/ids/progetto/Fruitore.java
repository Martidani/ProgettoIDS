package it.unibs.ids.progetto;
import java.io.Serializable;

/**
 * La classe Configuratore rappresenta un utente con privilegi di configurazione.
 * Estende la classe astratta Utente.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Fruitore extends Utente implements Serializable {

	
    private static final long serialVersionUID = 1L;
    public static final char TIPOUTENTE = 'f';
    
    private String indirizzo;
    private Comprensorio comprensorioAppartenenza;
    
    /**
     * Costruttore della classe Fruitore.
     * Crea un nuovo fruitore con le credenziali specificate.
     * 
     * @param credenziali Le credenziali associate al fruitore
     */
    public Fruitore(Comprensorio comprensorioAppartenenza, Credenziali credenziali, String indirizzo) {
        super(TIPOUTENTE, credenziali);
        this.indirizzo = indirizzo;
        this.comprensorioAppartenenza = comprensorioAppartenenza;
        setIsDefinitivo(true);
    }

    /**
     * Restituisce l'indirizzo del fruitore.
     * 
     * @return L'indirizzo del fruitore
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Imposta l'indirizzo del fruitore.
     * 
     * @param indirizzo Il nuovo indirizzo del fruitore
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Restituisce il comprensorio a cui il fruitore appartiene.
     * 
     * @return Il comprensorio a cui il fruitore appartiene
     */
    public Comprensorio getComprensorioAppartenenza() {
        return comprensorioAppartenenza;
    }

    /**
     * Imposta il comprensorio a cui il fruitore appartiene.
     * 
     * @param comprensorioAppartenenza Il nuovo comprensorio a cui il fruitore appartiene
     */
    public void setComprensorioAppartenenza(Comprensorio comprensorioAppartenenza) {
        this.comprensorioAppartenenza = comprensorioAppartenenza;
    }

    /**
     * Imposta lo stato di definitività delle credenziali del fruitore.
     * 
     * @param x Il nuovo stato di definitività delle credenziali del fruitore
     */
    @Override
    public void setIsDefinitivo(boolean x) {
        this.credenziali.setDefinitive(x);
    }
}