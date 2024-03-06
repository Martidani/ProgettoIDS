package it.unibs.fp.mylib;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * classe per formattare date / orari (toString)
 * @author federico
 *
 */
public class Data {

	
	/**
	 * formatta data SHORT
	 * @param d
	 * @return
	 */
	public static String toString_DateSHORT (Date d) {
		
		DateFormat formatoData1 = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
		return formatoData1.format(d);	
	}
	
	
	/**
	 * formatta data MEDIUM
	 * @param d
	 * @return
	 */
	public static String toString_DateMEDIUM (Data d) {
		
		DateFormat formatoData1 = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ITALY);
		return formatoData1.format(d);	
	}

	
	/**
	 * formatta data LONG
	 * @param d
	 * @return
	 */
	public static String toString_DateLONG (Data d) {
	
		DateFormat formatoData1 = DateFormat.getDateInstance(DateFormat.LONG, Locale.ITALY);
		return formatoData1.format(d);	
	}

	
	/**
	 * formatta data FULL
	 * @param d
	 * @return
	 */
	public static String toString_DateFULL (Date d) {
	
		DateFormat formatoData1 = DateFormat.getDateInstance(DateFormat.FULL, Locale.ITALY);
		return formatoData1.format(d);
	}
	
	/**
	 * formatta orario hh:mm:ss
	 * @param o
	 * @return
	 */
	public static String toString_orarioCompleto (Date o) {
		
		SimpleDateFormat formatoData = new SimpleDateFormat("hh:mm:ss");
		return formatoData.format(o);
	}
	
	
	/**
	 * formatta orario hh:mm
	 * @param o
	 * @return
	 */
	public static String toString_orarioNosec (Date o) {
		
		SimpleDateFormat formatoData = new SimpleDateFormat("hh:mm");
		return formatoData.format(o);
	}
	
	
	/**
	 * formatta orario mm:ss
	 * @param o
	 * @return
	 */
	public static String toString_orarioNohour (Date o) {
		
		SimpleDateFormat formatoData = new SimpleDateFormat("mm:ss");
		return formatoData.format(o);
	}
	
}
