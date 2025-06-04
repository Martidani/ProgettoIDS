package it.unibs.ids.progetto;

/**
 * La classe PropostaDiScambio rappresenta una proposta di scambio tra due prestazioni.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class PropostaDiScambio {

    private boolean status; // false proposta aperta, true proposta chiusa
    private PrestazioneOpera richiesta;
    private PrestazioneOpera offerta;

    /**
     * Costruttore che inizializza una PropostaDiScambio con una prestazione richiesta e una offerta.
     *
     * @param richiesta La prestazione richiesta nella proposta di scambio.
     * @param offerta   La prestazione offerta nella proposta di scambio.
     */
    public PropostaDiScambio(PrestazioneOpera richiesta, PrestazioneOpera offerta) {
        this.status = false;
        this.richiesta = richiesta;
        this.offerta = offerta;

        setOfferta();
    }

    /**
     * Restituisce lo stato della proposta di scambio.
     *
     * @return True se la proposta è chiusa, False se è aperta.
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Cambia lo stato della proposta di scambio da aperta a chiusa.
     */
    public void changeStatus() {
        this.status = true;
    }

    /**
     * Restituisce la prestazione richiesta nella proposta di scambio.
     *
     * @return La prestazione richiesta.
     */
    public PrestazioneOpera getRichiesta() {
        return richiesta;
    }

    /**
     * Restituisce la prestazione offerta nella proposta di scambio.
     *
     * @return La prestazione offerta.
     */
    public PrestazioneOpera getOfferta() {
        return offerta;
    }

    /**
     * Imposta la durata della prestazione offerta in base al fattore di compatibilità tra le foglie delle prestazioni.
     */
    public void setOfferta() {
        int durata = this.richiesta.getDurata();
        Nodo foglia1 = this.richiesta.getFoglia();
        Nodo foglia2 = this.offerta.getFoglia();

        double fattore = foglia1.fattoreFoglia(foglia2);

        durata = (int) (fattore * durata);

        this.offerta.setDurata(durata);
    }
}