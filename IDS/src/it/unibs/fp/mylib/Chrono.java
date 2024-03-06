package it.unibs.fp.mylib;

public class Chrono {
	
	public static final long TO_SECOND = 1000000000;
	public static final long TO_MILLI  = 1000000;
	
	
	private Long start;
	private Long stop;
	
	/**
	 * COSTRUTTORE che inizia il cronometro
	 */
	public Chrono() {
		start();
	}
	
	/**
	 * metodo per iniziare a contare
	 */
	public void start() {
		// start = tempo in nanosecondi (orario ? )
		start = System.nanoTime();
		stop = null;
	}
	/**
	 * metodo per finire di contare
	 * @return tempo dallo start
	 */
	public long stop() {
		// stop = tempo in nanosecondi (orario ? ) di adesso - orario di prima
		return (stop = System.nanoTime()) - start;
	}
	
	
	// metodi per stampare a video
	public long elapsed() {
		return (stop == null) ? System.nanoTime() - start : stop - start;
	}
	public String elapsedTime() {
		return format(elapsed()/TO_MILLI);
	}
	
	public static String format(long dtms) {
		long ms =  dtms % 1000; 	dtms /= 1000;
		long s = dtms % 60; 		dtms /= 60;
		
		return (dtms > 0) ? String.format("%02d:%02d.%03d", dtms, s, ms) :
			String.format("%02d.%03d", s, ms);
	}
	public String toString() {
		return elapsedTime();
	}

}
