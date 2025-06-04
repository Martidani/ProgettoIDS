package it.unibs.ids.progetto.servizi;
import java.io.*;

/**
 * FileService è una classe utilizzata per gestire il caricamento 
 * e il salvataggio dei dati da e verso file di testo.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class FileService
{
	private final static String MSG_NO_FILE = "ATTENZIONE:  NON TROVO IL FILE ";
	private final static String MSG_NO_LETTURA = "ATTENZIONE:  PROBLEMI CON LA LETTURA DEL FILE ";
	private final static String MSG_NO_SCRITTURA = "ATTENZIONE:  PROBLEMI CON LA SCRITTURA DEL FILE ";
	private final static String MSG_NO_CHIUSURA ="ATTENZIONE:  PROBLEMI CON LA CHIUSURA DEL FILE ";
  	
	
	/**
	 * Carica un dato oggetto su File 
	 * @param f File
	 * @return
	 */
	public static Object caricaSingoloOggetto (File f)
	 {
		 Object letto = null;
		 ObjectInputStream ingresso = null;
			
		 try
			{
			 ingresso = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
				
			 letto = ingresso.readObject();
				
			}
		 catch (FileNotFoundException excNotFound)
			{
			 System.out.println(MSG_NO_FILE + f.getName() );
			}
		 catch (IOException excLettura)
			{
			 System.out.println(MSG_NO_LETTURA + f.getName() );
			}
		 catch (ClassNotFoundException excLettura)
			{
			 System.out.println(MSG_NO_LETTURA + f.getName() );
			}
  	 finally
			{
			 if (ingresso != null)
				{
				 try 
					{
				   ingresso.close();
					}
				 catch (IOException excChiusura)
					{
			 			System.out.println(MSG_NO_CHIUSURA + f.getName() );
					}
				}
			} 

		 return letto;
		  
	 } 
	
	/**
	 * Salva un dato oggetto su File 
	 * @param f File
	 * @param daSalvare l'Oggetto da salvare
	 * @return
	 */
	public static void salvaSingoloOggetto (File f, Object daSalvare)
	 {
		 ObjectOutputStream uscita = null;
			
		 try
			{
			 uscita = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));				
			 uscita.writeObject(daSalvare);
				
			}
		 catch (IOException excScrittura)
			{
			 System.out.println(MSG_NO_SCRITTURA + f.getName() );
			}
		 
  	     finally
			{
			 if (uscita != null)
				{
				 try 
				  {
				   uscita.close();
				  }
				 catch (IOException excChiusura)
					{
			 			System.out.println(MSG_NO_CHIUSURA + f.getName() );
					}
				}
			} 
		 } 
}