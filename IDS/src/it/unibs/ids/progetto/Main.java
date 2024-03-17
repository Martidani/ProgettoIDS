package it.unibs.ids.progetto;
import java.io.File;
import it.unibs.fp.mylib.MyMenu;
import it.unibs.fp.mylib.ServizioFile;


public class Main {

	
	public final static String[] voci = {"A", "B", "C", 
			"D", "E",  "F"};
		
	
	
		public static void main(String[] args) {
			
			MyMenu menu = new MyMenu("Menu principale",voci);
			Gerarchia gerarchia = caricaGerarchia();
			if(gerarchia!=null) {
				System.out.println("Lettura da file: \n\n"+ gerarchia.toString());
			}
			
			GestioneUtenza gestioneUtenza = caricaGestioneUtenza();
			if(gestioneUtenza!=null) {
				System.out.println("Lettura da file: \n\n"+ gestioneUtenza.toString());
			}
			
			
			int scelta;
			do {
				 scelta = menu.scegli();
				 switch(scelta) {
				 
				 case 1:
					 break;
					 
				 case 2:
					 break;
					 
				 case 3:
					break;
				
				 case 4:
					break;
				
				 case 5:
					break;
					 	 
				 case 6:
					 break;
					 
				 case 7:
					 break;
					 
				 default:
					 break;
				 }
				 
			} while(scelta!=0);
			salvaSuFile(gerarchia);
			salvaSuFile(gestioneUtenza);
		}
		
	

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



