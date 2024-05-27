package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unibs.ids.progetto.news.Proposta;

/**
 * La classe Fruitore rappresenta un utente con privilegi di configurazione.
 * Estende la classe astratta Utente.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Fruitore extends Utente implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final char TIPO_UTENTE = 'f'; // Costante che identifica il tipo di utente
    
    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"; // Pattern per la validazione dell'email
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    
    private String indirizzo; // Indirizzo del fruitore
    private Comprensorio comprensorioAppartenenza; // Comprensorio a cui il fruitore appartiene
    private ArrayList<Proposta> proposte; // Lista delle proposte di scambio del fruitore
    
    /**
     * Costruttore della classe Fruitore.
     * Crea un nuovo fruitore con le credenziali specificate.
     * 
     * @param comprensorioAppartenenza Il comprensorio a cui il fruitore appartiene.
     * @param credenziali              Le credenziali associate al fruitore.
     * @param indirizzo                L'indirizzo del fruitore.
     */
    public Fruitore(Comprensorio comprensorioAppartenenza, Credenziali credenziali, String indirizzo) {
        super(TIPO_UTENTE, credenziali);
        this.indirizzo = indirizzo;
        this.comprensorioAppartenenza = comprensorioAppartenenza;
        this.proposte = new ArrayList<>();
        setIsDefinitivo(true);
    }

    /**
     * Restituisce l'indirizzo del fruitore.
     * 
     * @return L'indirizzo del fruitore.
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Imposta l'indirizzo del fruitore.
     * 
     * @param indirizzo L'indirizzo da impostare per il fruitore.
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Restituisce il comprensorio di appartenenza del fruitore.
     * 
     * @return Il comprensorio di appartenenza del fruitore.
     */
    public Comprensorio getComprensorioAppartenenza() {
        return comprensorioAppartenenza;
    }

    /**
     * Imposta il comprensorio di appartenenza del fruitore.
     * 
     * @param comprensorioAppartenenza Il comprensorio da impostare come appartenenza del fruitore.
     */
    public void setComprensorioAppartenenza(Comprensorio comprensorioAppartenenza) {
        this.comprensorioAppartenenza = comprensorioAppartenenza;
    }

    /**
     * Restituisce le proposte di scambio del fruitore.
     * 
     * @return Le proposte di scambio del fruitore.
     */
    public ArrayList<Proposta> getProposte() {
        return proposte;
    }

    /**
     * Aggiunge una proposta di scambio alla lista delle proposte del fruitore.
     * 
     * @param proposta La proposta di scambio da aggiungere.
     */
    public void addProposte(Proposta proposta) {
        this.proposte.add(proposta);
    }

    /**
     * Imposta lo stato delle credenziali del fruitore come definitivo o no.
     * 
     * @param x True se le credenziali sono definitive, False altrimenti.
     */
    @Override
    public void setIsDefinitivo(boolean x) {
        this.credenziali.setDefinitive(x);
    }
    
    /**
     * Verifica se un indirizzo email è valido.
     * 
     * @param email L'indirizzo email da verificare.
     * @return True se l'indirizzo email è valido, False altrimenti.
     */
    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
