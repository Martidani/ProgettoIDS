package it.unibs.ids.progetto;
import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;
import it.unibs.ids.progetto.news.DefaultInitializer;
import it.unibs.ids.progetto.news.FileManager;

/**
 * Classe Main per l'esecuzione del programma.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Main {


	private static final int NUM_MAX_TENTATIVI = 3;
	public final static String[] voci = {"Introdurre comprensorio geografico", "Introdurre albero", "Visualizza comprensorio", 
			"Visualizza gerarchia", "Visualizza fattori di conversione"};
	public final static String[] vociAccesso = {"Registrazione","Login"};
	
		public static void main(String[] args) throws Exception {
			
			MyMenu menuAccesso = new MyMenu("Menu accesso",vociAccesso);
			MyMenu menu = new MyMenu("Menu principale",voci);
			
			
	        // Caricamento da file
			GestioneUtenza gestioneUtenza = FileManager.caricaGestioneUtenza();
	        Gerarchia gerarchia = FileManager.caricaGerarchia();

	        if (gestioneUtenza == null || gerarchia == null) {
	            // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
	            gerarchia = DefaultInitializer.defaultTree();
	            gestioneUtenza = DefaultInitializer.defaultAccess();
	        } else {
	            System.out.println("Lettura da file: " + FileManager.getGestioneUtenzaFile() + ", " + FileManager.getGerarchiaFile());
	        }
	        
			
			int accesso;
				 
			do {
				accesso = menuAccesso.scegli();
				
				switch(accesso) {
				case 1:
				registrazione(gestioneUtenza); 
				break; 
				 
				case 2:
				for (int i = 0; i < NUM_MAX_TENTATIVI; i++) {
					 System.out.println("Inserisci dati di login: ");
					
					String ID = InputDati.leggiStringaNonVuota("  ID: ");
					String PSSW = InputDati.leggiStringaNonVuota("  Password: ");
					Credenziali credenzialiLogin = new Credenziali(ID, PSSW);
					Configuratore conf = gestioneUtenza.verificaEsistenzaConfiguratore(credenzialiLogin.getID(), credenzialiLogin.getPassword());
					if ( conf == null) {
						 System.out.println(" ! Non esiste configuratore con queste credenziali !");
						 accesso = 1;
					 }else if ( !conf.getCredenziali().isDefinitive() ){
						 accesso = 2;
						 System.out.println("Scegli nuove credenziali: ");	
						 Credenziali credenzialiRegistrazione = inserisciCredenzialiRegistrazione(gestioneUtenza);
						 conf.setCredenziali(credenzialiRegistrazione);
						 conf.setIsDefinitivo(true);
						 break;
						 
					 } else {
						 accesso = 2;
						 System.out.println("-> Utente riconosciuto");
						 break;
					 }
					
					}
				 break;
				 
				default:	
				break;
				 }
			} while (accesso == 1);
			
			if (accesso!=0) {
				int scelta;
				do {
					
					 scelta = menu.scegli();
					 switch(scelta) {
					 
					 case 1:
						 aggiungiComprensorio(gestioneUtenza);
						 break;
						 
					 case 2:
						 
						 ArrayList<Nodo> foglieAttuali = new ArrayList<>();
						 String radice;
						 do {
							radice = InputDati.leggiStringaNonVuota("Nome radice -> ");
							
						} while (gerarchia.verificaEsistenzaNomeRadice(radice));
						 
						 String campo = InputDati.leggiStringaNonVuota("Campo -> ");
						 Nodo root = new Nodo(radice,true, campo);
						 
						 int num = 0;
							do {
							    num++;
								String valoreDominio = InputDati.leggiStringaNonVuota(num + "' valore del dominio -> ");
								if (InputDati.yesOrNo("  Vuoi inserire una descrizione di " + valoreDominio + "? ")) {
									String descrizioneDominio = InputDati.leggiStringaNonVuota("Descrizione -> ");
									root.addElementiDominio(valoreDominio, descrizioneDominio);
								}else 
									root.addElementiDominio(valoreDominio);
								
								
							}while(InputDati.yesOrNo("Vuoi aggiugere un altro elemento al dominio? "));
							
						 
						 creaNodiFiglio(root,gerarchia,root,foglieAttuali);
						 gerarchia.addAlberi(root);
						 

						 String foglia1;
						 String radice1;
						 String foglia2;
						 String radice2;
						 boolean condizione;
						 System.out.println("\nInserimento fattori di conversione:");
						 do {
							 
							 Nodo nodo1;
							 do {
								foglia1 = InputDati.leggiStringaNonVuota("Foglia 1:\n  Nome -> ");
								radice1 = InputDati.leggiStringaNonVuota("  Radice -> ");
								nodo1 = gerarchia.visualizzaNodo(foglia1, radice1, gerarchia.getAlberi());
							 } while (nodo1 == null);
							 
							 Nodo nodo2;
							 do {
								foglia2 = InputDati.leggiStringaNonVuota("Foglia 2:\n  Nome -> ");
								radice2 = InputDati.leggiStringaNonVuota("  Radice -> ");
								nodo2 = gerarchia.visualizzaNodo(foglia2, radice2, gerarchia.getAlberi()); 
							 } while (nodo2 == null);
							
							

							
							
							double fattoreDiConversione;
							do {
								fattoreDiConversione = InputDati.leggiDouble("Fattore di conversione -> ");
							} while (!gerarchia.verificaFattoreConversione(fattoreDiConversione));
							
							condizione = !gerarchia.checkFoglia(nodo1, foglieAttuali) && !gerarchia.checkFoglia(nodo2, foglieAttuali);
							
							if (!condizione) 
								gerarchia.aggiungiFattoreConversione(nodo1, nodo2, fattoreDiConversione);
							
						} while (InputDati.yesOrNo("Vuoi continuare l'inserimento? "));
							
						 gerarchia.addTransitivoFattoreConversione();
						 break;
						 
					 case 3:
						System.out.println(gestioneUtenza.toString());
						break;
					
					 case 4:
						System.out.println(gerarchia.toString());
						break;
					
					 case 5:
						stampaFattori(gerarchia);
						break;
						 	 
					 default:
						 break;
					 }
					 
				} while(scelta!=0);
			}
			
	        FileManager.salvaSuFile(gerarchia);
	        FileManager.salvaSuFile(gestioneUtenza);
		}

		/**
		 * 
		 * Crea i nodi figlio a partire da un nodo parent, aggiungendoli all'albero gerarchico.
		 * 
		 * @param nodoParent Il nodo genitore
		 * @param gerarchia L'albero gerarchico
		 * @param radice La radice dell'albero
		 * @param foglieAttuali Elenco delle foglie attuali
		 */
		private static void creaNodiFiglio(Nodo nodoParent, Gerarchia gerarchia,Nodo radice,ArrayList<Nodo> foglieAttuali) {
				int numFigli=0;
				do{
					numFigli++;
					 System.out.println("\n" + numFigli + "' figlio (di " + nodoParent.getNome() + "): ");
					 
					 String nome;
					 do {
						  nome = InputDati.leggiStringaNonVuota("Nome -> ");
							
					 } while (gerarchia.verificaEsistenzaNomeNonRadice(nome,radice));
					 boolean risposta = InputDati.yesOrNo("È foglia? " );
					 Nodo nodoChild;
					 if (risposta) {
						 nodoChild = new Nodo(nome);
						 foglieAttuali.add(nodoChild);
						
					}else {
						String campo = InputDati.leggiStringaNonVuota("Campo -> ");
						nodoChild = new Nodo(nome,false,campo);
						
					   int num = 0;
						do {
						    num++;
							String valoreDominio = InputDati.leggiStringaNonVuota(num + "'" + 
							" valore del dominio -> ");
							if (InputDati.yesOrNo("  Vuoi inserire una descrizione di " + valoreDominio + "? ")) {
								String descrizioneDominio = InputDati.leggiStringaNonVuota("Descrizione -> ");
								nodoChild.addElementiDominio(valoreDominio, descrizioneDominio);
							}else 
								nodoChild.addElementiDominio(valoreDominio);
							
							
						}while(InputDati.yesOrNo("Vuoi aggiugere un altro elemento al dominio? "));
						
						
					}
					try {
						nodoParent.addChild(nodoChild);
					} catch (Exception e) {
						e.getMessage();
					}
				 }while(numFigli<nodoParent.getDominio().size());
				
				
				
				for (Nodo nodo : nodoParent.getChildren() ) {
					if (!nodo.isLeaf()) {
						creaNodiFiglio(nodo,gerarchia,radice,foglieAttuali);
					}
				}
				
			}

		/**
		 * Stampa i fattori di conversione per un dato nodo dell'albero gerarchico.
		 * 
		 * @param gerarchia L'albero gerarchico
		 */
		private static void stampaFattori(Gerarchia gerarchia) {
				String foglia = InputDati.leggiStringaNonVuota("Inserisci nome foglia: ");
				String root = InputDati.leggiStringaNonVuota("Inserisci radice della gerarchia della foglia: ");
				Nodo nodo = gerarchia.visualizzaNodo(foglia, root, gerarchia.getAlberi());
				if (nodo==null) 
					System.out.println("  Non è stata trovata nessuna corrispondenza");
				else 
					System.out.println(nodo.toStringF());
				
			}

		/**
		 * Aggiunge un comprensorio alla gestione utenza.
		 * 
	     * @param gestioneUtenza La gestione utenza
		 */
		private static void aggiungiComprensorio(GestioneUtenza gestioneUtenza) {
				Comprensorio comprensorio = new Comprensorio();
				 System.out.println("Inserisci comprensorio (Exit per uscire) ");
				 String comune;
				 
				 do {
					comune = InputDati.leggiStringaNonVuota("	comune -> ");
					
					comprensorio.addComune(comune);
					
					
				} while (!comune.equalsIgnoreCase("Exit"));
				
				 int size = comprensorio.getComprensorio().size();
				 comprensorio.getComprensorio().remove(size - 1);
				
				 gestioneUtenza.addComprensorio(comprensorio);
			}

		/**
		 * Registra un nuovo utente nella gestione utenza.
		 * 
		 * @param gestioneUtenza La gestione utenza
		 */
		private static void registrazione(GestioneUtenza gestioneUtenza) {
				Configuratore configuratore = new Configuratore();
				String id = configuratore.getID();
				String psswd = configuratore.getPSSW();
				 System.out.println("ID di default: " + id);
				 System.out.println("Password di default " + psswd);
				 
				 Credenziali credenziali = new Credenziali(id, psswd);
				 configuratore.setCredenziali(credenziali);
				 configuratore.setIsDefinitivo(false);
				 gestioneUtenza.addUtente(configuratore);
			}

		/**
		 * Chiede all'utente di inserire le credenziali per la registrazione e le restituisce.
		 * 
		 * @param gestioneUtenza La gestione utenza
		 * @return Le credenziali inserite dall'utente
		 */
		private static Credenziali inserisciCredenzialiRegistrazione(GestioneUtenza gestioneUtenza) {
				String ID;
				
				do {
					ID = InputDati.leggiStringaNonVuota("  ID: ");
					if (gestioneUtenza.verificaEsistenzaID(ID)) System.out.println(" ! ID già utilizzato ! ");
				} while (gestioneUtenza.verificaEsistenzaID(ID));
				
				
				
				String PSSW = InputDati.leggiStringaNonVuota("  Password: ");
				return new Credenziali(ID, PSSW);
			}		
}