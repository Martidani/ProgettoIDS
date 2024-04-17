package it.unibs.fp.mylib;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * classe per ricevere dati in input
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
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
	   * metodo per leggere una stringa
	   * @param messaggio Messaggio da mostrare in output
	   * @return stringa in input
	   */
	  public static String leggiStringa (String messaggio)
	  {
		  System.out.print(messaggio);
		  return lettore.next();
	  }
	  
	  /**
	   * metodo per leggere una stringa, che non deve essere vuota
	   * @param messaggio Messaggio da mostrare in output
	   * @return stringa in input
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
	   * metodo per leggere un carattere
	   * @param messaggio Messaggio da mostrare in output
	   * @return carattere in input
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
	   * metodo per leggere un int
	   * @param messaggio Messaggio da mostrare in output
	   * @return intero in input
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
	   * metodo per leggere un int maggiore di 0
	   * @param messaggio Messaggio da mostrare in output
	   * @return intero in input
	   */
	  public static int leggiInteroPositivo(String messaggio)
	  {
		  return leggiInteroConMinimo(messaggio,1);
	  }
	  
	  /**
	   * metodo per leggere un int maggiore o uguale a 0
	   * @param messaggio Messaggio da mostrare in output
	   * @return intero in input
	   */
	  public static int leggiInteroNonNegativo(String messaggio)
	  {
		  return leggiInteroConMinimo(messaggio,0);
	  }
	  
	  /**
	   * metodo per leggere un int con un valore minimo
	   * @param messaggio Messaggio da mostrare in output
	   * @param minimo
	   * @return intero in input
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
	   * metodo per leggere un int tra un minimo e un massimo valore
	   * @param messaggio Messaggio da mostrare in output
	   * @param minimo
	   * @param massimo
	   * @return intero in input
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
	   * metodo per leggere un double
	   * @param messaggio Messaggio da mostrare in output
	   * @return double in input
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
	   * metodo per leggere un double con valore minimo
	   * @param messaggio Messaggio da mostrare in output
	   * @param minimo
	   * @return double in input
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

	  
	  public static boolean yesOrNo(String messaggio)
	  {
		  String mioMessaggio = messaggio + "("+RISPOSTA_SI+"/"+RISPOSTA_NO+") ";
		  char valoreLetto = leggiUpperChar(mioMessaggio,String.valueOf(RISPOSTA_SI)+String.valueOf(RISPOSTA_NO));
		  
		  if (valoreLetto == RISPOSTA_SI)
			return true;
		  else
			return false;
	  }
	  
	 
	  /**
	   * metodo per leggere un carattere che rappresenti il sesso dell'individuo: m, f, M , F
	   * @param messaggio Messaggio da mostrare in output
	   * @return carattere in input
	   */
	  public static char leggiSesso (String msg) {
			char sesso;
			do {
				sesso = InputDati.leggiChar(msg);
			} while ((sesso != 'M' && sesso != 'F') && (sesso != 'm' && sesso != 'f'));
			
			return sesso;
		}
	  
	  
	  
	  /**
	   * metodo per leggere una data del calendario (stringa)
	   * @return Date
	   */
	  public static Date leggiData () {
		  Date d = null;
		  String stringa = leggiStringa(DATE_STRING);
		  
		  try {
			  DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
			  formatoData.setLenient(false);
			  d = formatoData.parse(stringa);
			  
		  } catch (ParseException e) {
			  System.out.println(ERRORE_FORMATO);
		  }
		  
		  return d;
	  }
	  
	  
	  /**
	   * metodo per leggere un orario (stringa)
	   * @return Date
	   */
	  public static Date leggiOrarioCompleto () {
		  Date d = null;
		  String stringa = leggiStringa(ORARIO_STRING);
		  
		  try {
			  SimpleDateFormat formatoData = new SimpleDateFormat("hh:mm:ss");
			  d = formatoData.parse(stringa);
			  
		  } catch (ParseException e) {
			  System.out.println(ERRORE_FORMATO);
		  }
		  
		  return d;
	  }
	  
	  
	  /**
	   * metodo per leggere un orario senza ore (stringa)
	   * @return Date
	   */
	  public static Date leggiOrarioCompletoNoHour () {
		  Date d = null;
		  String stringa = leggiStringa(ORARIO_STRING_NOHOUR);
		  
		  try {
			  SimpleDateFormat formatoData = new SimpleDateFormat("mm:ss");
			  d = formatoData.parse(stringa);
			  
		  } catch (ParseException e) {
			  System.out.println(ERRORE_FORMATO);
		  }
		  
		  return d;
	  }
	  
}
