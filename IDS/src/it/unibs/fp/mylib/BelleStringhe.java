package it.unibs.fp.mylib;


/**
 * classe per rappresentare in modo ordinato le stringhe
 * @author federico
 */
public class BelleStringhe
{

 private final static String SPAZIO = " ";
 private final static String CORNICE = "---------------------------------------------------";
 private final static String ACAPO = "\n";


 /**
  * metodo per incorniciare stringhe
  * @param Stringa da incorniciare
  * @return Stringa incorniciata da stampare
  */
 public static String incornicia (String s)
	{ 
	 StringBuffer res = new StringBuffer();
		
	 res.append(CORNICE+ACAPO);
	 res.append(s+ACAPO);
	 res.append(CORNICE+ACAPO);

 	 return res.toString();

  }

 /**
  * metodo per incolonnare stringhe
  * @param s stringa da centrare
  * @param larghezza larghezza colonna
  * @return Stringa incolonnata da stampare
  */
 public static String incolonna (String s, int larghezza)
	{
	 StringBuffer res = new StringBuffer(larghezza);
	 int numCharDaStampare = Math.min(larghezza,s.length());
	 res.append(s.substring(0, numCharDaStampare));
	 for (int i=s.length()+1; i<=larghezza; i++)
		res.append(SPAZIO);
	 return res.toString();
	}
	
 /**
  * * metodo per centrare stringhe
  * @param s stringa da centrare
  * @param larghezza larghezza colonna
  * @return Stringa centrata da stampare
  */
 public static String centrata (String s, int larghezza)
	{
	 StringBuffer res = new StringBuffer(larghezza);
	 if (larghezza <= s.length())
		res.append(s.substring(larghezza));
	 else
		{
		 int spaziPrima = (larghezza - s.length())/2;
		 int spaziDopo = larghezza - spaziPrima - s.length();
		 for (int i=1; i<=spaziPrima; i++)
			res.append(SPAZIO);
			
		 res.append(s);
		
		 for (int i=1; i<=spaziDopo; i++)
			res.append(SPAZIO);
		}
	 	 return res.toString();

	}

 /**
  * * metodo per ripetere un carattere
  * @param elemento carattere da ripetere
  * @param larghezza numero di ripetizioni
  * @return Stringa finita da stampare
  */
	public static String ripetiChar (char elemento, int larghezza)
	 {
		 StringBuffer result = new StringBuffer(larghezza);
		 for (int i = 0; i < larghezza; i++)
			{
			 result.append(elemento);
			}
		 return result.toString();

	 }

	 /**
	  * * metodo per isolare una certa riga
	  * @param daIsolare 
	  */
	public static String rigaIsolata(String daIsolare)
	 {
		 StringBuffer result = new StringBuffer();
		 result.append(ACAPO);
		 result.append(daIsolare);
		 result.append(ACAPO);
		 return result.toString();
	 }
 
			
}

