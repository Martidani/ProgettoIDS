package it.unibs.ids.progetto.servizi;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailAddress implements Serializable {
    private static final String EMAIL_PATTERN =
        "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    
    private String email;
    

    public MailAddress(String email) {
		this.email = email;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
