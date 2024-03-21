package it.unibs.ids.progetto;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class Nodo implements Serializable {
	
	private String nome;
	private boolean isLeaf;
	private boolean isRoot;
	private List<Nodo> children;
	private HashMap<Nodo, Double> fattori;
	private String campo;
	private List<String[]> dominio;
	
	/**
	 * COSTRUTTORE NON FOGLIA
	 * @param nome
	 * @param isRoot
	 * @param campo
	 * @param dominio
	 */
	
	public Nodo(String nome,boolean isRoot, String campo) {
	
		this.isRoot = isRoot;
		this.campo = campo;
		this.nome = nome;
		this.isLeaf = false;
		this.children = new ArrayList<>();
		this.dominio = new ArrayList<>();
	}

	/**
	 * COSTRUTTORE FOGLIA
	 * @param nome
	 * @param isRoot
	 */
	public Nodo(String nome, boolean isRoot) {
		
		this.nome = nome;
		this.isRoot = isRoot;
		this.isLeaf = true;
		this.fattori = new HashMap<>();
	}


	/**
	 * GETTER E SETTERS
	 * 
	 */

	public String getNome() { return nome; }
	public void setNome(String nome) { 	this.nome = nome; }

	public boolean isLeaf() { return isLeaf;}
	public void setLeaf(boolean isLeaf) { this.isLeaf = isLeaf;}

	public boolean isRoot() {return isRoot;}
	public void setRoot(boolean isRoot) {this.isRoot = isRoot;}

	public List<Nodo> getChildren() {return children;}
	public void setChildren(List<Nodo> children) {this.children = children;}

	public HashMap<Nodo, Double> getFattori() { return fattori;}
	public void addFattori(Nodo foglia, Double fattore) {
		fattori.put(foglia,fattore);
	}

	public String getCampo() {return campo;}
	public void setCampo(String campo) {this.campo = campo;}

	public List<String[]> getDominio() {return dominio;}

	public void addElementiDominio(String valore, String descrizione) {
		
		this.dominio.add(new String [] {
				valore, descrizione
		});
		
	}
	public void addElementiDominio(String valore) {
		
		this.dominio.add(new String [] {
				valore
		});
		
	}

	
	
	// Metodi
	
	public void addChild(Nodo child) throws Exception {
		if (this.isLeaf) throw new Exception("Le foglie non possono avere figli");
		this.children.add(child);
	}
	
	
	
	public boolean esisteFoglia(Nodo foglia) {
		
		if(this.getFattori().get(foglia) != null)
			return true;
		else
			return false;
	}
	
	public double valoreRelazione(Nodo foglia) {
		return this.getFattori().get(foglia);
	}
	
	public String toString() {
		
		StringBuffer bf = new StringBuffer();
		

		for (Entry<Nodo, Double> fatt : fattori.entrySet()) {
			Nodo key = fatt.getKey();
			Double val = fatt.getValue();
			
			bf.append(this.nome + " - " + key.getNome() + " - " + val);
			bf.append("\n");
		}
		
		return bf.toString();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
