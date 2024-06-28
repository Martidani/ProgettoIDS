package it.unibs.ids.progetto.main.model;
import java.io.Serializable;
import it.unibs.ids.progetto.Commercio;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Utenza;


public class Model implements Serializable {

    private static final long serialVersionUID = 1L;
	private ModelUtenza modelUtenza;
    private ModelGerarchia modelGerarchia;
    private ModelGeografia modelGeografia ;
    private ModelCommercio modelCommercio ;
    
    
	public Model() {
		super();
        this.modelUtenza = new ModelUtenza();
        this.modelGerarchia = new ModelGerarchia();
        this.modelGeografia = new ModelGeografia();
        this.modelCommercio = new ModelCommercio();
	}


	public ModelUtenza getModelUtenza() {
		return modelUtenza;
	}
	public ModelGerarchia getModelGerarchia() {
		return modelGerarchia;
	}
	public ModelGeografia getModelGeografia() {
		return modelGeografia;
	}
	public ModelCommercio getModelCommercio() {
		return modelCommercio;
	}
	
	public Utenza getUtenza() {
		return modelUtenza.getUtenza();
	}
	public Gerarchia getGerarchia() {
		return modelGerarchia.getGerarchia();
	}
	public Geografia getGeografia() {
		return modelGeografia.getGeografia();
	}
	public Commercio getCommercio() {
		return modelCommercio.getCommercio();
	}

	public void save() {
		modelGerarchia.save();
        modelUtenza.save();
        modelGeografia.save();
        modelCommercio.save();
	}

}