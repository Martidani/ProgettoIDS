package it.unibs.fp.mylib;

import java.util.*;

/**
 * Classe per gestire l'input da parte dell'utente.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class InputDati 
{

	  private static Scanner lettore = creaScanner();
	  
	  public static final String DATE_STRING = "Inserisci la data [gg/mm/yyyy]: ";
	  public static final String ORARIO_STRING = "Inserisci l'orario [hh:mm:ss] -> ";
	  public static final String ORARIO_STRING_NOHOUR = "Inserisci l'orario [mm:ss] -> ";
	  
	  private final static String ERRORE_FORMATO = "Attenzione: il dato inserito non e' nel formato corretto";
	  private final static String ERRORE_MINIMO= "Attenzione: e' richiesto un valore maggiore o uguale a ";
	  private final static String ERRORE_STRINGA_VUOTA= "Attenzione: non hai inserito alcun carattere";
	  private final static String ERRORE_MASSIMO= "Attenzione: e' richiesto un valore minore o uguale a ";
	  private final static String MESSAGGIO_AMMISSIBILI= "Attenzione: i caratteri ammissibili sono: ";

	  private final static char RISPOSTA_SI='S';
	  private final static char RISPOSTA_NO='N';

	  
 
	  private static Scanner creaScanner ()
	  {
	   Scanner creato = new Scanner(System.in);
	   creato.useDelimiter(System.getProperty("line.separator"));
	   return creato;
	  }
	  
	  /**
	   * Metodo per leggere una stringa inserita dall'utente.
	   * 
	   * @param messaggio Il messaggio da visualizzare prima della richiesta di input
	   * @return La stringa inserita dall'utente
	   */
	  public static String leggiStringa (String messaggio)
	  {
		  System.out.print(messaggio);
		  return lettore.next();
	  }
	  
	  /**
	   * Metodo per leggere una stringa inserita dall'utente, che non deve essere vuota.
	   * 
	   * @param messaggio Il messaggio da visualizzare prima della richiesta di input
	   * @return La stringa inserita dall'utente
	   */
	  public static String leggiStringaNonVuota(String messaggio)
	  {
	   boolean finito=false;
	   String lettura = null;
	   do
	   {
		 lettura = leggiStringa(messaggio);
		 lettura = lettura.trim();
		 if (lettura.length() > 0)
		  finito=true;
		 else
		  System.out.println(ERRORE_STRINGA_VUOTA);
	   } while (!finito);
	   
	   return lettura;
	  }
	  
	  /**
	   * Metodo per leggere un carattere inserito dall'utente.
	   * 
	   * @param messaggio Il messaggio da visualizzare prima della richiesta di input
	   * @return Il carattere inserito dall'utente
	   */
	  public static char leggiChar (String messaggio)
	  {
	   boolean finito = false;
	   char valoreLetto = '\0';
	   do
	    {
	     System.out.print(messaggio);
	     String lettura = lettore.next();
	     if (lettura.length() > 0)
	      {
	       valoreLetto = lettura.charAt(0);
	       finito = true;
	      }
	     else
	      {
	       System.out.println(ERRORE_STRINGA_VUOTA);
	      }
	    } while (!finito);
	   return valoreLetto;
	  }
	    
	  /**
	   * Metodo per leggere un carattere inserito dall'utente, convertendolo in maiuscolo.
	   * 
	   * @param messaggio Il messaggio da visualizzare prima della richiesta di input
	   * @param ammissibili Stringa contenente i caratteri ammissibili
	   * @return Il carattere inserito dall'utente, convertito in maiuscolo
	   */
	  public static char leggiUpperChar (String messaggio, String ammissibili)
	  {
	   boolean finito = false;
	   char valoreLetto = '\0';
	   do
	   {
	    valoreLetto = leggiChar(messaggio);
	    valoreLetto = Character.toUpperCase(valoreLetto);
	    if (ammissibili.indexOf(valoreLetto) != -1)
		 finito  = true;
	    else
	     System.out.println(MESSAGGIO_AMMISSIBILI + ammissibili);
	   } while (!finito);
	   return valoreLetto;
	  }
	    
	  /**
	   * Metodo per leggere un intero inserito dall'utente.
	   * 
	   * @param messaggio Il messaggio da visualizzare prima della richiesta di input
	   * @return L'intero inserito dall'utente
	   */
	  public static int leggiIntero (String messaggio)
	  {
	   boolean finito = false;
	   int valoreLetto = 0;
	   do
	    {
	     System.out.print(messaggio);
	     try
	      {
	       valoreLetto = lettore.nextInt();
	       finito = true;
	      }
	     catch (InputMismatchException e)
	      {
	       System.out.println(ERRORE_FORMATO);
           lettore.nextLine();
	      }
	    } while (!finito);
	   return valoreLetto;
	  }
	  
	  /**
	   * Metodo per leggere un intero inserito dall'utente, con un valore minimo specificato.
	   * 
	   * @param messaggio Il messaggio da visualizzare prima della richiesta di input
	   * @param minimo Il valore minimo ammesso per l'intero inserito
	   * @return L'intero inserito dall'utente
	   */
	  public static int leggiInteroConMinimo(String messaggio, int minimo)
	  {
	   boolean finito = false;
	   int valoreLetto = 0;
	   do
	    {
	     valoreLetto = leggiIntero(messaggio);
	     if (valoreLetto >= minimo)
	      finito = true;
	     else
	      System.out.println(ERRORE_MINIMO + minimo);
	    } while (!finito);
	     
	   return valoreLetto;
	  }

	  /**
	   * Metodo per leggere un intero inserito dall'utente, compreso tra un valore minimo e un valore massimo specificati.
	   * 
	   * @param messaggio Il messaggio da visualizzare prima della richiesta di input
	   * @param minimo Il valore minimo ammesso per l'intero inserito
	   * @param massimo Il valore massimo ammesso per l'intero inserito
	   * @return L'intero inserito dall'utente
	   */
	  public static int leggiInteroRange(String messaggio, int minimo, int massimo)
	  {
	   boolean finito = false;
	   int valoreLetto = 0;
	   do
	    {
	     valoreLetto = leggiIntero(messaggio);
	     if (valoreLetto >= minimo && valoreLetto<= massimo)
	      finito = true;
	     else
	      if (valoreLetto < minimo)
	         System.out.println(ERRORE_MINIMO + minimo);
	      else
	    	 System.out.println(ERRORE_MASSIMO + massimo); 
	    } while (!finito);
	     
	   return valoreLetto;
	  }

	  /**
	   * Metodo per leggere un numero decimale (double) inserito dall'utente.
	   * 
	   * @param messaggio Il messaggio da visualizzare prima della richiesta di input
	   * @return Il numero decimale inserito dall'utente
	   */
	  public static double leggiDouble (String messaggio)
	  {
	   boolean finito = false;
	   double valoreLetto = 0;
	   do
	    {
	     System.out.print(messaggio);
	     try
	      {
	       valoreLetto = lettore.nextDouble();
	       finito = true;
	      }
	     catch (InputMismatchException e)
	      {
	       System.out.println(ERRORE_FORMATO);
	      }
	    } while (!finito);
	   return valoreLetto;
	  }
	 
	  /**
	   * Metodo per leggere un numero decimale (double) inserito dall'utente, con un valore minimo specificato.
	   * 
	   * @param messaggio Il messaggio da visualizzare prima della richiesta di input
	   * @param minimo Il valore minimo ammesso per il numero decimale inserito
	   * @return Il numero decimale inserito dall'utente
	   */
	  public static double leggiDoubleConMinimo (String messaggio, double minimo)
	  {
	   boolean finito = false;
	   double valoreLetto = 0;
	   do
	    {
	     valoreLetto = leggiDouble(messaggio);
	     if (valoreLetto >= minimo)
	      finito = true;
	     else
	      System.out.println(ERRORE_MINIMO + minimo);
	    } while (!finito);
	     
	   return valoreLetto;
	  }
  
	  /**
	   * Metodo per chiedere all'utente una conferma (risposta Sì o No) tramite input.
	   * 
	   * @param messaggio Il messaggio da visualizzare prima della richiesta di conferma
	   * @return True se l'utente risponde "Sì", False se l'utente risponde "No"
	   */
	  public static boolean yesOrNo(String messaggio)
	  {
		  String mioMessaggio = messaggio + "("+RISPOSTA_SI+"/"+RISPOSTA_NO+") ";
		  char valoreLetto = leggiUpperChar(mioMessaggio,String.valueOf(RISPOSTA_SI)+String.valueOf(RISPOSTA_NO));
		  
		  if (valoreLetto == RISPOSTA_SI)
			return true;
		  else
			return false;
	  }
	  
	 	  
}
