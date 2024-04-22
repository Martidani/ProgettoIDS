package it.unibs.ids.progetto.main;

import it.unibs.fp.mylib.MyMenu;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Utenza;

public class MenuFruitorController implements MenuController {

	public AccessFruitorController accessFruitorController;
	public WorkFruitorController workFruitorController;
	
    public MenuFruitorController() {
		super();
		this.accessFruitorController = new AccessFruitorController();
		this.workFruitorController = new WorkFruitorController();
	}
    
	public final static String[] vociF = 
		{"Naviga nella gerarchia", "Proponi uno scambio"};
	
    static MyMenu menuFruitore = new MyMenu("Menu principale fruitore", vociF);
    
	public int menu(Gerarchia gerarchia, Geografia geografia, Utenza utenza) {
		int scelta;
		scelta = menuFruitore.scegli();
		switch (scelta) {

		    case 1:
		    	workFruitorController.navigaGerarchia(gerarchia);
		    	break;
		    	
		    case 2:
		    	workFruitorController.proponiScambio(gerarchia, utenza);
		        

		    default:
		        break;
		}
		return scelta;
	}

	
    public final static String[] vociAccesso = 
        {"Registrazione","Login"};
    
    static MyMenu menuAccesso = new MyMenu("Menu accesso fruitore", vociAccesso);
    
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
             System.out.print(geografia.toString());
             accessFruitorController.registrazione(utenza, geografia);

            break;

        case 2:
        	accesso = accessFruitorController.login(utenza);
            break;

        default:
            break;
    }
        return accesso;
    }
    

    
}
