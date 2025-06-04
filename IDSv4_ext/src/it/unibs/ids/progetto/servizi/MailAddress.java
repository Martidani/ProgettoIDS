package it.unibs.ids.progetto.servizi;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La classe MailAddress rappresenta un oggetto per gestire e validare un indirizzo email.
 */
public class MailAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    // Pattern regex per validare un indirizzo email
    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    
    private String email; // Stringa che rappresenta l'indirizzo email

    /**
     * Costruttore della classe MailAddress.
     * @param email Indirizzo email da assegnare all'oggetto
     */
    public MailAddress(String email) {
        this.email = email;
    }

    /**
     * Metodo getter per ottenere l'indirizzo email.
     * @return L'indirizzo email memorizzato nell'oggetto
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metodo setter per impostare l'indirizzo email.
     * @param email Nuovo indirizzo email da assegnare
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metodo statico per verificare se una stringa rappresenta un indirizzo email valido.
     * Utilizza il pattern regex definito nella costante EMAIL_PATTERN.
     * @param email Indirizzo email da validare
     * @return true se l'indirizzo email è valido, false altrimenti
     */
    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
