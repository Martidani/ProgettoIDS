package it.unibs.ids.progetto;
import java.io.File;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;
import it.unibs.fp.mylib.ServizioFile;


public class Main {

	
	private static final int NUM_MAX_TENTATIVI = 3;
	public final static String[] voci = {"Introdurre comprensorio geografico", "Introdurre albero", "Visualizza comprensorio", 
			"Visualizza gerarchia", "Visualizza fattori di conversione"};
	public final static String[] vociAccesso = {"Registrazione","Login"};
	
	
		public static void main(String[] args) throws Exception {
			
			MyMenu menuAccesso = new MyMenu("Menu accesso",vociAccesso);
			MyMenu menu = new MyMenu("Menu principale",voci);
			Gerarchia gerarchia = caricaGerarchia();
			if(gerarchia!=null) {
				System.out.println("Lettura da file: \n\n"+ gerarchia.toString());
			}
			
			GestioneUtenza gestioneUtenza = caricaGestioneUtenza();
			if(gestioneUtenza!=null) {
				System.out.println("Lettura da file: \n\n"+ gestioneUtenza.toString());
			}
			
			
			int accesso;
			do {
				 accesso = menuAccesso.scegli();
				 switch(accesso) {
				 case 1:
					 registrazione(gestioneUtenza);
					 break; 
				 case 2:
					 login(gestioneUtenza);
					break;
				 default:
					 break;
				 }
				 
			} while(accesso!=0);
			
			
			
			
			int scelta;
			do {
				
				 scelta = menu.scegli();
				 switch(scelta) {
				 
				 case 1:
					 aggiungiComprensorio(gestioneUtenza);
					 break;
					 
				 case 2:
					 break;
					 
				 case 3:
					 System.out.println(gestioneUtenza.getGeografia().toString());
					break;
				
				 case 4:
					break;
				
				 case 5:
					stampaFattori(gerarchia);
					break;
					 	 
				 default:
					 break;
				 }
				 
			} while(scelta!=0);
			salvaSuFile(gerarchia);
			salvaSuFile(gestioneUtenza);
		}



		private static void stampaFattori(Gerarchia gerarchia) {
			String foglia = InputDati.leggiStringaNonVuota("Inserisci foglia");
			gerarchia.visualizzaFoglia(foglia);
		}



		private static void aggiungiComprensorio(GestioneUtenza gestioneUtenza) {
			Comprensorio comprensorio = new Comprensorio();
			 System.out.println("Inserisci comprensorio (Exit per uscire) ");
			 String comune;
			 
			 do {
				comune = InputDati.leggiStringaNonVuota("Inserisci comune");
				comprensorio.addComune(comune);
				
			} while (comune.equalsIgnoreCase("Exit"));
			 gestioneUtenza.addComprensorio(comprensorio);
		}



		private static void login(GestioneUtenza gestioneUtenza) {
			for (int i = 0; i < NUM_MAX_TENTATIVI; i++) {
			 System.out.println("Inserisci dati di login: ");
			 Credenziali credenzialiLogin = inserisciCredenziali(gestioneUtenza);
				
			 if (!gestioneUtenza.verificaEsistenzaConfiguratore
				(credenzialiLogin.getID(), credenzialiLogin.getPassword())) {
					System.out.println("Non esiste configuratore con queste credenziali");
			 }else {
					System.out.println("Utente riconosciuto");
					break;
				}	
			}
		}



		private static void registrazione(GestioneUtenza gestioneUtenza) {
			Configuratore configuratore = new Configuratore();
			 System.out.println("ID di default: " + configuratore.getID());
			 System.out.println("Password di default " + configuratore.getPSSW());	
			 System.out.println("Scegli nuove credenziali: ");	
			 Credenziali credenzialiRegistrazione = inserisciCredenziali(gestioneUtenza);
			 configuratore.setCredenziali(credenzialiRegistrazione);
			 gestioneUtenza.addUtente(configuratore);
		}



		private static Credenziali inserisciCredenziali(GestioneUtenza gestioneUtenza) {
			String ID;
			do {
				ID = InputDati.leggiStringaNonVuota("Immetti ID: ");
				
			} while (gestioneUtenza.verificaEsistenzaID(ID));
			
			
			String PSSW = InputDati.leggiStringaNonVuota("Immetti Password: ");
			return new Credenziali(ID, PSSW);
		}
		
	
		////////////////////////////////////

		private static Gerarchia caricaGerarchia() {
			File file = new File("gerarchia.txt");
			Gerarchia gerarchia = (Gerarchia)ServizioFile.caricaSingoloOggetto(file);
			return gerarchia;
		}
		
		private static void salvaSuFile(Gerarchia gerarchia) {
			File dst = new File("gerarchia.txt");
			ServizioFile.salvaSingoloOggetto(dst, gerarchia);
		}
		
		private static GestioneUtenza caricaGestioneUtenza() {
			File file = new File("gestioneUtenza.txt");
			GestioneUtenza gestioneUtenza= (GestioneUtenza)ServizioFile.caricaSingoloOggetto(file);
			return gestioneUtenza;
		}
		
		private static void salvaSuFile(GestioneUtenza gestioneUtenza) {
			File dst = new File("gestioneUtenza.txt");
			ServizioFile.salvaSingoloOggetto(dst, gestioneUtenza);
		}

		
		
		
		/**
		 * MetodoCostruisci gerarchia (configuratore)
		 * --> se configuratore, costruisci
		 * --> else, null
		 */
		
}



