package it.unibs.ids.progetto;
import java.io.File;
import java.util.ArrayList;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;
import it.unibs.fp.mylib.ServizioFile;

/**
 * Classe Main per l'esecuzione del programma.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Main {

	
	private static final String GESTIONE_UTENZA_FILE = "gestioneUtenza.txt";
	private static final String GERARCHIA_FILE = "gerarchia.txt";
	private static final int NUM_MAX_TENTATIVI = 3;
	public final static String[] voci = {"Introdurre comprensorio geografico", "Introdurre albero", "Visualizza comprensorio", 
			"Visualizza gerarchia", "Visualizza fattori di conversione"};
	public final static String[] vociAccesso = {"Registrazione","Login"};
	
		public static void main(String[] args) throws Exception {
			
			MyMenu menuAccesso = new MyMenu("Menu accesso",vociAccesso);
			MyMenu menu = new MyMenu("Menu principale",voci);
			
			
			 //  default initialization
			 //Gerarchia gerarchia = defaultTree();
			 //GestioneUtenza gestioneUtenza = defaultAccess();
			
			
			 //  file initialization
			Gerarchia gerarchia = caricaGerarchia();
			GestioneUtenza gestioneUtenza = caricaGestioneUtenza();
			
			if(gerarchia!=null && gestioneUtenza!=null) {
				System.out.println("Lettura da file: " + GESTIONE_UTENZA_FILE + ", " + GERARCHIA_FILE );
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
			
			salvaSuFile(gerarchia);
			salvaSuFile(gestioneUtenza);
		}

		
		/**
		 * Crea e restituisce un albero gerarchico di default.
		 * 
		 * @return L'albero gerarchico di default
		 * @throws Exception Se si verifica un errore durante la creazione dell'albero
		 */
		public static Gerarchia defaultTree() throws Exception {
			  	Gerarchia gerarchia = new Gerarchia();

				// Creazione del nodo radice
				Nodo nodo1 = new Nodo("system", true, "firstRoot");
				nodo1.setCampo("field");
				nodo1.addElementiDominio("rootchildM");
				nodo1.addElementiDominio("rootchildF");
				
				// Creazione dei nodi figli
				Nodo nodo21 = new Nodo("rootchild1", false);
				nodo1.addChild(nodo21);
				
				Nodo nodo22 = new Nodo("rootchild2", false, "1' Rootchild");
				nodo1.addChild(nodo22);
				nodo22.setCampo("field2");
				nodo22.addElementiDominio("first");
				nodo22.addElementiDominio("second");
				
				Nodo nodo23 = new Nodo("rootchild2.1", false);
				nodo22.addChild(nodo23);
				Nodo nodo24 = new Nodo("rootchild2.2", false);	
				nodo22.addChild(nodo24);
				
				// Aggiunta dei nodi all'albero e definizione dei fattori di conversione
				gerarchia.aggiungiFattoreConversione(nodo21, nodo23, 2);
				gerarchia.aggiungiFattoreConversione(nodo23, nodo24, 1.5);
				gerarchia.addAlberi(nodo1);
				gerarchia.addTransitivoFattoreConversione();
				
				return gerarchia;
			}

		/**
		 * Crea e restituisce una gestione utenza di default.
		 * 
		 * @return La gestione utenza di default
		 */
		public static GestioneUtenza defaultAccess() {
				GestioneUtenza gestioneUtenza = new GestioneUtenza();
				
				// Creazione delle credenziali di default per l'utente admin
				Credenziali cred = new Credenziali("admin","admin");
				cred.setDefinitive(true);
				Configuratore utente = new Configuratore(cred);
				gestioneUtenza.addUtente(utente);
				
				return gestioneUtenza;
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
						 nodoChild = new Nodo(nome,false);
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
		
		/**
		 * Carica la gerarchia da file.
		 * 
		 * @return La gerarchia caricata da file, null se non esiste o si verifica un errore durante il caricamento
		 */
		private static Gerarchia caricaGerarchia() {
			File file = new File(GERARCHIA_FILE);
			Gerarchia gerarchia = (Gerarchia) ServizioFile.caricaSingoloOggetto(file);
			return gerarchia;
		}
		
		
		/**
		 * Salva la gerarchia su file.
		 * 
		 * @param gerarchia La gerarchia da salvare su file
		 */
		private static void salvaSuFile(Gerarchia gerarchia) {
			File dst = new File(GERARCHIA_FILE);
			ServizioFile.salvaSingoloOggetto(dst, gerarchia);
		}
		
		
		/**
		 * Carica la gestione utenza da file.
		 * 
		 * @return La gestione utenza caricata da file, null se non esiste o si verifica un errore durante il caricamento
		 */
		private static GestioneUtenza caricaGestioneUtenza() {
			File file = new File(GESTIONE_UTENZA_FILE);
			GestioneUtenza gestioneUtenza = (GestioneUtenza) ServizioFile.caricaSingoloOggetto(file);
			return gestioneUtenza;
		}
		
		
		/**
		 * Salva la gestione utenza su file.
		 * 
		 * @param gestioneUtenza La gestione utenza da salvare su file
		 */
		private static void salvaSuFile(GestioneUtenza gestioneUtenza) {
			File dst = new File(GESTIONE_UTENZA_FILE);
			ServizioFile.salvaSingoloOggetto(dst, gestioneUtenza);
		}

		
		
}