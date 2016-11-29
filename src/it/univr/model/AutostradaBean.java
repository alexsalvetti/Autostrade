package it.univr.model;

import java.io.Serializable;

public class AutostradaBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cod_a,n_caselli;
	private String nome_a;
	private float lunghezza;
	private int traffico_medio;
	public int getCod_a() {
		return cod_a;
	}
	public void setCod_a(int cod_a) {
		this.cod_a = cod_a;
	}
	public int getN_caselli() {
		return n_caselli;
	}
	public void setN_caselli(int n_caselli) {
		this.n_caselli = n_caselli;
	}
	public String getNome_a() {
		return nome_a;
	
	}
	public void setNome_a(String nome_a) {
		this.nome_a = nome_a;
	}
	public float getLunghezza() {
		return lunghezza;
	}
	public void setLunghezza(float lunghezza) {
		this.lunghezza = lunghezza;
	}
	public int getTraffico_medio() {
		return traffico_medio;
	}
	public void setTraffico_medio(int traffico_medio) {
		this.traffico_medio = traffico_medio;
	}
	
	
	
}
