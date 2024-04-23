package it.unibs.ids.progetto.main;

import it.unibs.fp.mylib.MyMenu;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.RootTreeException;
import it.unibs.ids.progetto.Utenza;

public class MainConfView implements MainView{

	public AccessController accessConfController;
	public WorkController workConfController;
	
    public MainConfView() {
		super();
		this.accessConfController = new AccessConfController();
		this.workConfController = new WorkConfController();
	}
    
	public final static String[] voci = 
        {"Introdurre comprensorio geografico", "Introdurre albero", "Visualizza comprensorio", 
            "Visualizza gerarchia", "Visualizza fattori di conversione"};
    
    static MyMenu menuConfiguratore = new MyMenu("Menu principale configuratore", voci);
	
    /**
     * Metodo che gestisce il menu di configurazione.
     * 
     * @param gerarchia L'oggetto Gerarchia su cui effettuare le operazioni.
     * @param geografia L'oggetto Geografia su cui effettuare le operazioni.
     * @return La scelta effettuata dall'utente.
     * @throws RootTreeException 
     */
    public int menu(Gerarchia gerarchia, Geografia geografia, Utenza utenza) throws RootTreeException {
        int scelta;
        scelta = menuConfiguratore.scegli();
        switch (scelta) {

            case 1:
                WorkConfController.creaComprensorio(geografia);
                break;

            case 2:
                WorkConfController.creaGerarchia(gerarchia);
                break;

            case 3:
                WorkConfController.stampaGeografia(geografia);
                break;

            case 4:
                workConfController.navigaGerarchia(gerarchia);
                break;

            case 5:
                WorkConfController.stampaFattori(gerarchia);
                break;

            default:
                break;
        }
        return scelta;
    }

    
	
    public final static String[] vociAccesso = 
        {"Registrazione","Login"};
    
    static MyMenu menuAccesso = new MyMenu("Menu accesso configuratore", vociAccesso);
    
    /**
     * Metodo che gestisce il menu di accesso.
     * 
     * @param utenza L'utente che sta effettuando l'accesso.
     * @return La scelta effettuata dall'utente.
     */
    public int menuAccesso(Utenza utenza, Geografia geografia) {
        int accesso;
        accesso = menuAccesso.scegli();
        switch (accesso) {
        case 1:
        	accessConfController.registrazione(utenza, geografia);
            break;

        case 2:

        	accesso = accessConfController.login(utenza);
        	
            break;

        default:
            break;
    }
        return accesso;
    }
    

	
}
