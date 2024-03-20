package it.unibs.ids.progetto;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
			GestioneUtenza gestioneUtenza = caricaGestioneUtenza();
			//Gerarchia gerarchia = new Gerarchia();
			//GestioneUtenza gestioneUtenza = new GestioneUtenza();
			
			if(gerarchia!=null && gestioneUtenza!=null) {
				System.out.println("Lettura da file: \n\n");
			}
			
			
			
			
			
			
//			Credenziali cred = new Credenziali("sabba","sabba");
//			Configuratore utente = new Configuratore(cred);
//			gestioneUtenza.addUtente(utente);
//			Comprensorio com = new Comprensorio();
//			com.addComune("Brescia");
//			gestioneUtenza.addComprensorio(com);
//			
//			Nodo nodo2 = new Nodo("rootchild", false);
//			ArrayList<String[]> dominio = new ArrayList<String[]>();
//			String [] val = { "root", null};
//			dominio.add(val);
//			
//			Nodo nodo = new Nodo("root", true, "root", dominio);
//			nodo.addChild(nodo2);
//			gerarchia.addAlberi(nodo);
//			
			
			int accesso;
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
				 
			
			
			
			
			int scelta;
			do {
				
				 scelta = menu.scegli();
				 switch(scelta) {
				 
				 case 1:
					 aggiungiComprensorio(gestioneUtenza);
					 break;
					 
				 case 2:
					 
					 ArrayList<String> foglieAttuali = new ArrayList<>();
					 String radice;
					 do {
						radice = InputDati.leggiStringaNonVuota("Immetti nome radice -> ");
						
					} while (!gerarchia.verificaEsistenzaNomeRadice(radice));
					 
					 String campo = InputDati.leggiStringaNonVuota("Inserisci campo -> ");
					 Nodo root = new Nodo(radice,true, campo);
					 
					 creaNodiFiglio(root,gerarchia,root,foglieAttuali);
					 gerarchia.addAlberi(root);
					 
					 ArrayList<String> foglieNonAttuali = gerarchia.foglieNonAttuali(foglieAttuali);
					 String foglia1;
					 String foglia2;
					 boolean condizione;
					 do {
						foglia1 = InputDati.leggiStringaNonVuota("Inserisci foglia 1 ");
						if (gerarchia.checkFoglia(foglia1, foglieAttuali)) {
							foglieAttuali.remove(foglia1);
						}
						foglia2 = InputDati.leggiStringaNonVuota("Inserisci foglia 2 ");
						
						if (gerarchia.checkFoglia(foglia2, foglieAttuali)) {
							foglieAttuali.remove(foglia2);
						}
						
						double fattoreDiConversione;
						do {
							fattoreDiConversione = InputDati.leggiDouble("Inserisci fattore di conversione -> ");
						} while (!gerarchia.verificaFattoreConversione(fattoreDiConversione));
						
						
						condizione = gerarchia.checkFoglia(foglia2, foglieNonAttuali) && gerarchia.checkFoglia(foglia1, foglieNonAttuali);
						if (!condizione) {
							
							gerarchia.aggiungiFattoreConversione(foglia1, foglia2, fattoreDiConversione);
							
						}
					} while (foglieAttuali.size() != 0 && condizione );
					 
					 break;
					 
				 case 3:
					 System.out.println(gestioneUtenza.toString());
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



		private static void creaNodiFiglio(Nodo nodoParent,Gerarchia gerarchia,Nodo radice,ArrayList<String> foglieAttuali) {
			do{
				 System.out.println("Inserisci nodo figlio di " + nodoParent.getNome());
				 
				 String nome;
				 do {
					  nome = InputDati.leggiStringaNonVuota("Inserisci nome -> ");
						
				 } while (!gerarchia.verificaEsistenzaNomeNonRadice(nome,radice));
				 boolean risposta = InputDati.yesOrNo("è foglia? -> " );
				 Nodo nodoChild;
				 if (risposta) {
					 nodoChild = new Nodo(nome,false);
					 foglieAttuali.add(nodoChild.getNome());
					
				}else {
					String campo = InputDati.leggiStringaNonVuota("Inserisci campo -> ");
					nodoChild = new Nodo(nome,false,campo);
					
				   int num = 0;
					do {
					    num++;
						String valoreDominio = InputDati.leggiStringaNonVuota("Inserisci il " + num + "'" + 
						" valore del dominio");
						if (InputDati.yesOrNo("Vuoi inserire una descrizione di " + valoreDominio)) {
							String descrizioneDominio = InputDati.leggiStringaNonVuota("Inserisci descrizione -> ");
							nodoChild.addElementiDominio(valoreDominio, descrizioneDominio);
						}else 
							nodoChild.addElementiDominio(valoreDominio);
						
						
					}while(InputDati.yesOrNo("Vuoi aggiugere un altro elemento al dominio? -> "));
					
					
				}
				try {
					nodoParent.addChild(nodoChild);
				} catch (Exception e) {
					e.getMessage();
				}
			 }while(InputDati.yesOrNo("Vuoi aggiungere un altro figlio a " + nodoParent.getNome()));
			
			
			
			for (Nodo nodo : nodoParent.getChildren() ) {
				if (!nodo.isLeaf()) {
					creaNodiFiglio(nodo,gerarchia,radice,foglieAttuali);
				}
			}
			
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
				comune = InputDati.leggiStringaNonVuota("Inserisci comune -> ");
				
				comprensorio.addComune(comune);
				
				
			} while (!comune.equalsIgnoreCase("Exit"));
			
			 int size = comprensorio.getComprensorio().size();
			 comprensorio.getComprensorio().remove(size - 1);
			
			 gestioneUtenza.addComprensorio(comprensorio);
		}



		private static void login(GestioneUtenza gestioneUtenza) {
			for (int i = 0; i < NUM_MAX_TENTATIVI; i++) {
			 System.out.println("Inserisci dati di login: ");
			 Credenziali credenzialiLogin = inserisciCredenzialiLogin(gestioneUtenza);
				
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
			 Credenziali credenzialiRegistrazione = inserisciCredenzialiRegistrazione(gestioneUtenza);
			 configuratore.setCredenziali(credenzialiRegistrazione);
			 gestioneUtenza.addUtente(configuratore);
		}



		private static Credenziali inserisciCredenzialiRegistrazione(GestioneUtenza gestioneUtenza) {
			String ID;
			
			do {
				ID = InputDati.leggiStringaNonVuota("Immetti ID: ");
				if (gestioneUtenza.verificaEsistenzaID(ID)) System.out.println("ID gia utilizzato");
			} while (gestioneUtenza.verificaEsistenzaID(ID));
			
			
			
			String PSSW = InputDati.leggiStringaNonVuota("Immetti Password: ");
			return new Credenziali(ID, PSSW);
		}
		
		private static Credenziali inserisciCredenzialiLogin(GestioneUtenza gestioneUtenza) {
			String ID;
						
			ID = InputDati.leggiStringaNonVuota("Immetti ID: ");

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



